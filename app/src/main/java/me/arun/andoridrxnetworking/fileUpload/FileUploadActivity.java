package me.arun.andoridrxnetworking.fileUpload;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import me.arun.andoridrxnetworking.R;
import me.arun.andoridrxnetworking.resModel.ModelPatientSearch;
import me.arun.andoridrxnetworking.resModel.UserImgUploadResponse;
import me.arun.andoridrxnetworking.resModel.imageResponse.ImageResponse;
import me.arun.andoridrxnetworking.utils.Imageutils;
import me.arun.androidrxnetworking.Network_check;
import me.arun.androidrxnetworking.NetworkingApiClient;
import me.arun.androidrxnetworking.ObservableType;
import me.arun.androidrxnetworking.RequestType;
import me.arun.androidrxnetworking.ResponseType;
import me.arun.androidrxnetworking.RxNetworkRequest;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import static me.arun.andoridrxnetworking.utils.Imageutils.CAMERA_REQUEST_CODE;

/**
 * A method to test the
 */
public class FileUploadActivity extends AppCompatActivity implements Imageutils.ImageAttachmentListener {
    Imageutils imageutils;
    @BindView(R.id.ivUpload)
    ImageView ivUpload;
    PublishSubject<ImageResponse> sourceUplopad = PublishSubject.create();
    CompositeDisposable compositeDisposable=new CompositeDisposable();


    String TAG="FileUploadActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_upload);
        ButterKnife.bind(this);
        imageutils = new Imageutils(this, this);
        imageutils.setImageAttachment_callBack(this);
        source();
        NetworkingApiClient.setClient("https://api.imgur.com/");


        ivUpload.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                imageutils.imagepicker(1);
            }
        });
    }

    @Override
    public void image_attachment(int from, String filename, Bitmap file1, String uripath)
    {
        if (from == CAMERA_REQUEST_CODE) {
            Log.d(TAG, "image_attachment: ");

            File file = new File(uripath);
            Log.d(TAG, "uploadFile: "+file);

            if(file.exists()) android.util.Log.d(TAG, "uploadFile: "+file.getName());
            //creating request body for file
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);

            Map<String, String> hmHearders = new HashMap<>();
            hmHearders.put("token", "eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImZkZUBuZm4iLCJuYW1lIjoiUmF0aGlzaCIsImV4cCI6MTU0ODM5MjcyOX0.-8NDqqVD9bSYle1gekL46N8xp42rdf1q1GV8wFu1Tt4");
            RxNetworkRequest<ImageResponse> rxNetworkRequest = new RxNetworkRequest.RxNetworkRequestBuilder(this,"/3/image", ObservableType.SINGLE,RequestType.POST, UserImgUploadResponse.class).setHeaderParams(hmHearders).setFile(body).build();
            if (Network_check.isNetworkAvailable(this))
            rxNetworkRequest.makeImageUpload(sourceUplopad);
            else
            {
                //show internet error
            }
            ivUpload.setImageBitmap(file1);

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: reqcode+resltcode"+requestCode+""+resultCode);
        try {
            super.onActivityResult(requestCode, resultCode, data);
            imageutils.onActivityResult(requestCode, resultCode, data);

            if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK)
            {
                Log.d(TAG, "onActivityResult: " + CAMERA_REQUEST_CODE);
            } /*else if (requestCode == GALEERY_REQUEST_CODE && resultCode == RESULT_OK) {
                Log.d(TAG, "onActivityResult: galery " + GALEERY_REQUEST_CODE);

            }*/
        } catch (Exception ex)
        {
            Toast.makeText(this, ex.toString(),
                    Toast.LENGTH_SHORT).show();
        }

    }



    @SuppressLint("CheckResult")
    public void source()
    {
        sourceUplopad.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new Observer<ImageResponse>() {
            @Override
            public void onSubscribe(Disposable d)
            {
                Log.d(TAG, "onSubscribe: " + d);
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(ImageResponse modelPatientSearch)
            {
                Log.d(TAG, "onNext: " + modelPatientSearch.status);
                Log.d(TAG, "onNext: "+modelPatientSearch.data.link);
                Log.d(TAG, "onNext: ");
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: ");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
       imageutils.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}