package com.example.lz.newsbeta.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.lz.newsbeta.BaseActivity;
import com.example.lz.newsbeta.R;

/**
 * Created by lz on 2016/7/10.
 */
public class Loading extends BaseActivity{
    ImageView logo;
    Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    Intent in =new Intent(Loading.this,HomeActivity.class);
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
        setContentView(R.layout.loading_activity);
        getView();
    }
    public void getView(){
        logo= (ImageView) findViewById(R.id.loading_iv);
        new Thread(new GoTo()).start();
    }

    @Override
    public void setView() {

    }

    class GoTo implements Runnable{
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                handler.sendEmptyMessage(0);
            }
        }
    }
}
