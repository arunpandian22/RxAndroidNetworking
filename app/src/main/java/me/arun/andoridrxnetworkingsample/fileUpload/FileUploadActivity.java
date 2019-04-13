package me.arun.andoridrxnetworkingsample.fileUpload;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.Guideline;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import me.arun.andoridrxnetworkingsample.R;
import me.arun.andoridrxnetworkingsample.resModel.imageResponse.ImageUploadResponse;
import me.arun.andoridrxnetworkingsample.utils.FeatureCategory;
import me.arun.andoridrxnetworkingsample.utils.Imageutils;
import me.arun.androidrxnetworking.Network_check;
import me.arun.androidrxnetworking.NetworkingApiClient;
import me.arun.androidrxnetworking.ObservableType;
import me.arun.androidrxnetworking.RequestType;
import me.arun.androidrxnetworking.RxNetworkRequest;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static me.arun.andoridrxnetworkingsample.utils.Imageutils.CAMERA_REQUEST_CODE;

/**
 * A method to test the
 */
public class FileUploadActivity extends AppCompatActivity implements Imageutils.ImageAttachmentListener {
    Imageutils imageutils;
    @BindView(R.id.ivUpload)
    ImageView ivUpload;
    PublishSubject<ImageUploadResponse> sourceUplopad = PublishSubject.create();
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    File imageFile;


    String TAG = "FileUploadActivity";
    @BindView(R.id.tvTitleLabel)
    TextView tvTitleLabel;
    @BindView(R.id.etTitle)
    EditText etTitle;
    @BindView(R.id.tvDescriptionLabel)
    TextView tvDescriptionLabel;
    @BindView(R.id.etDescription)
    EditText etDescription;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.guidelineTop)
    Guideline guidelineTop;
    @BindView(R.id.guidelineBottom)
    Guideline guidelineBottom;
    @BindView(R.id.fabUpload)
    FloatingActionButton fabUpload;
    @BindView(R.id.ivUploadImage)
    ImageView ivUploadImage;
    String title = "", description = "";
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_upload);
        ButterKnife.bind(this);
        toolbar.setTitle(FeatureCategory.FILE_UPLOAD);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAbTitle));
        setActionBar(toolbar);
        imageutils = new Imageutils(this, this);
        imageutils.setImageAttachment_callBack(this);
        source();
        NetworkingApiClient.setClient("https://api.imgur.com/");


        ivUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageutils.imagepicker(1);
            }
        });
    }

    @Override
    public void image_attachment(int from, String filename, Bitmap file1, String uripath) {
        if (from == CAMERA_REQUEST_CODE) {
            Log.d(TAG, "image_attachment: ");

            imageFile = new File(uripath);
            Log.d(TAG, "uploadFile: " + imageFile);

            if (imageFile.exists()) Log.d(TAG, "uploadFile: " + imageFile.getName());

            ivUploadImage.setImageBitmap(file1);

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: reqcode+resltcode" + requestCode + "" + resultCode);
        try {
            super.onActivityResult(requestCode, resultCode, data);
            imageutils.onActivityResult(requestCode, resultCode, data);

            if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
                Log.d(TAG, "onActivityResult: " + CAMERA_REQUEST_CODE);
            } /*else if (requestCode == GALEERY_REQUEST_CODE && resultCode == RESULT_OK) {
                Log.d(TAG, "onActivityResult: galery " + GALEERY_REQUEST_CODE);

            }*/
        } catch (Exception ex) {
            Toast.makeText(this, ex.toString(),
                    Toast.LENGTH_SHORT).show();
        }

    }


    @SuppressLint("CheckResult")
    public void source() {
        sourceUplopad.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new Observer<ImageUploadResponse>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: " + d);
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(ImageUploadResponse modelPatientSearch) {
//                Log.d(TAG, "onNext: " + modelPatientSearch.status);
//                Log.d(TAG, "onNext: " + modelPatientSearch.data.link);
                Log.d(TAG, "onNext: ");
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: ");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        imageutils.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @OnClick(R.id.fabUpload)
    public void onViewClicked() {
        title = etTitle.getText().toString();
        description = etDescription.getText().toString();
        if (TextUtils.isEmpty(title) && TextUtils.isEmpty(description)) {
            etTitle.setError("set the Title");
            etDescription.setError("set Description");
        } else if (TextUtils.isEmpty(title))
            etTitle.setError("set Title");
        else if (TextUtils.isEmpty(description))
            etDescription.setError("set Description");
        else {
            makeRequest();
        }

    }


    public void makeRequest() {
        //creating request body for file
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", imageFile.getName(), requestFile);

        Map<String, String> hmHearders = new HashMap<>();
        hmHearders.put("Authorization", "Client-ID ed83c4512de19ea");
        Map<String, String> hmParams = new HashMap<>();
        hmParams.put("title", "testing");
        hmParams.put("description", "testingDescription");

        RxNetworkRequest<ImageUploadResponse> rxNetworkRequest = new RxNetworkRequest.RxNetworkRequestBuilder(this, "/3/image", ObservableType.SINGLE, RequestType.POST, ImageUploadResponse.class).setHeaderParams(hmHearders).setFile(body).build();
        if (Network_check.isNetworkAvailable(this))
            rxNetworkRequest.makeRequest(sourceUplopad, null, "");
        else {
            //show internet error
        }
    }
}
