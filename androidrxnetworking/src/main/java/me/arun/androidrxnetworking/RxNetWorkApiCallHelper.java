package me.arun.androidrxnetworking;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import okhttp3.ResponseBody;

/**
 * Created by Arun Pandian M on 27/December/2018
 * arunsachin222@gmail.com
 * Chennai
 */
public class RxNetWorkApiCallHelper<T>
{

    public  Gson gson;

    static  String TAG="RxNetWorkApiCallHelper";

    /**
     * This method makes api call request and return the result in the publish subject
     * @param observable a observable type
     * @param publishSubject publish subject
     * @param responseType a response model type
     * @return it returns the Disposable object later we can dispose this
     */
    public   Disposable call(Single<ResponseBody> observable, final PublishSubject<T> publishSubject, final Class<T> responseType) {

        gson = new GsonBuilder().create();
        if (observable == null) {
            ProgressDialogLoader.progressdialogDismiss();

            throw new IllegalArgumentException("Observable must not be null.");
        }

        if (publishSubject == null) {
            ProgressDialogLoader.progressdialogDismiss();
            throw new IllegalArgumentException("Callback must not be null.");
        }
//        Log.d(TAG, "call: make request"+observable.subscribe());

        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(@NonNull ResponseBody responseBody) throws Exception {
                        // success response
                        ProgressDialogLoader.progressdialogDismiss();
                        String json = null;
                        try {
                            json = responseBody.string();
                            Log.d(TAG, "accept: " + json);
                            T res = gson.fromJson(json, responseType);
                            Log.i("Response", "test : " + gson);
                            Log.d(TAG, "accept: " + gson.fromJson(json, responseType));
                            Log.i("Response", "test : " + res + responseType.getName());
                            Log.d(TAG, "accept: " + publishSubject.hasObservers());
                            if (res != null) {
                                if (publishSubject.hasObservers())
                                    publishSubject.onNext(res);
                            }
                            else {
                                if (publishSubject.hasObservers())
                                    publishSubject.onError(new Throwable("null"));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.d(TAG, "accept: " + e.getLocalizedMessage());
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        ProgressDialogLoader.progressdialogDismiss();

                        Log.d(TAG, "accept: error" + throwable.getMessage());
                        Log.d(TAG, "accept: " + throwable.getCause());
                        Log.d(TAG, "accept: " + publishSubject.hasObservers());
                        // failure response

                            if (publishSubject.hasObservers())
                                publishSubject.onError(throwable);

                    }
                });
    }



    /**
     * This method makes api call request and return the result in the publish subject
     * @param observable a observable type
     * @param publishProcessor publish subject
     * @param responseType a response model type
     * @return it returns the Disposable object later we can dispose this
     */

    public Disposable callFlowable(Flowable<ResponseBody> observable, final PublishProcessor<T> publishProcessor, final Class<T> responseType) {

        gson = new GsonBuilder().create();
        if (observable == null) {
            ProgressDialogLoader.progressdialogDismiss();

            throw new IllegalArgumentException("Observable must not be null.");
        }

        if (publishProcessor == null) {
            ProgressDialogLoader.progressdialogDismiss();
            throw new IllegalArgumentException("Callback must not be null.");
        }
//        Log.d(TAG, "call: make request"+observable.subscribe());

        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(@NonNull ResponseBody responseBody) throws Exception {
                        // success response
                        ProgressDialogLoader.progressdialogDismiss();
                        String json = null;
                        try {
                            json = responseBody.string();
                            Log.d(TAG, "accept: " + json);
                            T res = gson.fromJson(json, responseType);
                            Log.i("Response", "test : " + gson);
                            Log.d(TAG, "accept: " + gson.fromJson(json, responseType));
                            Log.i("Response", "test : " + res + responseType.getName());
                            Log.d(TAG, "accept: hassubscribers " + publishProcessor.hasSubscribers());
                            if (res != null) {
                                if (publishProcessor.hasSubscribers())
                                    publishProcessor.onNext(res);
                            }
                            else {
                                if (publishProcessor.hasSubscribers())
                                    publishProcessor.onError(new Throwable("null"));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.d(TAG, "accept: " + e.getLocalizedMessage());
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        ProgressDialogLoader.progressdialogDismiss();

                        Log.d(TAG, "accept: error" + throwable.getMessage());
                        Log.d(TAG, "accept: " + throwable.getCause());
                        // failure response
                       try {
                           if (throwable != null) {
                               if (publishProcessor.hasSubscribers())
                               publishProcessor.onError(throwable);
                           } else {
                               if (publishProcessor.hasSubscribers())
                                   publishProcessor.onError(throwable);
                           }
                       }catch (Throwable e){
                           Log.d(TAG, "accept: "+e);
                       }
                    }
                });
    }


