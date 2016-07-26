package com.example.lz.text.Home.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.lz.text.R;

/**
 * Created by lz on 2016/5/18.
 */
public class Loading extends Activity{

    ImageView logo;
    Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    Intent in =new Intent(Loading.this,Home.class);
                    startActivity(in);
                    finish();
                    overridePendingTransition(R.anim.alpha,R.anim.toalpha);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.loading);
        getView();
    }
    public void getView(){
        logo= (ImageView) findViewById(R.id.loading_iv);
        new Thread(new GoTo()).start();
    }
    class GoTo implements Runnable{
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                handler.sendEmptyMessage(0);
            }
        }
    }
}
