package me.arun.andoridrxnetworking;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import java.io.File;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import me.arun.androidrxnetworking.NetworkingApiClient;
import me.arun.androidrxnetworking.ObservableType;
import me.arun.androidrxnetworking.ResponseType;
import me.arun.androidrxnetworking.RxNetworkRequest;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.Okio;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    Retrofit retrofit;
    String TAG = "MainActivity";
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    @BindView(R.id.ivCheck)
    ImageView ivCheck;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        NetworkingApiClient.setClient("http://79.127.126.110/");

        retrofit = NetworkingApiClient.getRetrofitClient();
        RxNetworkRequest<Bitmap> rxNetworkRequest = new RxNetworkRequest.RxNetworkRequestBuilder("check", ObservableType.SINGLE, ResponseType.JSON_OBJECT).build();
        Log.d(TAG, "onCreate: " + retrofit);

        compositeDisposable.add(rxNetworkRequest.getImageFullUrl("Serial/Death%20Note/S01/Death.Note.S01E01.mkv").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<ResponseBody>(){

            @Override
            public void onSuccess(ResponseBody responseBody) {
//             ivCheck.setImageBitmap(bitmap);
                String name="Death.Note.S01E01.mkv";
                final File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), name);
                Log.d(TAG, "onSuccess: "+file.getAbsolutePath());
                if (file.exists()){
                    try {
                        BufferedSink sink = Okio.buffer(Okio.sink(file));
                        sink.writeAll(responseBody.source());
                        sink.flush();
                        sink.close();
                    }catch (Exception e){
                        Log.d(TAG, "onSuccess: exception"+e.getStackTrace()[0]);
                    }

                }

                Log.d(TAG, "onSuccess: "+responseBody.contentType());
                Log.d(TAG, "onSuccess: "+responseBody.contentLength());
                Log.d(TAG, "onSuccess: "+responseBody.byteStream().toString());
                Bitmap bitmap = BitmapFactory.decodeStream(responseBody.byteStream());
                ivCheck.setImageBitmap(bitmap);


            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: "+e.getMessage());
            }
        }));
    }
}
