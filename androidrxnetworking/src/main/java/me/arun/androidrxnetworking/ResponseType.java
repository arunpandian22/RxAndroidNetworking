package me.arun.androidrxnetworking;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;
import static me.arun.androidrxnetworking.ResponseType.BITMAP;
import static me.arun.androidrxnetworking.ResponseType.JSON_ARRAY;
import static me.arun.androidrxnetworking.ResponseType.JSON_OBJECT;
import static me.arun.androidrxnetworking.ResponseType.OK_HTTP;
import static me.arun.androidrxnetworking.ResponseType.PARSED;
import static me.arun.androidrxnetworking.ResponseType.PREFETCH;
import static me.arun.androidrxnetworking.ResponseType.STRING;


/**
 * Created by Arun Pandian M on 22/December/2018
 * arunsachin222@gmail.com
 * Chennai
 */
@Retention(SOURCE)
@StringDef({STRING, JSON_OBJECT, JSON_ARRAY, OK_HTTP, BITMAP, PREFETCH, PARSED})
public @interface ResponseType
{
    String STRING = "StringResponse";
    String JSON_OBJECT = "JsonObjectResponse";
    String JSON_ARRAY = "JsonArrayRes";
    String OK_HTTP = "OKHttpResponse";
    String BITMAP = "BITMAPResponse";
    String PREFETCH = "PREFETCHResponse";
    String PARSED = "PARSEDResponse";

}



