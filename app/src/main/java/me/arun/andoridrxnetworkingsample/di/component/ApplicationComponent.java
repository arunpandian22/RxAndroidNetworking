package me.arun.andoridrxnetworkingsample.di.component;


import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import me.arun.andoridrxnetworkingsample.RXNetworkingApplication;
import me.arun.andoridrxnetworkingsample.di.ApplicationContext;
import me.arun.andoridrxnetworkingsample.di.module.ApplicationModule;


/**
 * Created by Arun Pandian M on 25/December/2018
 * arunsachin222@gmail.com
 * Chennai
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent
{
    void inject(RXNetworkingApplication rxNetworkingApplication);

//    Retrofit getRetrofit();

    @ApplicationContext
    Context getContext();





}