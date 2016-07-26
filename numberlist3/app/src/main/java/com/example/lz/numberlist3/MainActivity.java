package com.example.lz.numberlist3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

public class MainActivity extends BaseActivity{
ImageView im;
    Animation.AnimationListener al;

    @Override
    protected void getView() {
        setContentView(R.layout.loading);
        im = (ImageView) findViewById(R.id.loading_logo_iv);
    }

    @Override
    protected void setView() {
        AlphaAnimation aa =new AlphaAnimation(0.0f,1.0f);
        al =new Animation.AnimationListener(){

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
               Intent in =new Intent(MainActivity.this,Mainmenu.class);
                startActivity(in);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
        aa.setDuration(1000);
        aa.setAnimationListener(al);
        im.startAnimation(aa);

    }


}
