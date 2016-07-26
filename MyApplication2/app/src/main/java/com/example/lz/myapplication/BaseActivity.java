package com.example.lz.myapplication;

import android.app.Activity;
import android.os.Bundle;


/**
 * Created by lz on 2016/5/9.
 */
public abstract class BaseActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getView();
        setView();
    }
    abstract protected void getView();
    abstract protected void setView();


}
