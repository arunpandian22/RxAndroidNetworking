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
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by Arun Pandian M on 21/December/2018
 * arunsachin222@gmail.com
 * Chennai
 */
public interface ApiInterfaceService<T> {
    //Get custom object Request

    @Headers({"Accept: application/json"})
    @GET("{url}")
    Single<T> getSingleObject(@Path("url") String url, @QueryMap Map<String, Object> params);

    @Headers({"Accept: application/json"})
    @GET("{url}")
    Flowable<T> getFlowableObject(@Path("url") String url, @QueryMap Map<String, Object> params);

    @Headers({"Accept: application/json"})
    @GET("{url}")
    Maybe<T> getMaybeObject(@Path("url") String url, @QueryMap Map<String, Object> params);

    @Headers({"Accept: application/json"})
    @GET("{url}")
    Observable<T> getObservable(@Path("url") String url, @QueryMap Map<String, Object> params);


    // Get okhttp3 ResponseBody Object

    @Headers({"Accept: application/json"})
    @GET("{url}")
    Single<ResponseBody> getSingleResponseBody(@Path("url") String url, @QueryMap Map<String, Object> params);

    @Headers({"Accept: application/json"})
    @GET("{url}")
    Flowable<ResponseBody> getFlowableResponseBody(@Path("url") String url, @QueryMap Map<String, Object> params);

    @Headers({"Accept: application/json"})
    @GET("{url}")
    Maybe<ResponseBody> getMaybeResponseBody(@Path("url") String url, @QueryMap Map<String, Object> params);

    @Headers({"Accept: application/json"})
    @GET("{url}")
    Observable<ResponseBody> getObservableResponseBody(@Path("url") String url, @QueryMap Map<String, Object> params);


    //Get Streaming bitmap

    @Streaming
    @GET("{url}")
    Single<Bitmap> getImageBitMapSingleRequest(@Path("url") String url);

    @Streaming
    @GET("{url}")
    Flowable<Bitmap> getImageBitMapFlowablRequest(@Path("url") String url);

    @Streaming
    @GET("{url}")
    Maybe<Bitmap> getImageBitMapMayBeRequest(@Path("url") String url);

    @Streaming
    @GET("{url}")
    Observable<Bitmap> getImageBitMapObservableRequest(@Path("url") String url);



    @Streaming
    @GET("{url}")
    Single<Bitmap> getImageBitMapSingleRequest(@Url String url, @QueryMap Map<String, Object> params);

    @Streaming
    @GET("{url}")
    Maybe<Bitmap> getImageBitMapMaybeRequest(@Url String url, @QueryMap Map<String, Object> params);

    @Streaming
    @GET("{url}")
    Flowable<Bitmap> getImageBitMapFlowableRequest(@Url String url, @QueryMap Map<String, Object> params);

    @Streaming
    @GET("{url}")
    Observable<Bitmap> getImageBitMapObservableRequest(@Url String url, @QueryMap Map<String, Object> params);


    //Postv okkhttp3 ResponseBody  request


    @Headers({"Accept: application/json"})
    @POST("{url}")
    Single<ResponseBody> postSingleResponseBodyRequest(@Path("url") String url, @QueryMap Map<String, Object> params);

    @Headers({"Accept: application/json"})
    @POST("{url}")
    Flowable<ResponseBody> postFlowableResponseBodyRequest(@Path("url") String url, @QueryMap Map<String, Object> params);

    @Headers({"Accept: application/json"})
    @POST("{url}")
    Maybe<ResponseBody> postMaybeResponseBodyRequest(@Path("url") String url, @QueryMap Map<String, Object> params);

    @Headers({"Accept: application/json"})
    @POST("{url}")
    Observable<ResponseBody> postObservableResponseBodyRequest(@Path("url") String url, @QueryMap Map<String, Object> params);


    //post okhttp3 Response body with multipart and requsetbody request in android

    @Multipart
    @POST("{url}")
    Single<ResponseBody> postSingleMultipartRequest(@Path("url") String url, @Part MultipartBody.Part file);

    @Multipart
    @POST("{url}")
    Flowable<ResponseBody> postFlowableMultipartRequest(@Path("url") String url, @Part MultipartBody.Part file);


    @Multipart
    @POST("{url}")
    Maybe<ResponseBody> postMaybeMultipartRequest(@Path("url") String url, @Part MultipartBody.Part file);


