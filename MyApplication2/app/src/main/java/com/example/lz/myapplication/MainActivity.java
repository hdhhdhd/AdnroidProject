package com.example.lz.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

public class MainActivity extends BaseActivity implements View.OnClickListener{
TextView tv,tv1,tv2,tv3;


    @Override
    protected void getView() {
         setContentView(R.layout.activity_main);
        tv= (TextView) findViewById(R.id.av_main_tv);
        tv1= (TextView) findViewById(R.id.av_main1_tv);
        tv2 = (TextView) findViewById(R.id.av_main2_tv);
        tv3 = (TextView) findViewById(R.id.av_main3_tv);
    }

    @Override
    protected void setView() {
         tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.av_main_tv:
                Animation animation = new AlphaAnimation(1.0f, 0.0f);
                animation.setDuration(1000);
                animation.setRepeatCount(5);
                animation.setRepeatMode(Animation.REVERSE);
                animation.start();
                tv.startAnimation(animation);
            case R.id.av_main1_tv:
                ScaleAnimation sc =new ScaleAnimation(1.0f,1.5f,1.0f,1.5f,Animation.RELATIVE_TO_SELF,0.8f,Animation.RELATIVE_TO_SELF,0.2f);
                sc.setFillAfter(true);
                sc.setRepeatCount(5);
                sc.setRepeatMode(Animation.REVERSE);
                sc.setDuration(1000);
                sc.start();
                tv1.startAnimation(sc);
                break;
            case R.id.av_main2_tv:
                TranslateAnimation ta =new TranslateAnimation(Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0.5f);
                ta.setFillAfter(true);
                ta.setRepeatCount(5);
                ta.setRepeatMode(Animation.REVERSE);
                ta.setDuration(1000);
                break;
            case R.id.av_main3_tv:

        }

    }
}
