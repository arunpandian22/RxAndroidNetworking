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
public interface ApiInterfaceService
{
    //Get custom object Request

    //for no params
    @Headers({"Accept: application/json"})
    @GET
    Single<ResponseBody> getSingleObject(@Url String url);

    @Headers({"Accept: application/json"})
    @GET
    Flowable<ResponseBody> getFlowableObject(@Url String url);

    @Headers({"Accept: application/json"})
    @GET
    Maybe<ResponseBody> getMaybeObject(@Url String url);

    @Headers({"Accept: application/json"})
    @GET
    Observable<ResponseBody> getObservable(@Url String url);


    @Headers({"Accept: application/json"})
    @GET
    Single<ResponseBody> getSingleObject(@Url String url, @QueryMap Map<String, String> params);

    @Headers({"Accept: application/json"})
    @GET
    Flowable<ResponseBody> getFlowableObject(@Url String url, @QueryMap Map<String, String> params);

    @Headers({"Accept: application/json"})
    @GET
    Maybe<ResponseBody> getMaybeObject(@Url String url, @QueryMap Map<String, String> params);

    @Headers({"Accept: application/json"})
    @GET
    Observable<ResponseBody> getObservable(@Url String url, @QueryMap Map<String, String> params);

    // for full url from any other website
    @Streaming
    @GET
    Single<ResponseBody> getImageSinglePublicRequest(@Url String url);

    @Streaming
    @GET
    Single<Bitmap> getImageBitMapSingleRequest(@Url String url);

    @Streaming
    @GET
    Flowable<Bitmap> getImageBitMapFlowablRequest(@Url String url);

    @Streaming
    @GET
    Maybe<Bitmap> getImageBitMapMayBeRequest(@Url String url);

    @Streaming
    @GET
    Observable<Bitmap> getImageBitMapObservableRequest(@Url String url);



    // get bitmap with param passing
    @Streaming
    @GET
    Single<Bitmap> getImageBitMapSingleRequest(@Url String url, @QueryMap Map<String, Object> params);

    @Streaming
    @GET
    Maybe<Bitmap> getImageBitMapMaybeRequest(@Url String url, @QueryMap Map<String, Object> params);

    @Streaming
    @GET
    Flowable<Bitmap> getImageBitMapFlowableRequest(@Url String url, @QueryMap Map<String, Object> params);

    @Streaming
    @GET
    Observable<Bitmap> getImageBitMapObservableRequest(@Url String url, @QueryMap Map<String, Object> params);






    //Post okkhttp3 ResponseBody  request

    @Headers({"Accept: application/json"})
    @POST
    Single<ResponseBody> postSingleResponseBodyRequest(@Url String url);





    @Headers({"Accept: application/json"})
    @POST
    Single<ResponseBody> postSingleResponseBodyRequest(@Url String url, @QueryMap Map<String, String> params);

    @Headers({"Accept: application/json"})
    @POST
    Flowable<ResponseBody> postFlowableResponseBodyRequest(@Url String url, @QueryMap Map<String, String> params);

    @Headers({"Accept: application/json"})
    @POST
    Maybe<ResponseBody> postMaybeResponseBodyRequest(@Url String url, @QueryMap Map<String, String> params);

    @Headers({"Accept: application/json"})
    @POST
    Observable<ResponseBody> postObservableResponseBodyRequest(@Url String url, @QueryMap Map<String, String> params);


    //post okhttp3 Response body with multipart and requsetbody request in android

    @Multipart
    @POST
    Single<ResponseBody> postSingleMultipartRequest(@Url String url, @Part MultipartBody.Part file);

    @Multipart
    @POST
    Single<ResponseBody> postSingleMultipartPartParamsRequest(@Url String url, @Part MultipartBody.Part file,@PartMap Map<String,String> partParams);

    @Multipart
    @POST
    Flowable<ResponseBody> postFlowableMultipartRequest(@Url String url, @Part MultipartBody.Part file);


    @Multipart
    @POST
    Flowable<ResponseBody> postFlowableMultipartPartParamsRequest(@Url String url, @Part MultipartBody.Part file,@PartMap Map<String,String> partParams);