    @Multipart
    @POST("{url}")
    Observable<ResponseBody> postObservableMultipartRequest(@Path("url") String url, @Part MultipartBody.Part file);

    // post request body
    @POST("{url}")
    Single<ResponseBody> postRequsetBodySingleRequest(@Path("url") String url, @Body RequestBody post);

    @POST("{url}")
    Flowable<ResponseBody> postRequsetBodyFlowableRequest(@Path("url") String url, @Body RequestBody post);

    @POST("{url}")
    Maybe<ResponseBody> postRequsetBodyMaybeRequest(@Path("url") String url, @Body RequestBody post);

    @POST("{url}")
    Observable<ResponseBody> postRequsetBodyObservableRequest(@Path("url") String url, @Body RequestBody post);


    //Post custom  request

    @Headers({"Accept: application/json"})
    @POST("{url}")
    Single<T> postSingleObjectRequest(@Path("url") String url, @QueryMap Map<String, Object> params);

    @Headers({"Accept: application/json"})
    @POST("{url}")
    Flowable<T> postFlowableObjectRequest(@Path("url") String url, @QueryMap Map<String, Object> params);

    @Headers({"Accept: application/json"})
    @POST("{url}")
    Maybe<T> postMaybeObjectRequest(@Path("url") String url, @QueryMap Map<String, Object> params);

    @Headers({"Accept: application/json"})
    @POST("{url}")
    Observable<T> postObservableRequest(@Path("url") String url, @QueryMap Map<String, Object> params);


    @Multipart
    @POST("{url}")
    Single<T> postSingleMultipartObjectRequest(@Path("url") String url, @Part MultipartBody.Part file);

    @Multipart
    @POST("{url}")
    Single<T> postSingleMultipartObjectRequest(@Path("url") String url, @QueryMap Map<String, String> params, @Part MultipartBody.Part file);


    @Multipart
    @POST("{url}")
    Single<T> postSingleMultipartObjectRequestquest(@Path("url") String url, @QueryMap Map<String, String> params, @Body RequestBody post, @Part MultipartBody.Part file);


    @Multipart
    Flowable<T> postFlowableMultipartObjectRequest(@Path("url") String url, @Part MultipartBody.Part file);


    @Multipart
    @POST("{url}")
    Flowable<T> postFlowableMultipartObjectRequest(@Path("url") String url, @QueryMap Map<String, String> params, @Part MultipartBody.Part file);


    @Multipart
    @POST("{url}")
    Flowable<T> postFlowableMultipartObjectRequest(@Path("url") String url, @QueryMap Map<String, String> params, @Body RequestBody post, @Part MultipartBody.Part file);


    @Multipart
    @POST("{url}")
    Maybe<T> postMaybeMultipartObjectRequest(@Path("url") String url, @Part MultipartBody.Part file);

    @Multipart
    @POST("{url}")
    Maybe<T> postMaybeMultipartObjectRequest(@Path("url") String url, @QueryMap Map<String, String> params, @Part MultipartBody.Part file);


    @Multipart
    @POST("{url}")
    Maybe<T> postMaybeMultipartObjectRequest(@Path("url") String url, @QueryMap Map<String, String> params, @Body RequestBody post, @Part MultipartBody.Part file);


    @Multipart
    Observable<T> postObservableMultipartObjectRequest(@Path("url") String url, @Part MultipartBody.Part file);

    @Multipart
    @POST("{url}")
    Observable<T> postObservableMultipartObjectRequest(@Path("url") String url, @QueryMap Map<String, String> params, @Part MultipartBody.Part file);


    @Multipart
    @POST("{url}")
    Observable<T> postObservableMultipartObjectRequest(@Path("url") String url, @QueryMap Map<String, String> params, @Body RequestBody post, @Part MultipartBody.Part file);


    // post request body
    @POST("{url}")
    Single<T> postSingleObjectRequest(@Path("url") String url, @Body RequestBody post);

    @POST("{url}")
    Flowable<T> postflowableObjectRequest(@Path("url") String url, @Body RequestBody post);

    @POST("{url}")
    Maybe<T> postMaybeObjectRequest(@Path("url") String url, @Body RequestBody post);

    @POST("{url}")
    Observable<T> postObservableObjectRequest(@Path("url") String url, @Body RequestBody post);


    @FormUrlEncoded
    @POST("{url}")
    Single<ResponseBody> postFormUrlEncodedSingleRequest(@FieldMap Map<String, String> fields, @Path("url") String url);


