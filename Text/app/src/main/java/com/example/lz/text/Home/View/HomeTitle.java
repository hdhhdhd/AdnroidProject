package com.example.lz.text.Home.View;

/**
 * Created by lz on 2016/5/19.
 */
public class HomeTitle {
    private String hometitle;
    private int ID;
    private Class cl;

    public Class getCl() {
        return cl;
    }

    public void setCl(Class cl) {
        this.cl = cl;
    }

    public HomeTitle(String hometitle, Class cl, int ID) {

        this.hometitle = hometitle;
        this.cl = cl;
        this.ID = ID;
    }

    public void setHometitle(String hometitle) {
        this.hometitle = hometitle;
    }

    public String getHometitle() {
        return hometitle;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
