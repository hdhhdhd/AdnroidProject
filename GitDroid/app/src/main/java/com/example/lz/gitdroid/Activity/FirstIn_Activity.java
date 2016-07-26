package com.example.lz.gitdroid.Activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.lz.gitdroid.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lz on 2016/7/26.
 */
public class FirstIn_Activity extends AppCompatActivity{
    @Bind(R.id.viewPager_spalsh) ViewPager viewPager;
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
    }

}