    @FormUrlEncoded
    @POST("{url}")
    Flowable<ResponseBody> postFormUrlEncodedFlowableRequest(@FieldMap Map<String, String> fields, @Path("url") String url);


    @FormUrlEncoded
    @POST("{url}")
    Maybe<ResponseBody> postFormUrlEncodedMaybeRequest(@FieldMap Map<String, String> fields, @Path("url") String url);


    @FormUrlEncoded
    @POST("{url}")
    Observable<ResponseBody> postFormUrlEncodedObservabRequest(@FieldMap Map<String, String> fields, @Path("url") String url);


    // PUT  custom object request
    @PUT("{url}")
    Single<T> putSingleRequest(@Path("url") String url);

    @PUT("{url}")
    Single<T> putSingleRequest(@Path("url") String url, @Body RequestBody post);

    @FormUrlEncoded
    @PUT("{url}")
    Single<T> putSingleRequest(@Path("url") String url, @FieldMap Map<String, String> fields);

    @PUT("{url}")
    Single<T> putSingleRequest(@QueryMap Map<String, String> params, @Path("url") String url);

    @PUT("{url}")
    Single<T> putSingleRequest(@Path("url") String url, @QueryMap Map<String, String> params, @Body RequestBody post);

    @FormUrlEncoded
    @PUT("{url}")
    Single<T> putSingleRequest(@Path("url") String url, @QueryMap Map<String, String> params, @FieldMap Map<String, String> fields);


    @PUT("{url}")
    Flowable<T> putFlowableRequest(@Path("url") String url);

    @PUT("{url}")
    Flowable<T> putFlowableRequest(@Path("url") String url, @Body RequestBody post);

    @FormUrlEncoded
    @PUT("{url}")
    Flowable<T> putFlowableRequest(@Path("url") String url, @FieldMap Map<String, String> fields);

    @PUT("{url}")
    Flowable<T> putFlowableRequest(@QueryMap Map<String, String> params, @Path("url") String url);

    @PUT("{url}")
    Flowable<T> putFlowableRequest(@Path("url") String url, @QueryMap Map<String, String> params, @Body RequestBody post);

    @FormUrlEncoded
    @PUT("{url}")
    Flowable<T> putFlowableRequest(@Path("url") String url, @QueryMap Map<String, String> params, @FieldMap Map<String, String> fields);


    @PUT("{url}")
    Maybe<T> putMaybeRequest(@Path("url") String url);

    @PUT("{url}")
    Maybe<T> putMaybeRequest(@Path("url") String url, @Body RequestBody post);

    @FormUrlEncoded
    @PUT("{url}")
    Maybe<T> putMaybeRequest(@Path("url") String url, @FieldMap Map<String, String> fields);

    @PUT("{url}")
    Maybe<T> putMaybeRequest(@QueryMap Map<String, String> params, @Path("url") String url);

    @PUT("{url}")
    Maybe<T> putMaybeRequest(@Path("url") String url, @QueryMap Map<String, String> params, @Body RequestBody post);

    @FormUrlEncoded
    @PUT("{url}")
    Maybe<T> putMaybeRequest(@Path("url") String url, @QueryMap Map<String, String> params, @FieldMap Map<String, String> fields);


    @PUT("{url}")
    Observable<T> putObservableRequest(@Path("url") String url);

    @PUT("{url}")
    Observable<T> putObservableRequest(@Path("url") String url, @Body RequestBody post);

    @FormUrlEncoded
    @PUT("{url}")
    Observable<T> putObservableRequest(@Path("url") String url, @FieldMap Map<String, String> fields);

    @PUT("{url}")
    Observable<T> putObservableRequest(@QueryMap Map<String, String> params, @Path("url") String url);

    @PUT("{url}")
    Observable<T> putObservableRequest(@Path("url") String url, @QueryMap Map<String, String> params, @Body RequestBody post);

    @FormUrlEncoded
    @PUT("{url}")
    Observable<T> putObservableRequest(@Path("url") String url, @QueryMap Map<String, String> params, @FieldMap Map<String, String> fields);


    @PUT("{url}")
    Single<ResponseBody> putSingleResponseBodyRequest(@Path("url") String url);

    @PUT("{url}")
    Single<ResponseBody> putSingleResponseBodyRequest(@Path("url") String url, @Body RequestBody post);

