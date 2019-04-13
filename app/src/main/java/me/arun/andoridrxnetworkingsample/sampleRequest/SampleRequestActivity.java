package me.arun.andoridrxnetworkingsample.sampleRequest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
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
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.processors.PublishProcessor;
import me.arun.andoridrxnetworkingsample.R;
import me.arun.andoridrxnetworkingsample.resModel.nowplaying.ModelNowPlayingMovie;
import me.arun.andoridrxnetworkingsample.resModel.nowplaying.Result;
import me.arun.andoridrxnetworkingsample.utils.FeatureCategory;
import me.arun.androidrxnetworking.Network_check;
import me.arun.androidrxnetworking.NetworkingApiClient;
import me.arun.androidrxnetworking.ObservableType;
import me.arun.androidrxnetworking.RequestType;
import me.arun.androidrxnetworking.RxNetworkRequest;
public class SampleRequestActivity extends AppCompatActivity
{
    String TAG = "SampleRequestActivity";
    PublishProcessor<ModelNowPlayingMovie> ppMovieRes = PublishProcessor.create();
    PublishProcessor<Integer> ppPagination=PublishProcessor.create();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rvMovieList)
    RecyclerView rvMovieList;
    MovieListAdapter movieListAdapter;
    List<Result> movieList=new ArrayList<>();
    private boolean loading = false;
    private int pageNumber = 1;
    private final int VISIBLE_THRESHOLD = 1;
    private int lastVisibleItem, totalItemCount;
    private LinearLayoutManager layoutManager;
    CompositeDisposable compositeDisposable=new CompositeDisposable();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_request);
        ButterKnife.bind(this);
        toolbar.setTitle(FeatureCategory.SAMPLE_REQUEST);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAbTitle));
        setActionBar(toolbar);
//        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(this,2);
       layoutManager=new LinearLayoutManager(this);
        NetworkingApiClient.setBaseUrl("https://api.themoviedb.org/3/movie/");
        setPpMovieResObserver();
        movieListAdapter=new MovieListAdapter(this,movieList);
        rvMovieList.setLayoutManager(layoutManager);
        rvMovieList.setAdapter(movieListAdapter);
        setUpLoadMoreListener();
        if (Network_check.isNetworkAvailable(this))
        makeMovieListApiCall(0);
        else
        {
           // show error
        }
    }


    public void makeMovieListApiCall(int page)
    {
        HashMap<String, String> hmParams = new HashMap<>();
        hmParams.put("api_key", "8a03975d504c762ab63b6c5fa98e3c17");
        RxNetworkRequest<ModelNowPlayingMovie> rxNetworkRequest = new RxNetworkRequest.RxNetworkRequestBuilder<ModelNowPlayingMovie>(this,"now_playing", ObservableType.FLOWABLE, RequestType.POST, ModelNowPlayingMovie.class).setQueryParams(hmParams).build();
        rxNetworkRequest.makeRequest(null, ppMovieRes,"");
    }

    public void setPpMovieResObserver()
    {
        ppMovieRes.onBackpressureBuffer().subscribe(new Subscriber<ModelNowPlayingMovie>()
        {
            @Override
            public void onSubscribe(Subscription s)
            {
                Log.d(TAG, "onSubscribe: ");
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(ModelNowPlayingMovie modelNowPlayingMovie)
            {
                Log.d(TAG, "onNext: " + modelNowPlayingMovie.getResults().get(0).getOriginalTitle());
                movieList=modelNowPlayingMovie.getResults();
                loading=false;
                if (movieList!=null) {
//                    movieListAdapter.setListMovies(movieList);
                    movieListAdapter.addMovies(movieList);
                }
            }
            @Override
            public void onError(Throwable t) {
                Log.d(TAG, "onError: " + t.getLocalizedMessage());
                Log.d(TAG, "onError: cause: "+t.getCause());
                loading=false;
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        });
    }

    /**
     * setting listener to get callback for load more
     */
    private void setUpLoadMoreListener() {
        rvMovieList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView,
                                   int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = layoutManager.getItemCount();
                lastVisibleItem = layoutManager
                        .findLastVisibleItemPosition();
                if (!loading
                        && totalItemCount <= (lastVisibleItem + VISIBLE_THRESHOLD)) {
                    pageNumber++;
//                    ppPagination.onNext(pageNumber);
                    makeMovieListApiCall(pageNumber);
                    loading = true;
                }
            }
        });
    }



}
