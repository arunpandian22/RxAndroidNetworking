package me.arun.andoridrxnetworking.di.module;
import android.app.Application;
import android.content.Context;
import dagger.Module;
import dagger.Provides;
import me.arun.andoridrxnetworking.RXNetworkingApplication;
import me.arun.andoridrxnetworking.di.ApplicationContext;
@Module
public class ApplicationModule
{

    public String TAG="ApplicationModule";

    private final Application mApplication;

    public ApplicationModule(Application app)
    {
        mApplication = app;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication()
    {
        return mApplication;
    }

    @Provides
    RXNetworkingApplication getApplication()
    {
        return (RXNetworkingApplication) mApplication;
    }



}