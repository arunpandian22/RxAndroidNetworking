package me.arun.andoridrxnetworking.di.module;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import me.arun.andoridrxnetworking.di.ActivityContext;
import me.arun.androidrxnetworking.ProgressUtils;

/**
 * it is a module class  which provide dependencies for Fragment related classes.
 */
@Module
public class FragmentModule
{
    Fragment fragment;
    Activity activity;

    public FragmentModule(Activity activity)
    {
        this.activity=activity;
    }


    /**
     * A method to provide the context of the current class
     * @return it returns the Context of current window
     */
    @Provides
    @ActivityContext
    Context provideContext()
    {
        return activity;
    }

    /**
     * A method to provide the context of the current class
     * @return it returns the Context of current window
     */
    @Provides
    Activity provideActivity()
    {
        return activity;
    }



    @Provides
    //rxJava
    CompositeDisposable getCompisiteDisposable()
            {
                return new CompositeDisposable();
            }

    @Provides
    ProgressUtils getProgressUtils() {
        return  new ProgressUtils(activity,true);
    }




}
