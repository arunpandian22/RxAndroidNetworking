package me.arun.androidrxnetworking;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
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

                        if (queryParams!=null && !queryParams.isEmpty())
                        rxNetWorkApiCallHelper.call(apiInterfaceService.getSingleObject(endPoint, queryParams), publishSubject, responseClassType);
                        else
                            rxNetWorkApiCallHelper.call(apiInterfaceService.getSingleObject(endPoint), publishSubject, responseClassType);

                        break;
                    case ObservableType.MAYBE:
                        if (queryParams!=null && !queryParams.isEmpty())
                        rxNetWorkApiCallHelper.callMaybe(apiInterfaceService.getMaybeObject(endPoint, queryParams), publishSubject, responseClassType);
                        else
                            rxNetWorkApiCallHelper.callMaybe(apiInterfaceService.getMaybeObject(endPoint), publishSubject, responseClassType);
                        break;


                    case ObservableType.FLOWABLE:

                        if (queryParams!=null && !queryParams.isEmpty()) {
                            rxNetWorkApiCallHelper.callFlowable(apiInterfaceService.getFlowableObject(endPoint, queryParams), publishProcessor, responseClassType);
                        } else {
                            rxNetWorkApiCallHelper.callFlowable(apiInterfaceService.getFlowableObject(endPoint), publishProcessor, responseClassType);
                        }
                        break;
                    case ObservableType.COMPLETABLE:
                        break;
                }
            }

            break;

            case RequestType.POST:
                switch (observableType) {
                    case ObservableType.SINGLE:
                        //
                        if (multipartFile!=null && requestBody!= null && (partParams!=null && !partParams.isEmpty() ) ) {
                            rxNetWorkApiCallHelper.call(apiInterfaceService. postSingleMultipartBodyPartParamRequest(endPoint,multipartFile,partParams,requestBody), publishSubject, responseClassType);
                            
                        }else if (multipartFile!=null && requestBody!= null && (queryParams!=null && !queryParams.isEmpty()) ){
                            rxNetWorkApiCallHelper.call(apiInterfaceService.postSingleMultipartBodyQueryParamRequest(endPoint,multipartFile,queryParams,requestBody), publishSubject, responseClassType);

                        } else if (multipartFile!=null && (queryParams!=null && !queryParams.isEmpty())){
                            rxNetWorkApiCallHelper.call(apiInterfaceService.postSingleMultipartQueryParamRequest(endPoint, multipartFile,queryParams), publishSubject, responseClassType);

                        }else if (multipartFile!=null && (partParams!=null && !partParams.isEmpty())){
                            rxNetWorkApiCallHelper.call(apiInterfaceService.postSingleMultipartPartParamRequest(endPoint, multipartFile,partParams), publishSubject, responseClassType);
                        }else if (multipartFile!=null){
                            rxNetWorkApiCallHelper.call(apiInterfaceService.postSingleMultiPartObjectRequest(endPoint, multipartFile), publishSubject, responseClassType);
                        }else if (requestBody!=null && (queryParams!=null && !queryParams.isEmpty())){
                            rxNetWorkApiCallHelper.call(apiInterfaceService.postSingleBodyQueryParamsObjectRequestquest(endPoint, queryParams,requestBody), publishSubject, responseClassType);
                        }else if (requestBody!=null){
                            rxNetWorkApiCallHelper.call(apiInterfaceService.postSingleRequsetBodyRequest(endPoint,requestBody), publishSubject, responseClassType);
                        }else if ((queryParams!=null && !queryParams.isEmpty())){
                            rxNetWorkApiCallHelper.call(apiInterfaceService.postSingleQueryParamObjectRequest(endPoint, queryParams), publishSubject, responseClassType);
                        }else if ((fieldsParams!=null && !fieldsParams.isEmpty())){
                            rxNetWorkApiCallHelper.call(apiInterfaceService.postSingleFormUrlEncodedRequest(endPoint, fieldsParams), publishSubject, responseClassType);

                        }else if (queryParams==null || queryParams.isEmpty()){
                            rxNetWorkApiCallHelper.call(apiInterfaceService.postSingleQueryParamObjectRequest(endPoint, queryParams), publishSubject, responseClassType);

                        }else {
                            rxNetWorkApiCallHelper.call(apiInterfaceService.postSingleResponseBodyRequest(endPoint), publishSubject, responseClassType);
                        }
                        break;
                    case ObservableType.MAYBE:

                        if (multipartFile!=null && requestBody!= null && (partParams!=null && !partParams.isEmpty() ) ) {
                            rxNetWorkApiCallHelper.callMaybe(apiInterfaceService. postMaybeMultipartBodyPartParamRequest(endPoint,multipartFile,partParams,requestBody), publishSubject, responseClassType);
                        }else if (multipartFile!=null && requestBody!= null && (queryParams!=null && !queryParams.isEmpty()) ){
                            rxNetWorkApiCallHelper.callMaybe(apiInterfaceService.postMaybeMultipartBodyQueryParamRequest(endPoint,multipartFile,queryParams,requestBody), publishSubject, responseClassType);
                        } else if (multipartFile!=null && (queryParams!=null && !queryParams.isEmpty())){
                            rxNetWorkApiCallHelper.callMaybe(apiInterfaceService.postMaybeMultipartQueryParamRequest(endPoint, multipartFile,queryParams), publishSubject, responseClassType);
                        }else if (multipartFile!=null && (partParams!=null && !partParams.isEmpty())){
                            rxNetWorkApiCallHelper.callMaybe(apiInterfaceService.postMaybeMultipartPartParamRequest(endPoint, multipartFile,partParams), publishSubject, responseClassType);
                        }else if (multipartFile!=null){
                            rxNetWorkApiCallHelper.callMaybe(apiInterfaceService.postMaybeMultiPartObjectRequest(endPoint, multipartFile), publishSubject, responseClassType);
                        }else if (requestBody!=null && (queryParams!=null && !queryParams.isEmpty())){
                            rxNetWorkApiCallHelper.callMaybe(apiInterfaceService.postMaybeBodyQueryParamsObjectRequestquest(endPoint, queryParams,requestBody), publishSubject, responseClassType);
                        }else if (requestBody!=null){
                            rxNetWorkApiCallHelper.callMaybe(apiInterfaceService.postMaybeRequsetBodyRequest(endPoint,requestBody), publishSubject, responseClassType);
                        }else if ((queryParams!=null && !queryParams.isEmpty())){
                            rxNetWorkApiCallHelper.callMaybe(apiInterfaceService.postMaybeQueryParamObjectRequest(endPoint, queryParams), publishSubject, responseClassType);
                        }else if ((fieldsParams!=null && !fieldsParams.isEmpty())){
                            rxNetWorkApiCallHelper.callMaybe(apiInterfaceService.postMaybeFormUrlEncodedRequest(endPoint, fieldsParams), publishSubject, responseClassType);

                        }else if (queryParams==null || queryParams.isEmpty()){
                            rxNetWorkApiCallHelper.callMaybe(apiInterfaceService.postMaybeQueryParamObjectRequest(endPoint, queryParams), publishSubject, responseClassType);
                        }else {
                            rxNetWorkApiCallHelper.callMaybe(apiInterfaceService.postMaybeResponseBodyRequest(endPoint), publishSubject, responseClassType);
                        }


                    case ObservableType.FLOWABLE:

                        if (multipartFile!=null && requestBody!= null && (partParams!=null && !partParams.isEmpty() ) ) {
                            rxNetWorkApiCallHelper.callFlowable(apiInterfaceService. postFlowableMultipartBodyPartParamRequest(endPoint,multipartFile,partParams,requestBody), publishProcessor, responseClassType);
                        }else if (multipartFile!=null && requestBody!= null && (queryParams!=null && !queryParams.isEmpty()) ){
                            rxNetWorkApiCallHelper.callFlowable(apiInterfaceService.postFlowableMultipartBodyQueryParamRequest(endPoint,multipartFile,queryParams,requestBody), publishProcessor, responseClassType);
                        } else if (multipartFile!=null && (queryParams!=null && !queryParams.isEmpty())){
                            rxNetWorkApiCallHelper.callFlowable(apiInterfaceService.postFlowableMultipartQueryParamRequest(endPoint, multipartFile,queryParams), publishProcessor, responseClassType);
                        }else if (multipartFile!=null && (partParams!=null && !partParams.isEmpty())){
                            rxNetWorkApiCallHelper.callFlowable(apiInterfaceService.postFlowableMultipartPartParamRequest(endPoint, multipartFile,partParams),publishProcessor, responseClassType);
                        }else if (multipartFile!=null){
                            rxNetWorkApiCallHelper.callFlowable(apiInterfaceService.postFlowableMultiPartObjectRequest(endPoint, multipartFile), publishProcessor, responseClassType);
                        }else if (requestBody!=null && (queryParams!=null && !queryParams.isEmpty())){
                            rxNetWorkApiCallHelper.callFlowable(apiInterfaceService.postFlowableBodyQueryParamsObjectRequestquest(endPoint, queryParams,requestBody), publishProcessor, responseClassType);
                        }else if (requestBody!=null){
                            rxNetWorkApiCallHelper.callFlowable(apiInterfaceService.postFlowableRequsetBodyRequest(endPoint,requestBody),publishProcessor, responseClassType);
                        }else if ((queryParams!=null && !queryParams.isEmpty())){
                            rxNetWorkApiCallHelper.callFlowable(apiInterfaceService.postFlowableQueryParamObjectRequest(endPoint, queryParams),publishProcessor, responseClassType);
                        }else if ((fieldsParams!=null && !fieldsParams.isEmpty())){
                            rxNetWorkApiCallHelper.callFlowable(apiInterfaceService.postFlowableFormUrlEncodedRequest(endPoint, fieldsParams), publishProcessor, responseClassType);
                        }else if (queryParams==null || queryParams.isEmpty()){
                            rxNetWorkApiCallHelper.callFlowable(apiInterfaceService.postFlowableQueryParamObjectRequest(endPoint, queryParams),publishProcessor, responseClassType);
                        }else {
                            rxNetWorkApiCallHelper.callFlowable(apiInterfaceService.postFlowableResponseBodyRequest(endPoint), publishProcessor, responseClassType);
                        }


                    case ObservableType.COMPLETABLE:

                }

                break;

            case RequestType.PUT: {

                switch (observableType) {
                    case ObservableType.SINGLE:
                        if (requestBody == null && multipartFile == null) {
                            rxNetWorkApiCallHelper.call(apiInterfaceService.putSingleResponseBodyRequest(endPoint, queryParams), publishSubject, responseClassType);
                        } else if (requestBody == null) {
                            Log.d(TAG, "makeRequest endpoint: " + endPoint);
                            Log.d(TAG, "makeRequest: requestbody");
                            rxNetWorkApiCallHelper.call(apiInterfaceService.putSingleResponseBodyRequest(endPoint, multipartFile), publishSubject, responseClassType);
                        } else if (multipartFile == null) {
                            Log.d(TAG, "makeRequest: " + retrofit.baseUrl());
                            Log.d(TAG, "makeRequest endpoint: multiport " + endPoint +" :"+ requestBody.contentType());
                            Log.d(TAG, "makeRequest: " + responseClassType.getCanonicalName());
                            rxNetWorkApiCallHelper.call(apiInterfaceService.putSingleResponseBodyRequest(endPoint, requestBody), publishSubject, responseClassType);
//                            rxNetWorkApiCallHelper.call(apiInterfaceService.makeRegistrationApiCall("eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImZkZUBuZm4iLCJuYW1lIjoiUmF0aGlzaCIsImV4cCI6MTU0ODkxNzgxNn0.ndfmJI7c0twM9DnUZWQiKN-ZVCY_lvzWsyKgF6m9nEo", requestBody), publishSubject, responseClassType);
                        } else
                            rxNetWorkApiCallHelper.call(apiInterfaceService.putSingleResponseBodyRequest(endPoint, queryParams, requestBody, multipartFile), publishSubject, responseClassType);
                        break;
                    case ObservableType.MAYBE:
                        if (requestBody == null && multipartFile == null)
                            rxNetWorkApiCallHelper.callMaybe(apiInterfaceService.putMaybeResponseBodyRequest(endPoint, queryParams), publishSubject, responseClassType);
                        else if (requestBody == null)
                            rxNetWorkApiCallHelper.callMaybe(apiInterfaceService.putMaybeResponseBodyRequest(endPoint, multipartFile), publishSubject, responseClassType);
                        else if (multipartFile == null)
                            rxNetWorkApiCallHelper.callMaybe(apiInterfaceService.putMaybeResponseBodyRequest(endPoint, requestBody), publishSubject, responseClassType);
                        else
                            rxNetWorkApiCallHelper.callMaybe(apiInterfaceService.putMaybeResponseBodyRequest(endPoint, queryParams, requestBody, multipartFile), publishSubject, responseClassType);
                        break;


                    case ObservableType.FLOWABLE:

                        if (requestBody == null && multipartFile == null) {
                            Log.d(TAG, "makeRequest: call Post");
                            rxNetWorkApiCallHelper.callPost(apiInterfaceService.putFlowableResponseBodyRequest(endPoint, queryParams), publishProcessor, responseClassType);
                        } else if (requestBody == null)
                            rxNetWorkApiCallHelper.callPost(apiInterfaceService.putFlowableResponseBodyRequest(endPoint, multipartFile), publishProcessor, responseClassType);
                        else if (multipartFile == null)
                            rxNetWorkApiCallHelper.callPost(apiInterfaceService.putFlowableResponseBodyRequest(endPoint, requestBody), publishProcessor, responseClassType);
                        else
                            rxNetWorkApiCallHelper.callPost(apiInterfaceService.putFlowableResponseBodyRequest(endPoint, queryParams, requestBody, multipartFile), publishProcessor, responseClassType);
                        break;
                    case ObservableType.COMPLETABLE:

                }
            }


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


    /**
     * A class to build the objects for the making API request
     * @param <T> it is generic class type
     */
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


        /**
         * A method to set the endpoint
         * @param endPoint endpoint of api calls
         * @return it returns this Builder object
         */
        public RxNetworkRequestBuilder setEndPoint(String endPoint)
        {
            this.endPoint = endPoint;
            return this;
        }

        /**
         * A method to set the observable the API call in rxjava
         * @param observableType observble type to set in the Rx Java call
         * @return it returns this Builder object
         */
        public RxNetworkRequestBuilder setObservableType(@ObservableType String observableType)
        {
            this.observableType = observableType;
            return this;
        }


        /**
         * A method to set the responseType for returning t
         * @param responseType it has the value for the in which class the response should be return
         * @return it returns this Builder object
         */
        @Deprecated
        public RxNetworkRequestBuilder setResponseType(@ResponseType String responseType)
        {
            this.responseType = responseType;
            return this;
        }


        /**
         * A method to set the set the HTTP request type
         * @param requestType it has the value of the HTTP request type
         * @return  it returns this Builder object
         */
        public RxNetworkRequestBuilder setRequestType(@RequestType String requestType)
        {
            this.requestType = requestType;
            return this;
        }


        /**
         * A method to get the multipart file
         * @return it returns the multpart file which has to be sent as a param
         */
        public MultipartBody.Part getFile() {
            return file;
        }

        /**
         * A method to set the file to params for send the file as a multipart file
         * @param file  the  file which has to be sent as a multipart param
         * @return it returns this Builder object
         */
        public RxNetworkRequestBuilder setFile(MultipartBody.Part file) {
            this.file = file;
            return this;
        }

        /**
         * A method to set the custom response type
         * @param responseClaasType has the custom object which is the response model should be return
         * @return it returns this Builder object
         */
        public RxNetworkRequestBuilder setResponseClaasType(Class<T> responseClaasType)
        {
            this.responseClaasType = responseClaasType;
            return this;
        }

        /**
         * A method to set the requestbody to send the params
         * @param requestBody the param has requestbody which has to be sent in params
         * @return it returns this Builder object
         */
        public RxNetworkRequestBuilder setRequestBody(RequestBody requestBody)
        {
            this.requestBody = requestBody;
            return this;
        }


        /**
         * A method to set the normal string params
         * @param queryParams a query params which has to be sent as a string params
         * @return it returns this Builder object
         */
        public RxNetworkRequestBuilder setQueryParams(Map<String, String> queryParams)
        {
            this.queryParams = queryParams;
            return this;
        }

        /**
         * A method to set the form data params
         * @param fieldsParams a hashmap params which has to be sent as the form data param
         * @return it returns this Builder object
         */
        public RxNetworkRequestBuilder setFieldsParams(Map<String, String> fieldsParams) {
            this.fieldsParams = fieldsParams;
            return this;
        }

        /**
         * A method to set the form data when the multipart file has to be sent
         * @param partParams  a hashmap params which has to be sent as the form data param  with the multipart file
         * @return it returns this Builder object
         */
        public RxNetworkRequestBuilder setPartParams(Map<String, String> partParams) {
            this.partParams = partParams;
            return this;
        }

        /**
         * A method to set the header params in API call
         * @param headerParams   a hashmap params which has to be sent as header param
         * @return it returns this Builder object
         */
        public RxNetworkRequestBuilder setHeaderParams(Map<String, String> headerParams) {
            this.headerParams = headerParams;
            return this;
        }


        /**
         * A method to build the required object to make API request
         * @return
         */
        public RxNetworkRequest<T> build() {
            return new RxNetworkRequest(this);
        }
    }

}
