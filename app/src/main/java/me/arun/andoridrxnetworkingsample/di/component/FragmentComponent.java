package me.arun.andoridrxnetworkingsample.di.component;

import dagger.Component;
import me.arun.andoridrxnetworkingsample.di.ObjectScope;
import me.arun.andoridrxnetworkingsample.di.module.FragmentModule;


/**
 * Created by Arun Pandian M on 25/December/2018
 * arunsachin222@gmail.com
 * Chennai
 */

@ObjectScope
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {


}