    @FormUrlEncoded
    @PUT("{url}")
    Single<ResponseBody> putSingleResponseBodyRequest(@Path("url") String url, @FieldMap Map<String, String> fields);

    @PUT("{url}")
    Single<ResponseBody> putSingleResponseBodyRequest(@QueryMap Map<String, String> params, @Path("url") String url);

    @PUT("{url}")
    Single<ResponseBody> putSingleResponseBodyRequest(@Path("url") String url, @QueryMap Map<String, String> params, @Body RequestBody post);

    @FormUrlEncoded
    @PUT("{url}")
    Single<ResponseBody> putSingleResponseBodyRequest(@Path("url") String url, @QueryMap Map<String, String> params, @FieldMap Map<String, String> fields);


    @PUT("{url}")
    Flowable<ResponseBody> putFlowableResponseBodyRequest(@Path("url") String url);

    @PUT("{url}")
    Flowable<ResponseBody> putFlowableResponseBodyRequest(@Path("url") String url, @Body RequestBody post);

    @FormUrlEncoded
    @PUT("{url}")
    Flowable<ResponseBody> putFlowableResponseBodyRequest(@Path("url") String url, @FieldMap Map<String, String> fields);

    @PUT("{url}")
    Flowable<ResponseBody> putFlowableResponseBodyRequest(@QueryMap Map<String, String> params, @Path("url") String url);

    @PUT("{url}")
    Flowable<ResponseBody> putFlowableResponseBodyRequest(@Path("url") String url, @QueryMap Map<String, String> params, @Body RequestBody post);

    @FormUrlEncoded
    @PUT("{url}")
    Flowable<ResponseBody> putFlowableResponseBodyRequest(@Path("url") String url, @QueryMap Map<String, String> params, @FieldMap Map<String, String> fields);


    @PUT("{url}")
    Maybe<ResponseBody> putMaybeResponseBodyRequest(@Path("url") String url);

    @PUT("{url}")
    Maybe<ResponseBody> putMaybeResponseBodyRequest(@Path("url") String url, @Body RequestBody post);

    @FormUrlEncoded
    @PUT("{url}")
    Maybe<ResponseBody> putMaybeResponseBodyRequest(@Path("url") String url, @FieldMap Map<String, String> fields);

    @PUT("{url}")
    Maybe<ResponseBody> putMaybeResponseBodyRequest(@QueryMap Map<String, String> params, @Path("url") String url);

    @PUT("{url}")
    Maybe<ResponseBody> putMaybeResponseBodyRequest(@Path("url") String url, @QueryMap Map<String, String> params, @Body RequestBody post);

    @FormUrlEncoded
    @PUT("{url}")
    Maybe<ResponseBody> putMaybeResponseBodyRequest(@Path("url") String url, @QueryMap Map<String, String> params, @FieldMap Map<String, String> fields);


    @PUT("{url}")
    Observable<ResponseBody> putObservableResponseBodyRequest(@Path("url") String url);

    @PUT("{url}")
    Observable<ResponseBody> putObservableResponseBodyRequest(@Path("url") String url, @Body RequestBody post);

    @FormUrlEncoded
    @PUT("{url}")
    Observable<ResponseBody> putObservableResponseBodyRequest(@Path("url") String url, @FieldMap Map<String, String> fields);

    @PUT("{url}")
    Observable<ResponseBody> putObservableResponseBodyRequest(@QueryMap Map<String, String> params, @Path("url") String url);

    @PUT("{url}")
    Observable<ResponseBody> putObservableResponseBodyRequest(@Path("url") String url, @QueryMap Map<String, String> params, @Body RequestBody post);

    @FormUrlEncoded
    @PUT("{url}")
    Observable<ResponseBody> putObservableResponseBodyRequest(@Path("url") String url, @QueryMap Map<String, String> params, @FieldMap Map<String, String> fields);


    // DELETE request

    @DELETE
    Single<T> deleteSingleRequest(@Url String url);

    @HTTP(method = "DELETE", hasBody = true)
    Single<T> deleteSingleRequest(@Url String url, @Body RequestBody post);


    @HTTP(method = "DELETE", hasBody = true)
    Single<T> deleteSingleRequest(@FieldMap Map<String, Object> fields, @Url String url);

    @DELETE
    Single<T> deleteSingleRequest(@Url String url, @QueryMap Map<String, String> params);

