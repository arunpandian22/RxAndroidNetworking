package me.arun.andoridrxnetworking.graphQlRequest;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.constraint.Guideline;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import me.arun.andoridrxnetworking.R;
import me.arun.andoridrxnetworking.resModel.country.Country;
import me.arun.andoridrxnetworking.resModel.country.Language;
import me.arun.andoridrxnetworking.resModel.country.ModelCountry;
import me.arun.andoridrxnetworking.utils.FeatureCategory;
import me.arun.andoridrxnetworking.utils.QueryString;
import me.arun.androidrxnetworking.Network_check;
import me.arun.androidrxnetworking.NetworkingApiClient;
import me.arun.androidrxnetworking.ObservableType;
import me.arun.androidrxnetworking.ParamsUtil;
import me.arun.androidrxnetworking.RequestType;
import me.arun.androidrxnetworking.RxNetworkRequest;
import okhttp3.RequestBody;

public class GraphQlActivity extends AppCompatActivity {
    PublishSubject<ModelCountry> sourceGraphQl = PublishSubject.create();
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    String TAG = "GraphQlActivity";
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.viewBottom)
    View viewBottom;
    @BindView(R.id.tvCurrencyLabel)
    TextView tvCurrencyLabel;
    @BindView(R.id.tvFollowingLabel)
    TextView tvFollowingLabel;
    @BindView(R.id.tvLanguagesLabel)
    TextView tvLanguagesLabel;
    @BindView(R.id.tvLanguages)
    TextView tvLanguages;
    @BindView(R.id.tvCurrency)
    TextView tvCurrency;
    @BindView(R.id.tvEmoji)
    TextView tvEmoji;
    @BindView(R.id.guidelineTop)
    Guideline guidelineTop;
    @BindView(R.id.guidelineBottom)
    Guideline guidelineBottom;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_ql);
        ButterKnife.bind(this);
        toolbar.setTitle(FeatureCategory.GRAPH_QL);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAbTitle));
        setActionBar(toolbar);
        source();
        apiCallSetup();
    }

    public void apiCallSetup() {
        //creating request body for file
        NetworkingApiClient.setBaseUrl("https://countries.trevorblades.com/");
//        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        RequestBody requestBody = ParamsUtil.getGraphQlString(QueryString.countryQuery());
//        Map<String, String> hmHearders = new HashMap<>();
//        hmHearders.put("token", "eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImZkZUBuZm4iLCJuYW1lIjoiUmF0aGlzaCIsImV4cCI6MTU0ODM5MjcyOX0.-8NDqqVD9bSYle1gekL46N8xp42rdf1q1GV8wFu1Tt4");
        RxNetworkRequest<ModelCountry> rxNetworkRequest = new RxNetworkRequest.RxNetworkRequestBuilder(this, "", ObservableType.SINGLE, RequestType.POST, ModelCountry.class).setRequestBody(requestBody).build();
        if (Network_check.isNetworkAvailable(this))
            rxNetworkRequest.makeRequest(sourceGraphQl, null, "");
        else {
            //show internet error
        }


    }

    @SuppressLint("CheckResult")
    public void source() {
        sourceGraphQl.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new Observer<ModelCountry>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: " + d);
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(ModelCountry modelCountry) {
                Log.d(TAG, "onNext: " + modelCountry.getData().getCountry());
                if (modelCountry.getData() != null) {
                    Country country = modelCountry.getData().getCountry();
                    tvCurrency.setText(country.getCurrency());
                    tvName.setText(country.getName());
                    tvEmoji.setText(country.getEmoji());
                    List<String> languages = new ArrayList<>();
                    for (Language language : country.getLanguages()) {
                        languages.add(language.getName());
                    }
                    tvLanguages.setText(StringUtils.join(languages, ','));
//);
                } else
                    imageView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        });
    }
}
