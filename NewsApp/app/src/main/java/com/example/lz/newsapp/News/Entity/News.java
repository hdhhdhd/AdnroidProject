package com.example.lz.newsapp.News.Entity;

import android.graphics.Bitmap;

/**
 * Created by lz on 2016/7/3.
 */
public class News {
    private Bitmap newsimg;
    private String newsname;
    private String newsurl;

    public News(Bitmap newsimg, String newsname, String newsurl) {
        this.newsimg = newsimg;
        this.newsname = newsname;
        this.newsurl = newsurl;
    }

    public Bitmap getNewsimg() {
        return newsimg;
    }

    public void setNewsimg(Bitmap newsimg) {
        this.newsimg = newsimg;
    }

    public String getNewsname() {
        return newsname;
    }

    public void setNewsname(String newsname) {
        this.newsname = newsname;
    }

    public String getNewsurl() {
        return newsurl;
    }

    public void setNewsurl(String newsurl) {
        this.newsurl = newsurl;
    }
}
