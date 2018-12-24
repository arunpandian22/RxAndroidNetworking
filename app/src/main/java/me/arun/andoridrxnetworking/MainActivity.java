package me.arun.andoridrxnetworking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import me.arun.androidrxnetworking.NetworkingApiClient;
import me.arun.androidrxnetworking.ObservableType;
import me.arun.androidrxnetworking.ResponseType;
import me.arun.androidrxnetworking.RxNetworkRequest;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity
{
    Retrofit retrofit;
    String TAG="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetworkingApiClient.setClient("https://gaana.com");
        retrofit=NetworkingApiClient.getRetrofitClient();
        RxNetworkRequest rxNetworkRequest=new RxNetworkRequest.RxNetworkRequestBuilder("check",ObservableType.SINGLE,ResponseType.JSON_OBJECT).build();
        Log.d(TAG, "onCreate: "+retrofit);
    }
}
