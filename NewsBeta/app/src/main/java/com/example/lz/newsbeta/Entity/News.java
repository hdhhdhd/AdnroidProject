package com.example.lz.newsbeta.Entity;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by lz on 2016/7/10.
 */
public class News implements Serializable, Parcelable {
    private Bitmap newsimg;
    private String newsname;
    private String newsurl;
    private String newsdate;
    private String newstype;
    private String imgurl;

    protected News(Parcel in) {
        newsimg = in.readParcelable(Bitmap.class.getClassLoader());
        newsname = in.readString();
        newsurl = in.readString();
        newsdate = in.readString();
        newstype = in.readString();
        imgurl = in.readString();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getNewsdate() {
        return newsdate;
    }

    public void setNewsdate(String newsdate) {
        this.newsdate = newsdate;
    }

    public String getNewstype() {
        return newstype;
    }

    public void setNewstype(String newstype) {
        this.newstype = newstype;
    }

    public News(String newsname, String newsurl, String newsdate, String newstype, String imgurl) {
        this.newsname = newsname;
        this.newsurl = newsurl;
        this.newsdate = newsdate;
        this.newstype = newstype;
        this.imgurl = imgurl;
    }

    public News(Bitmap newsimg, String newsname, String newsurl, String newsdate, String newstype, String imgurl) {
        this.newsimg = newsimg;
        this.newsname = newsname;
        this.newsurl = newsurl;
        this.newsdate = newsdate;
        this.newstype = newstype;
        this.imgurl = imgurl;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(newsimg, flags);
        dest.writeString(newsname);
        dest.writeString(newsurl);
        dest.writeString(newsdate);
        dest.writeString(newstype);
        dest.writeString(imgurl);
    }
}
