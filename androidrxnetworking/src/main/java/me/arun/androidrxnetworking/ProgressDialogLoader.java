package me.arun.androidrxnetworking;

import android.app.Activity;
import android.content.Context;
import android.util.Log;


/**
 * A class created to have the methods related to ProgressDoalogLoader
 */
public class ProgressDialogLoader
{
    public static ProgressUtils progressUtils;

    Context context;
    Activity activity;
    boolean isNetworkCall;


    public ProgressDialogLoader(Context context, boolean isNetworkCall)
    {
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
               Log.d("ProgressDialogLoader","dialog null: show");
               progressUtils.showDialog(false);
           }
        }
        catch(Exception e)
        {
            Log.d("ProgressDialogLoader", "progressdialogCreation: ");

        }
    }


    /**
     * A method to show the  dialog
     * @param activity a
     * @param progressBarData
     * @param isNetworkCall
     */
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

    /**
     * A method to dismiss the dialog
     */
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
