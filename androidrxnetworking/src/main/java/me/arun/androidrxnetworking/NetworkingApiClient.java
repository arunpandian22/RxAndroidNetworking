package me.arun.androidrxnetworking;

import android.content.Context;
import android.text.TextUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Arun Pandian M on 20/December/2018
 * arunsachin222@gmail.com
 * Chennai
 */
public class NetworkingApiClient {

    private static String BASE_URL = "";
    private static Map<String, String> default_headers = new HashMap<>();
    private static Map<String, String> commonHeader = new HashMap<>();

    private static OkHttpClient client;
    private static Retrofit retrofit;
    public static final int MAX_CACHE_SIZE = 10 * 1024 * 1024;
    public static final String CACHE_DIR_NAME = "cache_an";


    /**
     * a method to set the default retrofit client for the given base url
     *
     * @param baseUrl a base url of the network call
     */
    public static void setClient(String baseUrl) {

        client = getDefaultClient();
        if (client != null) {
            retrofit = getRetrofitInstance(baseUrl, client);

        }
    }


    public static Retrofit getRetrofitWithHeaders(HashMap<String, String> commonHeaders)
    {

       return getRetrofitInstance( BASE_URL,get_HTTPClient(commonHeaders));

    }


    public static void setCommonHeaders(HashMap<String, String> commonHeaders) {
        if (commonHeaders != null)
            commonHeader = commonHeaders;
    }

    private static OkHttpClient get_HTTPClient(final Map<String, String> headers) {
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                // Request customization: add request headers

                Request.Builder requestBuilder = original.newBuilder(); // <-- this is the important line

                for (Map.Entry<String, String> pairs : headers.entrySet()) {
                    if (pairs.getValue() != null) {
                        requestBuilder.header(pairs.getKey(), pairs.getValue());
                    }
                }

                requestBuilder.method(original.method(), original.body());
                Request request = requestBuilder.build();

                return chain.proceed(request);

            }
        });

        return httpClient.build();

    }

    /**
     * a method to set the client with cache
     *
     * @param baseUrl a baseUrl of the Network Call
     * @param context a context of the app
     */
    public static void setClientWithCache(String baseUrl, Context context) {
        if (commonHeader != null)
            get_HTTPClient(commonHeader);
        else
            setClientWithCache(context, CACHE_DIR_NAME);
        getRetrofitInstance(baseUrl, client);
    }

    public void setBaseUrl(String baseUrl) {
        BASE_URL = baseUrl;
    }

    public void setCustomClient(OkHttpClient okHttpClient) {
        getRetrofitInstance(BASE_URL, okHttpClient);
    }

    /**
     * A method to get the Retrofit client
     *
     * @return it returns the retrofit client
     */
    public static Retrofit getRetrofitClient() {
        if (retrofit == null) {
            setClient(BASE_URL);
        }
        return retrofit;
    }

    /**
     * a method to get retrofit instance
     *
     * @param baseUrl      a base url of the api call
     * @param okHttpClient a ok http client of the
     * @return
     */
    public static Retrofit getRetrofitInstance(String baseUrl, OkHttpClient okHttpClient) {
        if (TextUtils.isEmpty(baseUrl))
            throw new NullPointerException("base url is empty or null");


        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl).client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    /**
     * A method to make http client with default value
     *
     * @return it return the http client with default value
     */
    public static OkHttpClient getDefaultClient() {

        return new OkHttpClient().newBuilder()
                .followSslRedirects(true)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    /**
     * A method to set Htpp client with cache
     *
     * @param context       context of the app
     * @param cacheFileName a name of the cache file
     */
    public static void setClientWithCache(Context context, String cacheFileName)
    {
        client = new OkHttpClient().newBuilder()
                .cache(new Cache(new File(context.getCacheDir(), cacheFileName), MAX_CACHE_SIZE))
                .followSslRedirects(true)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
    }

}
