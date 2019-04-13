package me.arun.andoridrxnetworkingsample.di.module;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import me.arun.andoridrxnetworkingsample.di.ActivityContext;
import me.arun.androidrxnetworking.ProgressUtils;

@Module
public class ActivityModule
{

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }


    @Provides
        //rxJava
    CompositeDisposable getCompisiteDisposable()
    {
        return new CompositeDisposable();
    }

    @Provides
    ProgressUtils getProgressUtils() {
        return  new ProgressUtils(mActivity,true);
    }


}