    public Disposable callPost(Flowable<ResponseBody> observable, final PublishProcessor<T> publishSubject, final Class<T> responseType) {

        gson = new GsonBuilder().create();
        if (observable == null) {
            throw new IllegalArgumentException("Observable must not be null.");
        }

        if (publishSubject == null) {
            throw new IllegalArgumentException("Callback must not be null.");
        }

        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(@NonNull ResponseBody responseBody) throws Exception {
                        // success response
                        ProgressDialogLoader.progressdialogDismiss();

                        String json = null;
                        try {
                            json = responseBody.string();
                            Log.d(TAG, "accept: " + json);
                            T res = gson.fromJson(json, responseType);
                            Log.i("Response", "test : " + gson);
                            Log.d(TAG, "accept: " + gson.fromJson(json, responseType));
                            Log.i("Response", "test : " + res + responseType.getName());
                            Log.d(TAG, "accept: " + publishSubject.hasSubscribers());
                            if (res != null) {
                                if (publishSubject.hasSubscribers())
                                    publishSubject.onNext(res);
                                Log.d(TAG, "accept: onNext " + res);
                            } else {
                                if (publishSubject.hasSubscribers())
                                    publishSubject.onError(new Throwable("null"));

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            ProgressDialogLoader.progressdialogDismiss();

                            Log.d(TAG, "accept: " + e.getLocalizedMessage());
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        ProgressDialogLoader.progressdialogDismiss();
                        Log.d(TAG, "accept: error: flowable" + throwable.getMessage());
                        // failure response
                        if (throwable != null) {
                            if (publishSubject.hasSubscribers())
                                publishSubject.onError(throwable);
                        } else {
                            if (publishSubject.hasSubscribers())
                                publishSubject.onError(throwable);
                        }
                    }
                });
    }


    public Disposable callMaybe(Maybe<ResponseBody> observable, final PublishSubject<T> publishSubject, final Class<T> responseType) {

        gson=new GsonBuilder().create();
        if (observable == null) {
            throw new IllegalArgumentException("Observable must not be null.");
        }

        if (publishSubject == null) {
            throw new IllegalArgumentException("Callback must not be null.");
        }

        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(@NonNull ResponseBody responseBody) throws Exception {
                        ProgressDialogLoader.progressdialogDismiss();

                        // success response
                        String json = null;
                        try {
                            json = responseBody.string();
                            Log.d(TAG, "accept: "+json);
                            T res = gson.fromJson(json, responseType);
                            Log.i("Response", "test : " + gson);
                            Log.d(TAG, "accept: "+ gson.fromJson(json, responseType));
                            Log.i("Response", "test : " + res + responseType.getName());
                            Log.d(TAG, "accept: "+publishSubject.hasObservers());
                            if (res!=null) {
                                if (publishSubject.hasObservers())
                                    publishSubject.onNext(res);
                            }
                            else {
                                if (publishSubject.hasObservers())
                                    publishSubject.onError(new Throwable("null"));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.d(TAG, "accept: "+e.getLocalizedMessage());
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.d(TAG, "accept: error"+throwable.getMessage());
                        ProgressDialogLoader.progressdialogDismiss();
                        // failure response
                        if (throwable != null) {
                            if (publishSubject.hasObservers())
                                publishSubject.onError(throwable);
                        } else {
                            if (publishSubject.hasObservers())
                                publishSubject.onError(throwable);
                        }
                    }
                });
    }
}
