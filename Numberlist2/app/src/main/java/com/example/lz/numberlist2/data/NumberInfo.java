package com.example.lz.numberlist2.data;

/**
 * Created by lz on 2016/5/13.
 */
public class NumberInfo {
    private String name;
    private String number;

    public NumberInfo(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
