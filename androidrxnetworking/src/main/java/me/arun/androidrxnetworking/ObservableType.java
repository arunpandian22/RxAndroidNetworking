package me.arun.androidrxnetworking;

import android.support.annotation.StringDef;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.SOURCE;
import static me.arun.androidrxnetworking.ObservableType.COMPLETABLE;
import static me.arun.androidrxnetworking.ObservableType.FLOWABLE;
import static me.arun.androidrxnetworking.ObservableType.MAYBE;
import static me.arun.androidrxnetworking.ObservableType.SINGLE;


/**
 * Created by Arun Pandian M on 22/December/2018
 * arunsachin222@gmail.com
 * Chennai
 */

@Retention(SOURCE)
@StringDef({FLOWABLE, SINGLE, MAYBE, COMPLETABLE})
public @interface ObservableType {
    String FLOWABLE = "FlowableType";
    String SINGLE = "SingleType";
    String MAYBE = "MaybeType";
    String COMPLETABLE = "CompletableType";

}