    @Multipart
    @POST
    Maybe<ResponseBody> postMaybeMultipartRequest(@Url String url, @Part MultipartBody.Part file);


    @Multipart
    @POST
    Maybe<ResponseBody> postMaybeMultipartPartParamsRequest(@Url String url, @Part MultipartBody.Part file,@PartMap Map<String,String> partParams);

    @Multipart
    @POST
    Observable<ResponseBody> postObservableMultipartRequest(@Url String url, @Part MultipartBody.Part file);

    // post request body
    @POST
    Single<ResponseBody> postRequsetBodySingleRequest(@Url String url, @Body RequestBody post);

    @POST
    Flowable<ResponseBody> postRequsetBodyFlowableRequest(@Url String url, @Body RequestBody post);

    @POST
    Maybe<ResponseBody> postRequsetBodyMaybeRequest(@Url String url, @Body RequestBody post);

    @POST
    Observable<ResponseBody> postRequsetBodyObservableRequest(@Url String url, @Body RequestBody post);


    //Post custom  request


    @Headers({"Accept: application/json"})
    @POST
    Flowable<ResponseBody> postFlowableObjectRequest(@Url String url, @QueryMap Map<String, String> params);

    @Headers({"Accept: application/json"})
    @POST
    Maybe<ResponseBody> postMaybeObjectRequest(@Url String url, @QueryMap Map<String, String> params);



    @Multipart
    @POST
    Single<ResponseBody> postSingleMultipartObjectRequestquest(@Url String url, @QueryMap Map<String, String> params, @Body RequestBody post, @Part MultipartBody.Part file);


    @Multipart
    @POST
    Flowable<ResponseBody> postFlowableMultipartObjectRequest(@Url String url, @Part MultipartBody.Part file);



    @Multipart
    @POST
    Flowable<ResponseBody> postFlowableMultipartObjectRequest(@Url String url, @QueryMap Map<String, String> params, @Body RequestBody post, @Part MultipartBody.Part file);


    @Multipart
    @POST
    Maybe<ResponseBody> postMaybeMultipartObjectRequest(@Url String url, @Part MultipartBody.Part file);


    // post request body
    @POST
    Single<ResponseBody> postSingleObjectRequest(@Url String url, @Body RequestBody post);

    @POST
    Flowable<ResponseBody> postflowableObjectRequest(@Url String url, @Body RequestBody post);

    @POST
    Maybe<ResponseBody> postMaybeObjectRequest(@Url String url, @Body RequestBody post);


    @Multipart
    @POST
    Maybe<ResponseBody> postMaybeMultipartObjectRequest(@Url String url, @QueryMap Map<String, String> params, @Body RequestBody post, @Part MultipartBody.Part file);


    @FormUrlEncoded
    @POST
    Single<ResponseBody> postFormUrlEncodedSingleRequest(@FieldMap Map<String, String> fields, @Url String url);


    @FormUrlEncoded
    @POST
    Flowable<ResponseBody> postFormUrlEncodedFlowableRequest(@FieldMap Map<String, String> fields, @Url String url);


    @FormUrlEncoded
    @POST
    Maybe<ResponseBody> postFormUrlEncodedMaybeRequest(@FieldMap Map<String, String> fields, @Url String url);


    @FormUrlEncoded
    @POST
    Observable<ResponseBody> postFormUrlEncodedObservabRequest(@FieldMap Map<String, String> fields, @Url String url);


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


    @PUT
    Flowable<ResponseBody> putFlowableResponseBodyRequest(@Url String url);

    @PUT
    Flowable<ResponseBody> putFlowableResponseBodyRequest(@Url String url, @Body RequestBody post);

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


    @PUT
    Maybe<ResponseBody> putMaybeResponseBodyRequest(@Url String url);

    @PUT
    Maybe<ResponseBody> putMaybeResponseBodyRequest(@Url String url, @Body RequestBody post);

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

    @DELETE
    Flowable<ResponseBody> deleteFlowableResponseBodyRequest(@Url String url, @QueryMap Map<String, String> params, @Body RequestBody post, @Part MultipartBody.Part file);;



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
