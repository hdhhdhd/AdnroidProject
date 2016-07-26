package com.example.lz.newsapp.News.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.lz.newsapp.BaseActivity;
import com.example.lz.newsapp.R;

/**
 * Created by lz on 2016/7/3.
 */
public class WebViewActivity extends BaseActivity{
    WebView webView;
    Intent intent;
    String inputurl;

    @Override
    public void getview() {
        setContentView(R.layout.webview);
        webView= (WebView) findViewById(R.id.webview);
        intent=getIntent();
    }

    @Override
    public void setView() {
        if (intent!=null){
            Bundle b =intent.getExtras();
            inputurl=b.getString("url","www.baidu.com");
        }
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(inputurl);
    }

}
