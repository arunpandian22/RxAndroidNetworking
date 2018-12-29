package me.arun.andoridrxnetworking.sampleRequest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toolbar;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.processors.PublishProcessor;
import me.arun.andoridrxnetworking.R;
import me.arun.andoridrxnetworking.resModel.nowplaying.ModelNowPlayingMovie;
import me.arun.andoridrxnetworking.resModel.nowplaying.Result;
import me.arun.andoridrxnetworking.utils.FeatureCategory;
import me.arun.androidrxnetworking.NetworkingApiClient;
import me.arun.androidrxnetworking.ObservableType;
import me.arun.androidrxnetworking.RequestType;
import me.arun.androidrxnetworking.RxNetworkRequest;

public class SampleRequestActivity extends AppCompatActivity {
    String TAG = "SampleRequestActivity";
    PublishProcessor<ModelNowPlayingMovie> ppMovieRes = PublishProcessor.create();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rvMovieList)
    RecyclerView rvMovieList;
    MovieListAdapter movieListAdapter;
    List<Result> movieList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_request);
        ButterKnife.bind(this);
        toolbar.setTitle(FeatureCategory.SAMPLE_REQUEST);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAbTitle));
        setActionBar(toolbar);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(this,2);
        NetworkingApiClient.setBaseUrl("https://api.themoviedb.org/3/movie/");
        setPpMovieResObserver();
        movieListAdapter=new MovieListAdapter(this,movieList);
        rvMovieList.setLayoutManager(layoutManager);
        rvMovieList.setAdapter(movieListAdapter);
        makeMovieListApiCall(0);
    }


    @SuppressWarnings("unused")
    public void makeMovieListApiCall(int page) {
        HashMap<String, String> hmParams = new HashMap<>();
        hmParams.put("api_key", "8a03975d504c762ab63b6c5fa98e3c17");
        RxNetworkRequest<ModelNowPlayingMovie> rxNetworkRequest = new RxNetworkRequest.RxNetworkRequestBuilder<ModelNowPlayingMovie>("now_playing", ObservableType.FLOWABLE, RequestType.POST, ModelNowPlayingMovie.class).setQueryParams(hmParams).build();
        rxNetworkRequest.makeRequest(null, ppMovieRes);

    }

    public void setPpMovieResObserver() {
        ppMovieRes.onBackpressureBuffer().subscribe(new Subscriber<ModelNowPlayingMovie>() {
            @Override
            public void onSubscribe(Subscription s) {
                Log.d(TAG, "onSubscribe: ");
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(ModelNowPlayingMovie modelNowPlayingMovie) {
                Log.d(TAG, "onNext: " + modelNowPlayingMovie.getResults().get(0).getOriginalTitle());
                movieList=modelNowPlayingMovie.getResults();
                if (movieList!=null)
                    movieListAdapter.setListMovies(movieList);
            }


            @Override
            public void onError(Throwable t) {
                Log.d(TAG, "onError: " + t.getLocalizedMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        });
    }

}
