package com.example.lz.numberlist.SQL;

/**
 * Created by lz on 2016/5/12.
 */
public class NumberInfo{
    private String name;
    private String number;

    public NumberInfo(String number, String name) {
        this.number = number;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
