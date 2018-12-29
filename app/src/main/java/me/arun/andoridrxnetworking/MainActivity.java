package me.arun.andoridrxnetworking;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toolbar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import me.arun.andoridrxnetworking.adapter.FeatureCategoryAdapter;
import me.arun.andoridrxnetworking.model.ModelFeatureCategory;
import me.arun.andoridrxnetworking.resModel.ModelPatientSearch;
import me.arun.andoridrxnetworking.utils.FeatureCategory;
import me.arun.andoridrxnetworking.utils.Imageutils;
import me.arun.androidrxnetworking.NetworkingApiClient;
import me.arun.androidrxnetworking.ObservableType;
import me.arun.androidrxnetworking.RequestType;
import me.arun.androidrxnetworking.ResponseType;
import me.arun.androidrxnetworking.RxNetworkRequest;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
public class MainActivity extends AppCompatActivity  {
    Retrofit retrofit;
    String TAG = "MainActivity";
    PublishSubject<ModelPatientSearch> source = PublishSubject.create();
    PublishProcessor<ResponseBody> page = PublishProcessor.create();
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    List<ModelFeatureCategory> modelFeatureCategories = new ArrayList<>();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    FeatureCategoryAdapter featureCategoryAdapter;
    @BindView(R.id.rvFeatureCategory)
    RecyclerView rvFeatureCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbarSetup();
        setFeatureCategoryList();

        NetworkingApiClient.setClient("https://dev.slashdr.com/v2/");
        featureCategoryAdapter = new FeatureCategoryAdapter(this, modelFeatureCategories);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(this,2);
        rvFeatureCategory.setLayoutManager(layoutManager);
        rvFeatureCategory.setAdapter(featureCategoryAdapter);
        retrofit = NetworkingApiClient.getRetrofitClient();
        Map<String, String> hmHearders = new HashMap<>();
        hmHearders.put("token", "eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImZkZUBuZm4iLCJuYW1lIjoiUmF0aGlzaCIsImV4cCI6MTU0ODM5MjcyOX0.-8NDqqVD9bSYle1gekL46N8xp42rdf1q1GV8wFu1Tt4");
        RxNetworkRequest<ModelPatientSearch> rxNetworkRequest = new RxNetworkRequest.RxNetworkRequestBuilder("person_search", ObservableType.SINGLE,RequestType.POST, ModelPatientSearch.class).setHeaderParams(hmHearders).build();
        Log.d(TAG, "onCreate: " + retrofit);
        Map<String, String> hmParams = new HashMap<>();
        hmParams.put("search", "a");
        hmParams.put("page", "1");
        source();
        rxNetworkRequest.makeSingleRequest(source, hmParams);


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
            public void onSubscribe(Disposable d)
            {
                Log.d(TAG, "onSubscribe: " + d);
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(ModelPatientSearch modelPatientSearch)
            {
                Log.d(TAG, "onNext: " + modelPatientSearch);
            }

            @Override
            public void onError(Throwable e)
            {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onComplete()
            {
                Log.d(TAG, "onComplete: ");
            }
        });
    }


    public void setFeatureCategoryList()
    {
        ModelFeatureCategory sampleRequest = new ModelFeatureCategory("@drawable/ic_network_request", FeatureCategory.SAMPLE_REQUEST);
        modelFeatureCategories.add(sampleRequest);
        ModelFeatureCategory fileUpload = new ModelFeatureCategory("@drawable/ic_file_upload", FeatureCategory.FILE_UPLOAD);
        modelFeatureCategories.add(fileUpload);
        ModelFeatureCategory fileDownLoad = new ModelFeatureCategory("@drawable/ic_file_download",FeatureCategory.FILE_DOWNLOAD);
        modelFeatureCategories.add(fileDownLoad);
        ModelFeatureCategory graphQl = new ModelFeatureCategory("@drawable/ic_graphql",FeatureCategory.GRAPH_QL);
        modelFeatureCategories.add(graphQl);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        compositeDisposable.dispose();
    }

    public void toolbarSetup(){
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAbTitle));
        setActionBar(toolbar);
    }


}
