package me.arun.androidrxnetworking;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Jaison on 03/02/17.
 */

public class ProgressUtils {

    Context context;
    private Dialog progressDialog;
    boolean isNetworkCall;

    public ProgressUtils(Context context, boolean isNetworkCall) {
        this.context = context;
        this.isNetworkCall = isNetworkCall;
    }

    public void showDialog(boolean isCancelable) {

 /*       ProgressBarData progressBarData;
        if (isNetworkCall)
            progressBarData = NetworkClient.getProgressBarData();
        else
            progressBarData = ApiClient.getProgressBarData();

        progressBarData.setCancelable(isCancelable);
        showDialog(progressBarData);*/
    }

    public void showDialog(ProgressBarData progressBarData) {
        Log.d("util", "show dialog");
        progressDialog = new Dialog(context);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.setContentView(R.layout.progress_dialog);
        ProgressBar progressbar = progressDialog.findViewById(R.id.progressBar1);
        RelativeLayout rlBackground = progressDialog.findViewById(R.id.rlLoaderBackground);
        TextView tvProgressMessage = progressDialog.findViewById(R.id.tvProgressDesc);
//        if (progressBarData == null)
//        {
//            if (isNetworkCall)
//                progressBarData = NetworkClient.getProgressBarData();
//            else
//                progressBarData = ApiClient.getProgressBarData();
//        }

        progressbar.getIndeterminateDrawable().setColorFilter(progressBarData.getProgressbarTintColor(), android.graphics.PorterDuff.Mode.SRC_IN);
        progressDialog.setCancelable(progressBarData.isCancelable);

        if (!TextUtils.isEmpty(progressBarData.getProgressMessage())) {
            tvProgressMessage.setVisibility(View.VISIBLE);
            tvProgressMessage.setText(progressBarData.getProgressMessage());
            tvProgressMessage.setTextColor(progressBarData.getProgressMessageColor());
            rlBackground.setBackgroundColor(progressBarData.getBackgroundViewColor());
        } else {
            tvProgressMessage.setVisibility(View.GONE);
            rlBackground.setBackgroundColor(android.graphics.Color.TRANSPARENT);
        }

        progressDialog.show();
    }


    public void dismissDialog() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();

        Log.d("util", "dismiss dialog");
    }

}
