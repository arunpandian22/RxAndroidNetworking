package me.arun.androidrxnetworking;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


/**
 * Created by Arun Pandian M on 21/December/2018
 * arunsachin222@gmail.com
 * Chennai
 */

/**
 * A
 */
public class Network_check {

    /**
     * A method to check the internet is available or not
     * @param context a context of the current screen
     * @return it returns the true if ht enetwork is available
     */
    public static boolean isNetworkAvailable(Context context) {
        boolean networkavailable = false;

        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            assert connectivityManager != null;
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

            if (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting()) {
                networkavailable = true;
            } else {
                networkavailable = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return networkavailable;
    }

}