package me.arun.androidrxnetworking;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;
import static me.arun.androidrxnetworking.RequestType.DELETE;
import static me.arun.androidrxnetworking.RequestType.GET;
import static me.arun.androidrxnetworking.RequestType.POST;
import static me.arun.androidrxnetworking.RequestType.PUT;


/**
 * Created by Arun Pandian M on 24/December/2018
 * arunsachin222@gmail.com
 * Chennai
 */

@Retention(SOURCE)
@StringDef({GET, POST, PUT, DELETE})
public @interface RequestType
{
    String GET = "GetType";
    String POST = "PostType";
    String PUT = "PutType";
    String DELETE = "DeleteType";
}
