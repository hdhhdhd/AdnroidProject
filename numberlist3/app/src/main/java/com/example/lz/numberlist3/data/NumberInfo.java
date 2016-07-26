package com.example.lz.numberlist3.data;

/**
 * Created by lz on 2016/5/13.
 */
public class NumberInfo {
    private String name;
    private String num;

    public NumberInfo(String name, String num) {
        this.name = name;
        this.num = num;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
