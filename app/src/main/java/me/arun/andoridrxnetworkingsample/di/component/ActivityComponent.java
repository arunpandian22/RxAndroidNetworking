package me.arun.andoridrxnetworkingsample.di.component;

import dagger.Component;
import io.reactivex.disposables.CompositeDisposable;
import me.arun.andoridrxnetworkingsample.MainActivity;
import me.arun.andoridrxnetworkingsample.di.ObjectScope;
import me.arun.andoridrxnetworkingsample.di.module.ActivityModule;

/**
 * Created by Arun Pandian M on 25/December/2018
 * arunsachin222@gmail.com
 * Chennai
 */

@ObjectScope
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent
{
    void inject(MainActivity mainActivity);
    CompositeDisposable getCompisiteDisposable();
}