package me.arun.androidrxnetworking;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * A method has the basic functionalities for the API Call params
 */
public class ParamsUtil
{

    /**
     * A method to get the RequestBody from the given object
     * @param purchasePayload param has the object which has to be changed as REquestBody
     * @return it returns the  RequestBody object from the given object
     */
    public static RequestBody getRequestBodyFromCustomObject(Object purchasePayload)
    {
        Gson gson =new GsonBuilder().create();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), gson.toJson(purchasePayload));
        return requestBody;
    }


    /**
     * A method to return as the JSON String for the given Data
     * @param context  a context of the current window
     * @param appID a param for the appid
     * @param deviceID a param has the deviceid
     * @return
     */
    public static String jsonStringParam(Context context, String appID, String deviceID)
    {
        HashMap obj = new HashMap();
        obj.put("param1", appID);
        obj.put("param2", deviceID);
        Gson gson=new Gson();
        return gson.toJson(obj);
    }

    /**
     * A method to get the multipart object for the given file
     * @param file it returns the multipart object for the given file
     */
    public static void getImageMultiPart(File file){
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);

    }


    /**
     * A method to get the Requestbody for the given query for the graphQl API calls
     * @param query the query string for the API call
     * @return it retuns the Requestbody for the given query for the graphQl API calls
     */
    public static RequestBody getGraphQlString(String query)
    {
        //QueryString.listQuery("1")
       return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),query);

    }







}
