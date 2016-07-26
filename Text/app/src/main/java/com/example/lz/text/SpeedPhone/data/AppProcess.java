package com.example.lz.text.SpeedPhone.data;

import android.graphics.drawable.Drawable;

/**
 * Created by lz on 2016/5/27.
 */
public class AppProcess {
    private String name;
    private String packagename;
    private String ram;
    private Drawable icon;
    private String type;
    private Boolean ischeck;
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getIscheck() {
        return ischeck;
    }

    public void setIscheck(Boolean ischeck) {
        this.ischeck = ischeck;
    }

    public String getPackagename() {
        return packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public AppProcess(String name, String packagename, String ram, Drawable icon, String type, Boolean ischeck) {

        this.name = name;
        this.packagename = packagename;
        this.ram = ram;
        this.icon = icon;
        this.type = type;
        this.ischeck = ischeck;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public Drawable getIcon() {
        return icon;
    }

}
