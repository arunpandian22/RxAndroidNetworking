package me.arun.andoridrxnetworking.di.component;

import dagger.Component;
import io.reactivex.disposables.CompositeDisposable;
import me.arun.andoridrxnetworking.di.ObjectScope;
import me.arun.andoridrxnetworking.di.module.FragmentModule;


/**
 * Created by Arun Pandian M on 25/December/2018
 * arunsachin222@gmail.com
 * Chennai
 */

@ObjectScope
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {


}
