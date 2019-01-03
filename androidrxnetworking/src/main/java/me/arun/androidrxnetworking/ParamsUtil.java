package me.arun.androidrxnetworking;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ParamsUtil
{

    public static RequestBody getRequestBodyFromCustomObject(Object purchasePayload)
    {
        Gson gson =new GsonBuilder().create();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), gson.toJson(purchasePayload));
        return requestBody;
    }


    public static String jsonStringParam(Context context, String appID, String deviceID)
    {
        HashMap obj = new HashMap();
        obj.put("param1", appID);
        obj.put("param2", deviceID);
        Gson gson=new Gson();
        return gson.toJson(obj);
    }

    public static void getImageMultiPart(File file){
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);

    }


    public static RequestBody getGraphQlString(String query)
    {
        //QueryString.listQuery("1")
       return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),query);

    }







}
