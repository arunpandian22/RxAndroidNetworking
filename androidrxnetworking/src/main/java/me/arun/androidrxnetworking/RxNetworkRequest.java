package me.arun.androidrxnetworking;

import android.app.Activity;
import android.text.TextUtils;
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
 * a class to create the network request
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
    private Map<String,String> partParams=new HashMap<>();
    ApiInterfaceService apiInterfaceService;
    Retrofit retrofit;
    MultipartBody.Part multipartFile;
    String TAG="RxNetworkRequest";
    Activity activity;

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
        if (rxNetworkRequestBuilder.partParams != null && !rxNetworkRequestBuilder.partParams.isEmpty())
            this.partParams = rxNetworkRequestBuilder.partParams;
        if (rxNetworkRequestBuilder.fieldsParams != null && !rxNetworkRequestBuilder.fieldsParams.isEmpty())
            this.fieldsParams= rxNetworkRequestBuilder.fieldsParams;
        if (rxNetworkRequestBuilder.requestBody!=null)
            this.requestBody=rxNetworkRequestBuilder.requestBody;
        if (rxNetworkRequestBuilder.requestType!=null)
            this.requestType=rxNetworkRequestBuilder.requestType;
        if (rxNetworkRequestBuilder.file!=null)
            this.multipartFile=rxNetworkRequestBuilder.file;
        if (rxNetworkRequestBuilder.observableType != null)
            this.observableType = rxNetworkRequestBuilder.observableType;

        buildApiInterfaceService();
        Log.d(TAG, "RxNetworkRequest: retrofit"+NetworkingApiClient.getRetrofitClient());
    }


    /**
     * A method to initialize the interface call
     */
    public void buildApiInterfaceService() {
        retrofit = NetworkingApiClient.getRetrofitWithHeaders(headerParams);
        apiInterfaceService = retrofit.create(ApiInterfaceService.class);
    }


    /**
     * a method to test the make request for the  the file download
     *
     * @param url
     * @return
     */
    public Single<ResponseBody> getImageFullUrl(String url) {
        Retrofit retrofitImage  = NetworkingApiClient.getRetrofitClient();
        apiInterfaceService = retrofitImage.create(ApiInterfaceService.class);
        return apiInterfaceService.getImageSinglePublicRequest(url);
    }


    /**
     * a method to make request for the network call
     *
     * @param publishSubject   a instance of the PublishSubject
     * @param publishProcessor a instance of the PublishProcessor
     */

    public void makeRequest(PublishSubject<T> publishSubject, PublishProcessor<T> publishProcessor, String message) {
        Log.d(TAG, "makeRequest: " + retrofit.baseUrl());
        Log.d(TAG, "makeRequest: " + queryParams.size());
        if (TextUtils.isEmpty(message)) {
            ProgressDialogLoader.progressdialogCreation(this.activity, true);
        } else {
            ProgressBarData progressBarData = new ProgressBarData.ProgressBarBuilder().setProgressMessage(message).build();
            ProgressDialogLoader.progressdialogCreation(this.activity, progressBarData, true);
        }

        RxNetWorkApiCallHelper<T> rxNetWorkApiCallHelper=new RxNetWorkApiCallHelper();

        if (requestType == null)
            throw new IllegalArgumentException("request type must not be null.");


        if (observableType == null)
            throw new IllegalArgumentException("observable type must not be null.");
        Log.d(TAG, "makeRequest: " + requestType + " " + observableType);

        switch (requestType) {
            case RequestType.GET: {
                switch (observableType) {
                    case ObservableType.SINGLE:
                        Log.d(TAG, "makeRequest endpoint: " + endPoint);
                        rxNetWorkApiCallHelper.call(apiInterfaceService.getSingleObject(endPoint, queryParams), publishSubject, responseClassType);
                        break;
                    case ObservableType.MAYBE:
                        rxNetWorkApiCallHelper.callMaybe(apiInterfaceService.getMaybeObject(endPoint, queryParams), publishSubject, responseClassType);
                        break;
                    case ObservableType.FLOWABLE:
                        rxNetWorkApiCallHelper.callFlowable(apiInterfaceService.getFlowableObject(endPoint, queryParams), publishProcessor, responseClassType);
                        break;
                    case ObservableType.COMPLETABLE:
                }
            }

            break;

            case RequestType.POST:
                switch (observableType) {
                    case ObservableType.SINGLE:
                        if (requestBody == null && multipartFile == null) {
                            rxNetWorkApiCallHelper.call(apiInterfaceService.postSingleResponseBodyRequest(endPoint, queryParams), publishSubject, responseClassType);
                        } else if (requestBody == null) {
                            Log.d(TAG, "makeRequest endpoint: " + endPoint);
                            Log.d(TAG, "makeRequest: requestbody");
                            rxNetWorkApiCallHelper.call(apiInterfaceService.postSingleMultipartRequest(endPoint, multipartFile), publishSubject, responseClassType);
                        } else if (multipartFile == null) {
                            Log.d(TAG, "makeRequest: " + retrofit.baseUrl());
                            Log.d(TAG, "makeRequest endpoint: multiport " + endPoint + requestBody.contentType());
                            Log.d(TAG, "makeRequest: " + responseClassType.getCanonicalName());
                            rxNetWorkApiCallHelper.call(apiInterfaceService.postSingleObjectRequest(endPoint, requestBody), publishSubject, responseClassType);
//                            rxNetWorkApiCallHelper.call(apiInterfaceService.makeRegistrationApiCall("eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImZkZUBuZm4iLCJuYW1lIjoiUmF0aGlzaCIsImV4cCI6MTU0ODkxNzgxNn0.ndfmJI7c0twM9DnUZWQiKN-ZVCY_lvzWsyKgF6m9nEo", requestBody), publishSubject, responseClassType);
                        } else
                            rxNetWorkApiCallHelper.call(apiInterfaceService.postSingleMultipartObjectRequestquest(endPoint, queryParams, requestBody, multipartFile), publishSubject, responseClassType);
                        break;
                    case ObservableType.MAYBE:
                        if (requestBody == null && multipartFile == null)
                            rxNetWorkApiCallHelper.callMaybe(apiInterfaceService.postMaybeObjectRequest(endPoint, queryParams), publishSubject, responseClassType);
                        else if (requestBody == null)
                            rxNetWorkApiCallHelper.callMaybe(apiInterfaceService.postMaybeMultipartRequest(endPoint, multipartFile), publishSubject, responseClassType);
                        else if (multipartFile == null)
                            rxNetWorkApiCallHelper.callMaybe(apiInterfaceService.postMaybeObjectRequest(endPoint, requestBody), publishSubject, responseClassType);
                        else
                            rxNetWorkApiCallHelper.callMaybe(apiInterfaceService.postMaybeMultipartObjectRequest(endPoint, queryParams, requestBody, multipartFile), publishSubject, responseClassType);
                        break;


                    case ObservableType.FLOWABLE:

                        if (requestBody == null && multipartFile == null) {
                            Log.d(TAG, "makeRequest: call Post");
                            rxNetWorkApiCallHelper.callPost(apiInterfaceService.postFlowableObjectRequest(endPoint, queryParams), publishProcessor, responseClassType);
                        } else if (requestBody == null)
                            rxNetWorkApiCallHelper.callPost(apiInterfaceService.postFlowableMultipartRequest(endPoint, multipartFile), publishProcessor, responseClassType);
                        else if (multipartFile == null)
                            rxNetWorkApiCallHelper.callPost(apiInterfaceService.postflowableObjectRequest(endPoint, requestBody), publishProcessor, responseClassType);
                        else
                            rxNetWorkApiCallHelper.callPost(apiInterfaceService.postFlowableMultipartObjectRequest(endPoint, queryParams, requestBody, multipartFile), publishProcessor, responseClassType);
                        break;
                    case ObservableType.COMPLETABLE:

                }

                break;

            case RequestType.PUT:
                break;

            case RequestType.DELETE:

                switch (observableType) {
                    case ObservableType.SINGLE:
                        if (requestBody == null && multipartFile == null) {
                            rxNetWorkApiCallHelper.call(apiInterfaceService.deleteSingleResponseBodyRequest(endPoint, queryParams), publishSubject, responseClassType);
                        } else if (requestBody == null) {
                            Log.d(TAG, "makeRequest endpoint: " + endPoint);
                            Log.d(TAG, "makeRequest: requestbody");
                            rxNetWorkApiCallHelper.call(apiInterfaceService.deleteSingleResponseBodyRequest(endPoint, multipartFile), publishSubject, responseClassType);
                        } else if (multipartFile == null) {
                            Log.d(TAG, "makeRequest: " + retrofit.baseUrl());
                            Log.d(TAG, "makeRequest endpoint: multiport " + endPoint +" :"+ requestBody.contentType());
                            Log.d(TAG, "makeRequest: " + responseClassType.getCanonicalName());
                            rxNetWorkApiCallHelper.call(apiInterfaceService.deleteSingleResponseBodyRequest(endPoint, requestBody), publishSubject, responseClassType);
//                            rxNetWorkApiCallHelper.call(apiInterfaceService.makeRegistrationApiCall("eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImZkZUBuZm4iLCJuYW1lIjoiUmF0aGlzaCIsImV4cCI6MTU0ODkxNzgxNn0.ndfmJI7c0twM9DnUZWQiKN-ZVCY_lvzWsyKgF6m9nEo", requestBody), publishSubject, responseClassType);
                        } else
                            rxNetWorkApiCallHelper.call(apiInterfaceService.deleteSingleResponseBodyRequest(endPoint, queryParams, requestBody, multipartFile), publishSubject, responseClassType);
                        break;
                    case ObservableType.MAYBE:
                        if (requestBody == null && multipartFile == null)
                            rxNetWorkApiCallHelper.callMaybe(apiInterfaceService.deleteMaybeResponseBodyRequest(endPoint, queryParams), publishSubject, responseClassType);
                        else if (requestBody == null)
                            rxNetWorkApiCallHelper.callMaybe(apiInterfaceService.deleteMaybeResponseBodyRequest(endPoint, multipartFile), publishSubject, responseClassType);
                        else if (multipartFile == null)
                            rxNetWorkApiCallHelper.callMaybe(apiInterfaceService.deleteMaybeResponseBodyRequest(endPoint, requestBody), publishSubject, responseClassType);
                        else
                            rxNetWorkApiCallHelper.callMaybe(apiInterfaceService.deleteMaybeResponseBodyRequest(endPoint, queryParams, requestBody, multipartFile), publishSubject, responseClassType);
                        break;


                    case ObservableType.FLOWABLE:

                        if (requestBody == null && multipartFile == null) {
                            Log.d(TAG, "makeRequest: call Post");
                            rxNetWorkApiCallHelper.callPost(apiInterfaceService.deleteFlowableResponseBodyRequest(endPoint, queryParams), publishProcessor, responseClassType);
                        } else if (requestBody == null)
                            rxNetWorkApiCallHelper.callPost(apiInterfaceService.deleteFlowableResponseBodyRequest(endPoint, multipartFile), publishProcessor, responseClassType);
                        else if (multipartFile == null)
                            rxNetWorkApiCallHelper.callPost(apiInterfaceService.deleteFlowableResponseBodyRequest(endPoint, requestBody), publishProcessor, responseClassType);
                        else
                            rxNetWorkApiCallHelper.callPost(apiInterfaceService.deleteFlowableResponseBodyRequest(endPoint, queryParams, requestBody, multipartFile), publishProcessor, responseClassType);
                        break;
                    case ObservableType.COMPLETABLE:

                }

                break;
        }

    }


    /**
     * A method to make graphQlRequest
     * @param publishSubject
     * @param publishProcessor
     * @param message
     */
    public void makeGraphqlRequest(PublishSubject<T> publishSubject, PublishProcessor<T> publishProcessor,String message) {
        requestType = RequestType.POST;
        makeRequest(publishSubject, publishProcessor,message);
    }


    /**
     * test imageupload
     * @return
     */

    public void makeImageUpload(PublishSubject<T> publishSubject) {
        RxNetWorkApiCallHelper<T> rxNetWorkApiCallHelper=new RxNetWorkApiCallHelper();
//        rxNetWorkApiCallHelper.call(apiInterfaceService.postSingleMultipartRequest(endPoint,multipartFile),publishSubject,responseClassType);
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
        private Map<String,String> partParams=new HashMap<>();
        Activity activity;


        public RxNetworkRequestBuilder(Activity activity, String endPoint, @ObservableType String observableType, @RequestType String requestType, Class<T> responseClaasType) {
            this.endPoint = endPoint;
            this.observableType = observableType;
            this.responseClaasType=responseClaasType;
            this.requestType = requestType;
            this.activity=activity;
        }


        public RxNetworkRequestBuilder setEndPoint(String endPoint)
        {
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

        public RxNetworkRequestBuilder setPartParams(Map<String, String> partParams) {
            this.partParams = partParams;
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
