package com.example.lz.text.CheckPhone.data;

/**
 * Created by lz on 2016/5/26.
 */
public class PhoneInfo {
    private String name1;
    private String name2;
    private int iconid;

    public PhoneInfo(String name1, String name2, int iconid) {
        this.name1 = name1;
        this.name2 = name2;
        this.iconid = iconid;
    }

    public String getName1() {
        return name1;
    }

    public int getIconid() {
        return iconid;
    }

    public void setIconid(int iconid) {
        this.iconid = iconid;
    }

    public void setName1(String name1) {
        this.name1 = name1;

    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }
}
