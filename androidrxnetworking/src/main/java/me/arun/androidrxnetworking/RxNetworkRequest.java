package me.arun.androidrxnetworking;

import android.graphics.Bitmap;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.subjects.PublishSubject;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

/**
 * Created by Arun Pandian M on 22/December/2018
 * arunsachin222@gmail.com
 * Chennai
 */
public class RxNetworkRequest<T>
{
    String endPoint;
    @ObservableType
    String observableType;
    @ResponseType
    String responseType;
    private Class<T> responseClassType;
    private RequestBody requestBody;
    @RequestType
    String requestType;
    private Map<String, String> headerParams = new HashMap<>();
    private Map<String, String> queryParams = new HashMap<>();
    private Map<String, String> fieldsParams = new HashMap<>();
    ApiInterfaceService apiInterfaceService;
    Retrofit retrofit;
    MultipartBody.Part multipartFile;
    String TAG="RxNetworkRequest";

    private RxNetworkRequest(RxNetworkRequestBuilder rxNetworkRequestBuilder)
    {

        this.endPoint = rxNetworkRequestBuilder.endPoint;
        if (rxNetworkRequestBuilder.responseClaasType!=null)
        this.responseClassType = rxNetworkRequestBuilder.responseClaasType;
        else

        this.responseType = rxNetworkRequestBuilder.responseType;
        if (rxNetworkRequestBuilder.queryParams != null && !rxNetworkRequestBuilder.queryParams.isEmpty())
            this.queryParams = rxNetworkRequestBuilder.queryParams;
        if (rxNetworkRequestBuilder.headerParams != null && !rxNetworkRequestBuilder.headerParams.isEmpty())
            this.headerParams = rxNetworkRequestBuilder.headerParams;
        if (rxNetworkRequestBuilder.requestBody!=null)
            this.requestBody=rxNetworkRequestBuilder.requestBody;
        if (rxNetworkRequestBuilder.requestType!=null)
            this.requestType=rxNetworkRequestBuilder.requestType;
        if (rxNetworkRequestBuilder.file!=null)
            this.multipartFile=rxNetworkRequestBuilder.file;
        if (rxNetworkRequestBuilder.file!=null)
            this.observableType=rxNetworkRequestBuilder.observableType;


        buildApiInterfaceService();
        Log.d(TAG, "RxNetworkRequest: retrofit"+NetworkingApiClient.getRetrofitClient());
    }


    public void buildApiInterfaceService()
    {
        retrofit = NetworkingApiClient.getRetrofitWithHeaders(headerParams);
        apiInterfaceService = retrofit.create(ApiInterfaceService.class);
    }


    public Single<ResponseBody> getImageFullUrl(String url)
    {
        Retrofit retrofitImage  = NetworkingApiClient.getRetrofitClient();
        apiInterfaceService = retrofitImage.create(ApiInterfaceService.class);
     return   apiInterfaceService.getImageSinglePublicRequest(url);

    }



    public void makeRequest(PublishSubject<T> publishSubject,PublishProcessor<T> publishProcessor)
    {
        RxNetWorkApiCallHelper<T> rxNetWorkApiCallHelper=new RxNetWorkApiCallHelper();

        if (requestType == null)
            throw new IllegalArgumentException("request type must not be null.");


        if (observableType == null)
            throw new IllegalArgumentException("observable type must not be null.");


        switch (requestType)
        {
            case RequestType.GET:
                {
                    switch (observableType)
                    {
                        case ObservableType.SINGLE:
                        rxNetWorkApiCallHelper.call(apiInterfaceService.getSingleObject(endPoint,queryParams),publishSubject,responseClassType);
                         break;
                        case ObservableType.MAYBE:
                            break;
                        case ObservableType.FLOWABLE:
                            break;
                        case ObservableType.COMPLETABLE:

                    }
            }

                break;
            case RequestType.POST:
                switch (observableType)
                {
                    case ObservableType.SINGLE:
                        if (requestBody==null &&  multipartFile==null)
                            rxNetWorkApiCallHelper.call(apiInterfaceService.postSingleResponseBodyRequest(endPoint, queryParams),publishSubject,responseClassType);
                        else if (requestBody==null)
                            rxNetWorkApiCallHelper.call(apiInterfaceService.postSingleMultipartRequest(endPoint,multipartFile),publishSubject,responseClassType);
                        else if (multipartFile==null)
                            rxNetWorkApiCallHelper.call(apiInterfaceService.postSingleObjectRequest(endPoint,requestBody),publishSubject,responseClassType);
                        else
                            rxNetWorkApiCallHelper.call(apiInterfaceService.postSingleMultipartObjectRequestquest(endPoint,queryParams,requestBody,multipartFile),publishSubject,responseClassType);
                        break;
                    case ObservableType.MAYBE:
                        if (requestBody==null &&  multipartFile==null)
                            rxNetWorkApiCallHelper.callMaybe(apiInterfaceService.postMaybeObjectRequest(endPoint, queryParams),publishSubject,responseClassType);
                        else if (requestBody==null)
                            rxNetWorkApiCallHelper.callMaybe(apiInterfaceService.postMaybeMultipartRequest(endPoint,multipartFile),publishSubject,responseClassType);
                        else if (multipartFile==null)
                            rxNetWorkApiCallHelper.callMaybe(apiInterfaceService.postMaybeObjectRequest(endPoint,requestBody),publishSubject,responseClassType);
                        else
                            rxNetWorkApiCallHelper.callMaybe(apiInterfaceService.postMaybeMultipartObjectRequest(endPoint,queryParams,requestBody,multipartFile),publishSubject,responseClassType);
                        break;


                    case ObservableType.FLOWABLE:

                        if (requestBody==null &&  multipartFile==null)
                            rxNetWorkApiCallHelper.callPost(apiInterfaceService.postFlowableObjectRequest(endPoint, queryParams),publishProcessor,responseClassType);
                        else if (requestBody==null)
                            rxNetWorkApiCallHelper.callPost(apiInterfaceService.postFlowableMultipartRequest(endPoint,multipartFile),publishProcessor,responseClassType);
                        else if (multipartFile==null)
                            rxNetWorkApiCallHelper.callPost(apiInterfaceService.postflowableObjectRequest(endPoint,requestBody),publishProcessor,responseClassType);
                        else
                            rxNetWorkApiCallHelper.callPost(apiInterfaceService.postFlowableMultipartObjectRequest(endPoint,queryParams,requestBody,multipartFile),publishProcessor,responseClassType);
                        break;
                    case ObservableType.COMPLETABLE:

                }

                break;

            case RequestType.PUT:
                break;

            case RequestType.DELETE:
                break;

        }

    }


