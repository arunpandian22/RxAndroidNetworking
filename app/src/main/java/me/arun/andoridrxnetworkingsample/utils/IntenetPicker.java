package me.arun.andoridrxnetworkingsample.utils;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by Arun Pandian M on 06/February/2019
 * arunsachin222@gmail.com
 * Chennai
 */
public class IntenetPicker {

    public final static int FILE_PICK = 1001;

    public static void chooseFileIntent(Activity activity)
    {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        activity.startActivityForResult(intent, FILE_PICK);
    }
}


