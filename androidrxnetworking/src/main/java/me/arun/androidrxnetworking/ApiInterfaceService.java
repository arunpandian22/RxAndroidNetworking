package me.arun.androidrxnetworking;

import android.graphics.Bitmap;
import java.util.Map;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
/**
 * Created by Arun Pandian M on 21/December/2018
 * arunsachin222@gmail.com
 * Chennai
 */


/**
 * A interface has the abstracts method to access the all type of request types and API calls
 */
public interface ApiInterfaceService
{
    //Get custom object Request

    // Get Params
    //for Single

    @Headers({"Accept: application/json"})
    @GET
    Single<ResponseBody> getSingleObject(@Url String url);

    @Headers({"Accept: application/json"})
    @GET
    Single<ResponseBody> getSingleObject(@Url String url, @QueryMap Map<String, String> params);

    @Streaming
    @GET
    Single<ResponseBody> getImageSinglePublicRequest(@Url String url);




    // Get request Flowable type

    @Headers({"Accept: application/json"})
    @GET
    Flowable<ResponseBody> getFlowableObject(@Url String url);

    @Headers({"Accept: application/json"})
    @GET
    Flowable<ResponseBody> getFlowableObject(@Url String url, @QueryMap Map<String, String> params);


    // Get  request Maybe type

    @Headers({"Accept: application/json"})
    @GET
    Maybe<ResponseBody> getMaybeObject(@Url String url);

    @Headers({"Accept: application/json"})
    @GET
    Maybe<ResponseBody> getMaybeObject(@Url String url, @QueryMap Map<String, String> params);



    // Get request  Observable type
    @Headers({"Accept: application/json"})
    @GET
    Observable<ResponseBody> getObservable(@Url String url);


    @Headers({"Accept: application/json"})
    @GET
    Observable<ResponseBody> getObservable(@Url String url, @QueryMap Map<String, String> params);




    /**
     *

     //Post okkhttp3 ResponseBody  request
     Post type network calls
     * @param url
     * @return
     */


    @Headers({"Accept: application/json"})
    @POST
    Single<ResponseBody> postSingleResponseBodyRequest(@Url String url);


    @Headers({"Accept: application/json"})
    @POST
    Single<ResponseBody> postSingleQueryParamObjectRequest(@Url String url, @QueryMap Map<String, String> params);


    @FormUrlEncoded
    @POST
    Single<ResponseBody> postSingleFormUrlEncodedRequest(@Url String url, @FieldMap Map<String, String> fields);


    @POST
    Single<ResponseBody> postSingleRequsetBodyRequest(@Url String url, @Body RequestBody post);


    @POST
    Single<ResponseBody> postSingleBodyQueryParamsObjectRequestquest(@Url String url, @QueryMap Map<String, String> params, @Body RequestBody post);



    @POST
    Single<ResponseBody> postSingleBodyPartObjectRequestquest(@Url String url, @PartMap Map<String, String> params, @Body RequestBody post);



    @Multipart
    @POST
    Single<ResponseBody> postSingleMultiPartObjectRequest(@Url String url, @Part MultipartBody.Part file);


    @Multipart
    @POST
    Single<ResponseBody> postSingleMultipartPartParamRequest(@Url String url, @Part MultipartBody.Part file,@PartMap Map<String,String> partParams);

    @Multipart
    @POST
    Single<ResponseBody> postSingleMultipartQueryParamRequest(@Url String url, @Part MultipartBody.Part file,@QueryMap Map<String,String> queryParams);

    @Multipart
    @POST
    Single<ResponseBody> postSingleMultipartBodyPartParamRequest(@Url String url, @Part MultipartBody.Part file,@PartMap Map<String,String> partParams,@Body RequestBody requestBody);


    @Multipart
    @POST
    Single<ResponseBody> postSingleMultipartBodyQueryParamRequest(@Url String url, @Part MultipartBody.Part file,@QueryMap Map<String,String> partParams,@Body  RequestBody BrequestBody);


    /**
     *     Flowable post request type method
      */