    public Single<T> makeSingleRequest()
    {

        switch (requestType)
        {
            case RequestType.GET:
                return apiInterfaceService.getSingleObject(endPoint, queryParams);

            case RequestType.POST:
                if (requestBody==null &&  multipartFile==null)
                    return apiInterfaceService.postSingleObjectRequest(endPoint, queryParams);
                else if (requestBody==null)
                    return apiInterfaceService.postSingleMultipartObjectRequest(endPoint, queryParams,multipartFile);
                else if (multipartFile==null)
                    return apiInterfaceService.postSingleObjectRequest(endPoint,requestBody);
                else
                    return apiInterfaceService.postSingleMultipartObjectRequestquest(endPoint,queryParams,requestBody,multipartFile);


            case RequestType.PUT:
            case RequestType.DELETE:

        }

        return apiInterfaceService.getSingleObject(endPoint, queryParams);
    }


    public void makeFlowbleRequest()
    {


    }


    /**
     * test request
     * @param publishSubject
     * @param queryParam
     */
    public void makeSingleRequest(PublishSubject<T> publishSubject,Map<String,String> queryParam)
    {
       RxNetWorkApiCallHelper<T> rxNetWorkApiCallHelper=new RxNetWorkApiCallHelper();
        rxNetWorkApiCallHelper.call(apiInterfaceService.getSingleObject(endPoint,queryParam),publishSubject,responseClassType);
    }

    /**
     * test imageupload
     * @return
     */

    public void makeImageUpload(PublishSubject<T> publishSubject){
        RxNetWorkApiCallHelper<T> rxNetWorkApiCallHelper=new RxNetWorkApiCallHelper();
        rxNetWorkApiCallHelper.call(apiInterfaceService.postSingleMultipartRequest(endPoint,multipartFile),publishSubject,responseClassType);
    }

    public Maybe<T> makeMaybeRequest() {
        return  apiInterfaceService.getMaybeObject(endPoint,queryParams);
    }

    public Flowable<T> makeFlowableRequest() {
        return apiInterfaceService.getFlowableObject(endPoint,queryParams);
    }

    public Observable<T> makeObservableRequest() {
        return apiInterfaceService.getObservable(endPoint,queryParams);
    }

    public static class RxNetworkRequestBuilder<T>
    {
        String endPoint;
        @ObservableType
        String observableType;
        @ResponseType
        String responseType;
        private Class<T> responseClaasType;
        private RequestBody requestBody;



        MultipartBody.Part file;
        @RequestType
        String requestType;
        private Map<String, String> queryParams = new HashMap<>();
        private Map<String, String> fieldsParams = new HashMap<>();
        private Map<String, String> headerParams = new HashMap<>();


        public RxNetworkRequestBuilder(String endPoint, @ObservableType String observableType,Class<T> responseClaasType) {
            this.endPoint = endPoint;
            this.observableType = observableType;
            this.responseClaasType=responseClaasType;
        }


        public RxNetworkRequestBuilder setEndPoint(String endPoint) {
            this.endPoint = endPoint;
            return this;
        }

        public RxNetworkRequestBuilder setObservableType(@ObservableType String observableType)
        {
            this.observableType = observableType;
            return this;
        }

        public RxNetworkRequestBuilder setResponseType(@ResponseType String responseType)
        {
            this.responseType = responseType;
            return this;
        }

        public RxNetworkRequestBuilder setRequestType(@RequestType String requestType)
        {
            this.requestType = requestType;
            return this;
        }


        public MultipartBody.Part getFile() {
            return file;
        }

        public RxNetworkRequestBuilder setFile(MultipartBody.Part file) {
            this.file = file;
            return this;
        }
        public RxNetworkRequestBuilder setResponseClaasType(Class<T> responseClaasType)
        {
            this.responseClaasType = responseClaasType;
            return this;
        }

        public RxNetworkRequestBuilder setRequestBody(RequestBody requestBody)
        {
            this.requestBody = requestBody;
            return this;
        }

        public RxNetworkRequestBuilder setQueryParams(Map<String, String> queryParams)
        {
            this.queryParams = queryParams;
            return this;
        }

        public RxNetworkRequestBuilder setFieldsParams(Map<String, String> fieldsParams) {
            this.fieldsParams = fieldsParams;
            return this;
        }

        public RxNetworkRequestBuilder setHeaderParams(Map<String, String> headerParams) {
            this.headerParams = headerParams;
            return this;
        }


        public RxNetworkRequest<T> build() {
            return new RxNetworkRequest(this);
        }
    }

}
