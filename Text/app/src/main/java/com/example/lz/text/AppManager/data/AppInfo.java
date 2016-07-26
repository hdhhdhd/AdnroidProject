package com.example.lz.text.AppManager.data;

import android.content.pm.PackageInfo;

/**
 * Created by lz on 2016/5/24.
 */
public class AppInfo {
    private PackageInfo packageInfo;
    private boolean ischeck;

    public AppInfo(PackageInfo packageInfo, boolean ischeck) {
        this.packageInfo = packageInfo;
        this.ischeck = ischeck;
    }

    public PackageInfo getPackageInfo() {
        return packageInfo;
    }

    public void setPackageInfo(PackageInfo packageInfo) {
        this.packageInfo = packageInfo;
    }

    public boolean ischeck() {
        return ischeck;
    }

    public void setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
    }
}