    @Headers({"Accept: application/json"})
    @POST
    Flowable<ResponseBody> postFlowableResponseBodyRequest(@Url String url);


    @Headers({"Accept: application/json"})
    @POST
    Flowable<ResponseBody> postFlowableQueryParamObjectRequest(@Url String url, @QueryMap Map<String, String> params);


    @FormUrlEncoded
    @POST
    Flowable<ResponseBody> postFlowableFormUrlEncodedRequest(@Url String url, @FieldMap Map<String, String> fields);


    @POST
    Flowable<ResponseBody> postFlowableRequsetBodyRequest(@Url String url, @Body RequestBody post);


    @POST
    Flowable<ResponseBody> postFlowableBodyQueryParamsObjectRequestquest(@Url String url, @QueryMap Map<String, String> params, @Body RequestBody post);



    @Multipart
    @POST
    Flowable<ResponseBody> postFlowableBodyPartObjectRequestquest(@Url String url, @PartMap Map<String, String> params, @Body RequestBody post);



    @Multipart
    @POST
    Flowable<ResponseBody> postFlowableMultiPartObjectRequest(@Url String url, @Part MultipartBody.Part file);


    @Multipart
    @POST
    Flowable<ResponseBody> postFlowableMultipartPartParamRequest(@Url String url, @Part MultipartBody.Part file,@PartMap Map<String,String> partParams);

    @Multipart
    @POST
    Flowable<ResponseBody> postFlowableMultipartQueryParamRequest(@Url String url, @Part MultipartBody.Part file,@QueryMap Map<String,String> queryParams);

    @Multipart
    @POST
    Flowable<ResponseBody> postFlowableMultipartBodyPartParamRequest(@Url String url, @Part MultipartBody.Part file,@PartMap Map<String,String> partParams,@Body RequestBody requestBody);


    @Multipart
    @POST
    Flowable<ResponseBody> postFlowableMultipartBodyQueryParamRequest(@Url String url, @Part MultipartBody.Part file,@QueryMap Map<String,String> partParams,@Body  RequestBody BrequestBody);




    // Maybe Post request type
    @Headers({"Accept: application/json"})
    @POST
    Maybe<ResponseBody> postMaybeResponseBodyRequest(@Url String url);


    @Headers({"Accept: application/json"})
    @POST
    Maybe<ResponseBody> postMaybeQueryParamObjectRequest(@Url String url, @QueryMap Map<String, String> params);


    @FormUrlEncoded
    @POST
    Maybe<ResponseBody> postMaybeFormUrlEncodedRequest(@Url String url, @FieldMap Map<String, String> fields);


    @POST
    Maybe<ResponseBody> postMaybeRequsetBodyRequest(@Url String url, @Body RequestBody post);


    @POST
    Maybe<ResponseBody> postMaybeBodyQueryParamsObjectRequestquest(@Url String url, @QueryMap Map<String, String> params, @Body RequestBody post);


    @Multipart
    @POST
    Maybe<ResponseBody> postMaybeBodyPartObjectRequestquest(@Url String url, @PartMap Map<String, String> params, @Body RequestBody post);

    @Multipart
    @POST
    Maybe<ResponseBody> postMaybeMultiPartObjectRequest(@Url String url, @Part MultipartBody.Part file);

    @Multipart
    @POST
    Maybe<ResponseBody> postMaybeMultipartPartParamRequest(@Url String url, @Part MultipartBody.Part file,@PartMap Map<String,String> partParams);

    @Multipart
    @POST
    Maybe<ResponseBody> postMaybeMultipartQueryParamRequest(@Url String url, @Part MultipartBody.Part file,@QueryMap Map<String,String> queryParams);

    @Multipart
    @POST
    Maybe<ResponseBody> postMaybeMultipartBodyPartParamRequest(@Url String url, @Part MultipartBody.Part file,@PartMap Map<String,String> partParams,@Body RequestBody requestBody);

    @Multipart
    @POST
    Maybe<ResponseBody> postMaybeMultipartBodyQueryParamRequest(@Url String url, @Part MultipartBody.Part file,@QueryMap Map<String,String> queryParams,@Body RequestBody requestBody);

