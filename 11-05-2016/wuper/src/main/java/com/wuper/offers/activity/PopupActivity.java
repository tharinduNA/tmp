package com.wuper.offers.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.wuper.offers.R;

/**
 * Created by tharindu on 5/10/16.
 */
public class PopupActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*0.8));
    }
}
