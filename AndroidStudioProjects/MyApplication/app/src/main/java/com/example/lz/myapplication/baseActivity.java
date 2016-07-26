package com.example.lz.myapplication;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by lz on 2016/5/5.
 */
public abstract class baseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getview();
        setview();
    }
    public abstract void getview();
    public abstract void setview();
}