    //Observable post type


    @Headers({"Accept: application/json"})
    @POST
    Observable<ResponseBody> postObservableResponseBodyRequest(@Url String url);


    @Headers({"Accept: application/json"})
    @POST
    Observable<ResponseBody> postObservableObjectRequest(@Url String url, @QueryMap Map<String, String> params);

    @Multipart
    @POST
    Observable<ResponseBody> postObservableMultipartRequest(@Url String url, @Part MultipartBody.Part file);


    @Multipart
    @POST
    Observable<ResponseBody> postObservableMultipartPartParamsRequest(@Url String url, @Part MultipartBody.Part file,@PartMap Map<String,String> partParams);

    @POST
    Observable<ResponseBody> postObservableObjectRequest(@Url String url, @Body RequestBody post);

    @FormUrlEncoded
    @POST
    Observable<ResponseBody> postObservableFormUrlEncodedRequest(@FieldMap Map<String, String> fields, @Url String url);


    @Multipart
    @POST
    Observable<ResponseBody> postObservableMultipartParamRequest(@Url String url, @Part MultipartBody.Part file,@QueryMap Map<String,String> partParams);


    @Multipart
    @POST
    Observable<ResponseBody> postObservableMultipartBodyObjectRequest(@Url String url, @QueryMap Map<String, String> params, @Body RequestBody post, @Part MultipartBody.Part file);









    // PUT  custom object request


    @PUT
    Single<ResponseBody> putSingleResponseBodyRequest(@Url String url);

    @PUT
    Single<ResponseBody> putSingleResponseBodyRequest(@Url String url, @Body RequestBody post);

    @FormUrlEncoded
    @PUT
    Single<ResponseBody> putSingleResponseBodyRequest(@Url String url, @FieldMap Map<String, String> fields);

    @PUT
    Single<ResponseBody> putSingleResponseBodyRequest(@QueryMap Map<String, String> params, @Url String url);

    @PUT
    Single<ResponseBody> putSingleResponseBodyRequest(@Url String url, @QueryMap Map<String, String> params, @Body RequestBody post);

    @FormUrlEncoded
    @PUT
    Single<ResponseBody> putSingleResponseBodyRequest(@Url String url, @QueryMap Map<String, String> params, @FieldMap Map<String, String> fields);


    @Multipart
    @PUT
    Single<ResponseBody> putSingleResponseBodyRequest(@Url String url, @QueryMap Map<String, String> params, @Body RequestBody post, @Part MultipartBody.Part file);


    @Multipart
    @PUT
    Single<ResponseBody> putSingleResponseBodyRequest(@Url String url, @Part MultipartBody.Part file);

    @PUT
    Flowable<ResponseBody> putFlowableResponseBodyRequest(@Url String url);

    @PUT
    Flowable<ResponseBody> putFlowableResponseBodyRequest(@Url String url, @Body RequestBody post);

    @Multipart
    @PUT
    Flowable<ResponseBody> putFlowableResponseBodyRequest(@Url String url, @Part MultipartBody.Part file);

    @FormUrlEncoded
    @PUT
    Flowable<ResponseBody> putFlowableResponseBodyRequest(@Url String url, @FieldMap Map<String, String> fields);

    @PUT
    Flowable<ResponseBody> putFlowableResponseBodyRequest(@QueryMap Map<String, String> params,@Url String url);

    @PUT
    Flowable<ResponseBody> putFlowableResponseBodyRequest(@Url String url, @QueryMap Map<String, String> params, @Body RequestBody post);

    @FormUrlEncoded
    @PUT
    Flowable<ResponseBody> putFlowableResponseBodyRequest(@Url String url, @QueryMap Map<String, String> params, @FieldMap Map<String, String> fields);


    @Multipart
    @PUT
    Flowable<ResponseBody> putFlowableResponseBodyRequest(@Url String url, @QueryMap Map<String, String> params, @Body RequestBody post, @Part MultipartBody.Part file);


