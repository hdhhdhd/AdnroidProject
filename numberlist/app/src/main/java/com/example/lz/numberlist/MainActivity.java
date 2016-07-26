package com.example.lz.numberlist;

import android.content.Intent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

public class MainActivity extends BaseActivity {
    ImageView im;
    Animation.AnimationListener al;

    @Override
    protected void getView() {
        setContentView(R.layout.loading);
        im = (ImageView) findViewById(R.id.loading_logo_imv);

    }

    @Override
    protected void setView() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f,0.0f);
        al=new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent in =new Intent(MainActivity.this,MenuActivity.class);
                startActivity(in);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
        alphaAnimation.setDuration(1000);
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        alphaAnimation.setRepeatCount(1);
        alphaAnimation.setAnimationListener(al);
        im.startAnimation(alphaAnimation);
    }

}
