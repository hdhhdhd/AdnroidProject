package com.example.lz.text.tongxun.data;

/**
 * Created by lz on 2016/5/22.
 */
public class Classlist {
    private String name;
    private int index;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Classlist(String name, int index) {
        this.name = name;
        this.index = index;
    }
}