    @PUT
    Maybe<ResponseBody> putMaybeResponseBodyRequest(@Url String url);

    @PUT
    Maybe<ResponseBody> putMaybeResponseBodyRequest(@Url String url, @Body RequestBody post);

    @Multipart
    @PUT
    Maybe<ResponseBody> putMaybeResponseBodyRequest(@Url String url, @Part MultipartBody.Part file);

    @FormUrlEncoded
    @PUT
    Maybe<ResponseBody> putMaybeResponseBodyRequest(@Url String url, @FieldMap Map<String, String> fields);

    @PUT
    Maybe<ResponseBody> putMaybeResponseBodyRequest(@QueryMap Map<String, String> params, @Url String url);

    @PUT
    Maybe<ResponseBody> putMaybeResponseBodyRequest(@Url String url, @QueryMap Map<String, String> params, @Body RequestBody post);

    @FormUrlEncoded
    @PUT
    Maybe<ResponseBody> putMaybeResponseBodyRequest(@Url String url, @QueryMap Map<String, String> params, @FieldMap Map<String, String> fields);

    @Multipart
    @PUT
    Maybe<ResponseBody> putMaybeResponseBodyRequest(@Url String url, @QueryMap Map<String, String> params, @Body RequestBody post, @Part MultipartBody.Part file);


    @PUT
    Observable<ResponseBody> putObservableResponseBodyRequest(@Url String url);

    @PUT
    Observable<ResponseBody> putObservableResponseBodyRequest(@Url String url, @Body RequestBody post);

    @FormUrlEncoded
    @PUT
    Observable<ResponseBody> putObservableResponseBodyRequest(@Url String url, @FieldMap Map<String, String> fields);

    @PUT
    Observable<ResponseBody> putObservableResponseBodyRequest(@QueryMap Map<String, String> params, @Url String url);

    @PUT
    Observable<ResponseBody> putObservableResponseBodyRequest(@Url String url, @QueryMap Map<String, String> params, @Body RequestBody post);

    @FormUrlEncoded
    @PUT
    Observable<ResponseBody> putObservableResponseBodyRequest(@Url String url, @QueryMap Map<String, String> params, @FieldMap Map<String, String> fields);


    // DELETE request



    // DELETE request

    @DELETE
    Single<ResponseBody> deleteSingleResponseBodyRequest(@Url String url);

    @HTTP(method = "DELETE", hasBody = true)
    Single<ResponseBody> deleteSingleResponseBodyRequest(@Url String url, @Body RequestBody post);


    @HTTP(method = "DELETE", hasBody = true)
    Single<ResponseBody> deleteSingleResponseBodyRequest(@FieldMap Map<String, Object> fields, @Url String url);

    @DELETE
    Single<ResponseBody> deleteSingleResponseBodyRequest(@Url String url, @QueryMap Map<String, String> params);

    @DELETE
    Single<ResponseBody> deleteSingleResponseBodyRequest(@Url String url, @Part MultipartBody.Part file);


    @DELETE
    Single<ResponseBody> deleteSingleResponseBodyRequest(@Url String url, @QueryMap Map<String, String> params, @Body RequestBody post, @Part MultipartBody.Part file);;

    @HTTP(method = "DELETE", hasBody = true)
    Single<ResponseBody> deleteSingleResponseBodyRequest(@Url String url, @QueryMap Map<String, String> params, @Body RequestBody post);

    @HTTP(method = "DELETE", hasBody = true)
    Single<ResponseBody> deleteSingleResponseBodyRequest(@Url String url, @QueryMap Map<String, String> params, @FieldMap Map<String, String> fields);


    @DELETE
    Flowable<ResponseBody> deleteFlowableResponseBodyRequest(@Url String url);

    @HTTP(method = "DELETE", hasBody = true)
    Flowable<ResponseBody> deleteFlowableResponseBodyRequest(@Url String url, @Body RequestBody post);


    @HTTP(method = "DELETE", hasBody = true)
    Flowable<ResponseBody> deleteFlowableResponseBodyRequest(@FieldMap Map<String, Object> fields, @Url String url);

