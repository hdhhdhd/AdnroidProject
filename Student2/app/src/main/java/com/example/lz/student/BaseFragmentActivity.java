package com.example.lz.student;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

/**
 * Created by lz on 2016/6/12.
 */
public abstract class BaseFragmentActivity extends FragmentActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getView();
        setView();
    }
    public abstract void getView();
    public abstract void setView();
}
