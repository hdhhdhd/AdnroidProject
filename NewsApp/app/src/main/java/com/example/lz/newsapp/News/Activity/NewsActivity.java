package com.example.lz.newsapp.News.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.lz.newsapp.News.BaseFragmentActivity;
import com.example.lz.newsapp.News.Fragment.NewsFragment;
import com.example.lz.newsapp.News.Tools.HomeManager;
import com.example.lz.newsapp.News.Tools.NetWorkManager;
import com.example.lz.newsapp.R;

/**
 * Created by lz on 2016/7/3.
 */
public class NewsActivity extends BaseFragmentActivity implements View.OnClickListener{
    HomeManager hm;
    TextView textView,tv;
    String result;

    public void getview() {
        setContentView(R.layout.appframe);
        hm=new HomeManager(this);
        hm.setHomeButtomChange();
        textView= (TextView) findViewById(R.id.home_buttom_one_tv);
    }

    @Override

    public void setView() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.appframe_page,new NewsFragment());
        ft.commit();
        thisview();
    }

    public void onClick(View v) {
        hm.setbuttomchick(v);
    }
    public void thisview(){
        tv= (TextView) findViewById(R.id.textview);
        new Thread(new Runnable() {
            @Override
            public void run() {

                result= NetWorkManager.httpcookbookpost("http://v.juhe.cn/toutiao/index","","cd66223698f51efea187f9daa8e9cc73");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText(result);
                    }
                });
            }
        }).start();
    }
}