    @DELETE
    Flowable<ResponseBody> deleteFlowableResponseBodyRequest(@Url String url, @QueryMap Map<String, String> params);

    @DELETE
    Flowable<ResponseBody> deleteFlowableResponseBodyRequest(@Url String url, @Part MultipartBody.Part file);


    @HTTP(method = "DELETE", hasBody = true)
    Flowable<ResponseBody> deleteFlowableResponseBodyRequest(@Url String url, @QueryMap Map<String, String> params, @Body RequestBody post);

    @HTTP(method = "DELETE", hasBody = true)
    Flowable<ResponseBody> deleteFlowableResponseBodyRequest(@Url String url, @QueryMap Map<String, String> params, @FieldMap Map<String, String> fields);

    @Multipart
    @DELETE
    Flowable<ResponseBody> deleteFlowableResponseBodyRequest(@Url String url, @QueryMap Map<String, String> params, @Body RequestBody post, @Part MultipartBody.Part file);



    @DELETE
    Maybe<ResponseBody> deleteMaybeResponseBodyRequest(@Url String url);

    @HTTP(method = "DELETE", hasBody = true)
    Maybe<ResponseBody> deleteMaybeResponseBodyRequest(@Url String url, @Body RequestBody post);


    @DELETE
    Maybe<ResponseBody> deleteMaybeResponseBodyRequest(@Url String url, @Part MultipartBody.Part file);

    @DELETE
    Maybe<ResponseBody> deleteMaybeResponseBodyRequest(@Url String url, @QueryMap Map<String, String> params, @Body RequestBody post, @Part MultipartBody.Part file);


    @HTTP(method = "DELETE", hasBody = true)
    Maybe<ResponseBody> deleteMaybeResponseBodyRequest(@FieldMap Map<String, Object> fields, @Url String url);

    @DELETE
    Maybe<ResponseBody> deleteMaybeResponseBodyRequest(@Url String url, @QueryMap Map<String, String> params);

    @HTTP(method = "DELETE", hasBody = true)
    Maybe<ResponseBody> deleteMaybeResponseBodyRequest(@Url String url, @QueryMap Map<String, String> params, @Body RequestBody post);

    @HTTP(method = "DELETE", hasBody = true)
    Maybe<ResponseBody> deleteMaybeResponseBodyRequest(@Url String url, @QueryMap Map<String, String> params, @FieldMap Map<String, String> fields);


    @DELETE
    Observable<ResponseBody> deleteObservableResponseBodyRequest(@Url String url);

    @HTTP(method = "DELETE", hasBody = true)
    Observable<ResponseBody> deleteObservableResponseBodyRequest(@Url String url, @Body RequestBody post);

    @HTTP(method = "DELETE", hasBody = true)
    Observable<ResponseBody> deleteObservableResponseBodyRequest(@FieldMap Map<String, Object> fields, @Url String url);

    @DELETE
    Observable<ResponseBody> deleteObservableResponseBodyRequest(@Url String url, @QueryMap Map<String, String> params);

    @HTTP(method = "DELETE", hasBody = true)
    Observable<ResponseBody> deleteObservableResponseBodyRequest(@Url String url, @QueryMap Map<String, String> params, @Body RequestBody post);

    @HTTP(method = "DELETE", hasBody = true)
    Observable<ResponseBody> deleteObservableResponseBodyRequest(@Url String url, @QueryMap Map<String, String> params, @FieldMap Map<String, String> fields);



    /*
    @Multipart
    @POST("add_reports")
    Single<InvestigationReportImageResponseModel> getInvestReportImg(@Header("token") String auth,
                                                                     @Part MultipartBody.Part image,
                                                                     @Part("appointment_id") RequestBody appId,
                                                                     @Part("title")   RequestBody title,
                                                                     @Part("description") RequestBody desc,
                                                                     @Part("date")   RequestBody date,
                                                                     @Part("tags") RequestBody tags,
                                                                     @Part("tag_id")   RequestBody tag_id);
*/
}
