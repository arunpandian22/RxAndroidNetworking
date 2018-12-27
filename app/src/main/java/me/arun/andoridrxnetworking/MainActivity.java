package me.arun.andoridrxnetworking;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import me.arun.andoridrxnetworking.resModel.ModelPatientSearch;
import me.arun.androidrxnetworking.NetworkingApiClient;
import me.arun.androidrxnetworking.ObservableType;
import me.arun.androidrxnetworking.ResponseType;
import me.arun.androidrxnetworking.RxNetworkRequest;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.Okio;
import retrofit2.Retrofit;
public class MainActivity extends AppCompatActivity
{
    Retrofit retrofit;
    String TAG = "MainActivity";
    PublishSubject<ModelPatientSearch> source = PublishSubject.create();
    PublishProcessor<ResponseBody> page=PublishProcessor.create();
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    @BindView(R.id.ivCheck)
    ImageView ivCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        NetworkingApiClient.setClient("https://dev.slashdr.com/v2/");
        retrofit = NetworkingApiClient.getRetrofitClient();
        Map<String,String> hmHearders=new HashMap<>();
        hmHearders.put("token","eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImZkZUBuZm4iLCJuYW1lIjoiUmF0aGlzaCIsImV4cCI6MTU0ODM5MjcyOX0.-8NDqqVD9bSYle1gekL46N8xp42rdf1q1GV8wFu1Tt4");
        RxNetworkRequest<ModelPatientSearch> rxNetworkRequest = new RxNetworkRequest.RxNetworkRequestBuilder("person_search", ObservableType.SINGLE, ResponseType.JSON_OBJECT,ModelPatientSearch.class).setHeaderParams(hmHearders).build();
        Log.d(TAG, "onCreate: " + retrofit);
        Map<String,String> hmParams=new HashMap<>();
        hmParams.put("search","a");
        hmParams.put("page","1");
        source();
        rxNetworkRequest.makeSingleRequest(source,hmParams);
//        compositeDisposable.add(rxNetworkRequest.getImageFullUrl("Serial/Death%20Note/S01/Death.Note.S01E01.mkv").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<ResponseBody>(){
//
//            @Override
//            public void onSuccess(ResponseBody responseBody) {
////             ivCheck.setImageBitmap(bitmap);
//                String name="Death.Note.S01E01.mkv";
//                final File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), name);
//                Log.d(TAG, "onSuccess: "+file.getAbsolutePath());
//                if (file.exists())
//                {
//                    try
//                    {
//                        BufferedSink sink = Okio.buffer(Okio.sink(file));
//                        sink.writeAll(responseBody.source());
//                        sink.flush();
//                        sink.close();
//                    }catch (Exception e)
//                    {
//                        Log.d(TAG, "onSuccess: exception"+e.getStackTrace()[0]);
//                    }
//
//                }
//
//                Log.d(TAG, "onSuccess: "+responseBody.contentType());
//                Log.d(TAG, "onSuccess: "+responseBody.contentLength());
//                Log.d(TAG, "onSuccess: "+responseBody.byteStream().toString());
//                Bitmap bitmap = BitmapFactory.decodeStream(responseBody.byteStream());
//                ivCheck.setImageBitmap(bitmap);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d(TAG, "onError: "+e.getMessage());
//            }
//        }));
    }


    public void source()
    {

        source.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new Observer<ModelPatientSearch>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: "+d);
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(ModelPatientSearch modelPatientSearch) {
                Log.d(TAG, "onNext: "+modelPatientSearch);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: "+e);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        });
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        compositeDisposable.dispose();
    }
}
