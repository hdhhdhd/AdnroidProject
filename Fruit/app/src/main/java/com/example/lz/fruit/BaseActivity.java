package com.example.lz.fruit;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by lz on 2016/7/5.
 */
public abstract class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getView();
        setView();
    }
    abstract public void getView();
    abstract public void setView();
}
