package com.example.lz.newsapp.News.Entity;


import android.support.v4.app.Fragment;
import android.widget.ImageView;

/**
 * Created by lz on 2016/7/3.
 */
public class HomeButton {
    private ImageView imageView;
    private int chickbefore;
    private int chickafter;
    private Fragment chickfragment;

    public HomeButton(ImageView imageView, int chickbefore, int chickafter, Fragment chickfragment) {
        this.imageView = imageView;
        this.chickbefore = chickbefore;
        this.chickafter = chickafter;
        this.chickfragment = chickfragment;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public int getChickbefore() {
        return chickbefore;
    }

    public void setChickbefore(int chickbefore) {
        this.chickbefore = chickbefore;
    }

    public int getChickafter() {
        return chickafter;
    }

    public void setChickafter(int chickafter) {
        this.chickafter = chickafter;
    }

    public Fragment getChickfragment() {
        return chickfragment;
    }

    public void setChickfragment(Fragment chickfragment) {
        this.chickfragment = chickfragment;
    }
}
