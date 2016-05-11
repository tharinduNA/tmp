package com.wuper.offers.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by Tharindu on 01-May-16.
 */
public class DeviceScreen {

    static DisplayMetrics metrics;

    private static DisplayMetrics commonMethod(Context context){
        metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }
    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = commonMethod(context);
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeigh(Context context) {
        DisplayMetrics displayMetrics = commonMethod(context);
        return displayMetrics.heightPixels;
    }
}
