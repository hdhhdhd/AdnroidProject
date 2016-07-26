package com.example.lz.newsbeta.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.lz.newsbeta.BaseActivity;
import com.example.lz.newsbeta.DB.CollectDb;
import com.example.lz.newsbeta.Entity.News;
import com.example.lz.newsbeta.R;

/**
 * Created by lz on 2016/7/21.
 */
public class WebViewActivity extends BaseActivity{
    WebView webView;
    Intent intent;
    News news;
    String inputurl;
    FloatingActionButton actionbtn;
     ProgressBar bar;
    CollectDb cb ;

    @Override
    public void getView() {
        setContentView(R.layout.webview);
        webView= (WebView) findViewById(R.id.webview_wb);
        intent=getIntent();
        bar= (ProgressBar) findViewById(R.id.web_progressbar);
        actionbtn= (FloatingActionButton) findViewById(R.id.actionbtn);
        cb =new CollectDb(this);
    }

    @Override
    public void setView() {
        if (intent!=null){
            Bundle b =intent.getExtras();
            inputurl=b.getString("url","www.baidu.com");
            news = b.getParcelable("news");
        }
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return true;
            }
        });
        actionbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                cb.add(news);
                Snackbar.make(actionbtn,"收藏成功",Snackbar.LENGTH_LONG).show();
            }
        });
        webView.loadUrl(inputurl);

    }
}
