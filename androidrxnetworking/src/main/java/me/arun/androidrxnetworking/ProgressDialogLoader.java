package me.arun.androidrxnetworking;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

/**
 * Created by Jaison on 18/08/16.
 */
public class ProgressDialogLoader
{
    public static ProgressUtils progressUtils;

    Context context;
    Activity activity;
    boolean isNetworkCall;

    public ProgressDialogLoader(Context context, boolean isNetworkCall) {
        this.context = context;
        this.activity= (Activity) context;
        this.isNetworkCall=isNetworkCall;
    }

    public static void progressdialogCreation(Activity activity,boolean isNetworkCall)
    {
        try {
           if(progressUtils==null)
           {
               progressUtils=new ProgressUtils(activity,isNetworkCall);
               Log.d("dialog null","show");
               progressUtils.showDialog(false);
           }
        }
        catch(Exception e)
        {

        }
    }

    public static void progressdialogCreation(Activity activity, ProgressBarData progressBarData,boolean isNetworkCall)
    {
        try {
            if(progressUtils==null)
            {
                progressUtils=new ProgressUtils(activity,isNetworkCall);
                Log.d("dialog null","show");
                progressUtils.showDialog(progressBarData);
            }
        }
        catch(Exception e)
        {

        }
    }

    public static void progressdialogDismiss()
    {

        if(progressUtils!=null)
        {
            Log.d("dialog not null","dismiss");
            progressUtils.dismissDialog();
        }
        progressUtils=null;
    }
}
