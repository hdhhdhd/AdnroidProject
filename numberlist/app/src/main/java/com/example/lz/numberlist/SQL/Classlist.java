package com.example.lz.numberlist.SQL;

/**
 * Created by lz on 2016/5/12.
 */
public class Classlist {
    private String name;
    private int index;

    public Classlist(int index, String name) {
        this.index = index;
        this.name = name;
    }

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
}
