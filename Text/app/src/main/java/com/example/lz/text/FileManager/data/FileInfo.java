package com.example.lz.text.FileManager.data;

import java.io.File;

/**
 * Created by lz on 2016/6/2.
 */
public class FileInfo {
    private File file;
    private boolean ischeck;
   private String icon;
    private  String type;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public FileInfo(File file, String icon, String type) {
        this.file = file;
        this.icon = icon;
        this.type = type;
    }

    public boolean ischeck() {
        return ischeck;
    }

    public void setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
    }
}