    @HTTP(method = "DELETE", hasBody = true)
    Single<T> deleteSingleRequest(@Url String url, @QueryMap Map<String, String> params, @Body RequestBody post);

    @HTTP(method = "DELETE", hasBody = true)
    Single<T> deleteSingleRequest(@Url String url, @QueryMap Map<String, String> params, @FieldMap Map<String, String> fields);


    @DELETE
    Flowable<T> deleteFlowableRequest(@Url String url);

    @HTTP(method = "DELETE", hasBody = true)
    Flowable<T> deleteFlowableRequest(@Url String url, @Body RequestBody post);


    @HTTP(method = "DELETE", hasBody = true)
    Flowable<T> deleteFlowableRequest(@FieldMap Map<String, Object> fields, @Url String url);

    @DELETE
    Flowable<T> deleteFlowableRequest(@Url String url, @QueryMap Map<String, String> params);

    @HTTP(method = "DELETE", hasBody = true)
    Flowable<T> deleteFlowableRequest(@Url String url, @QueryMap Map<String, String> params, @Body RequestBody post);

    @HTTP(method = "DELETE", hasBody = true)
    Flowable<T> deleteFlowableRequest(@Url String url, @QueryMap Map<String, String> params, @FieldMap Map<String, String> fields);


    @DELETE
    Maybe<T> deleteMaybeRequest(@Url String url);

    @HTTP(method = "DELETE", hasBody = true)
    Maybe<T> deleteMaybeRequest(@Url String url, @Body RequestBody post);


    @HTTP(method = "DELETE", hasBody = true)
    Maybe<T> deleteMaybeRequest(@FieldMap Map<String, Object> fields, @Url String url);

    @DELETE
    Maybe<T> deleteMaybeRequest(@Url String url, @QueryMap Map<String, String> params);

    @HTTP(method = "DELETE", hasBody = true)
    Maybe<T> deleteMaybeRequest(@Url String url, @QueryMap Map<String, String> params, @Body RequestBody post);

    @HTTP(method = "DELETE", hasBody = true)
    Maybe<T> deleteMaybeRequest(@Url String url, @QueryMap Map<String, String> params, @FieldMap Map<String, String> fields);


    @DELETE
    Observable<T> deleteObservableRequest(@Url String url);

    @HTTP(method = "DELETE", hasBody = true)
    Observable<T> deleteObservableRequest(@Url String url, @Body RequestBody post);

    @HTTP(method = "DELETE", hasBody = true)
    Observable<T> deleteObservableRequest(@FieldMap Map<String, Object> fields, @Url String url);

    @DELETE
    Observable<T> deleteObservableRequest(@Url String url, @QueryMap Map<String, String> params);

    @HTTP(method = "DELETE", hasBody = true)
    Observable<T> deleteObservableRequest(@Url String url, @QueryMap Map<String, String> params, @Body RequestBody post);

    @HTTP(method = "DELETE", hasBody = true)
    Observable<T> deleteObservableRequest(@Url String url, @QueryMap Map<String, String> params, @FieldMap Map<String, String> fields);


    // DELETE request

    @DELETE
    Single<ResponseBody> deleteSingleResponseBodyRequest(@Url String url);

    @HTTP(method = "DELETE", hasBody = true)
    Single<ResponseBody> deleteSingleResponseBodyRequest(@Url String url, @Body RequestBody post);


    @HTTP(method = "DELETE", hasBody = true)
    Single<ResponseBody> deleteSingleResponseBodyRequest(@FieldMap Map<String, Object> fields, @Url String url);

    @DELETE
    Single<ResponseBody> deleteSingleResponseBodyRequest(@Url String url, @QueryMap Map<String, String> params);

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

    @HTTP(method = "DELETE", hasBody = true)
    Flowable<ResponseBody> deleteFlowableResponseBodyRequest(@Url String url, @QueryMap Map<String, String> params, @Body RequestBody post);

    @HTTP(method = "DELETE", hasBody = true)
    Flowable<ResponseBody> deleteFlowableResponseBodyRequest(@Url String url, @QueryMap Map<String, String> params, @FieldMap Map<String, String> fields);


    @DELETE
    Maybe<ResponseBody> deleteMaybeResponseBodyRequest(@Url String url);

    @HTTP(method = "DELETE", hasBody = true)
    Maybe<ResponseBody> deleteMaybeResponseBodyRequest(@Url String url, @Body RequestBody post);


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


}
