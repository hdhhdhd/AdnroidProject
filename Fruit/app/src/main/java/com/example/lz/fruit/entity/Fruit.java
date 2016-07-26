package com.example.lz.fruit.entity;

import java.io.Serializable;

/**
 * Created by lz on 2016/7/5.
 */
public class Fruit implements Serializable{
    private String uesername;
    private String name;
    private int allnum=100;
    private int sum;
    private String info;
    private  int price;
    private int image;
    private boolean ischecked =false;
    public boolean ischecked() {
        return ischecked;
    }

    public void setIschecked(boolean ischecked) {
        this.ischecked = ischecked;
    }

    public String getUesername() {
        return uesername;
    }

    public void setUesername(String uesername) {
        this.uesername = uesername;
    }

    public Fruit(String uesername, String name, int allnum, int sum, int price, int image) {
        this.uesername = uesername;
        this.name = name;
        this.allnum = allnum;
        this.sum = sum;
        this.price = price;
        this.image = image;
    }

    public Fruit(String name, String info, int sum, int price, int image) {
        this.name = name;
        this.info = info;
        this.sum = sum;
        this.price = price;
        this.image = image;
    }

    public Fruit(String name, int sum, int price, int image) {
        this.name = name;
        this.sum = sum;
        this.price = price;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAllnum() {
        return allnum;
    }

    public void setAllnum(int allnum) {
        this.allnum = allnum;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "uesername='" + uesername + '\'' +
                ", name='" + name + '\'' +
                ", allnum=" + allnum +
                ", sum=" + sum +
                ", info='" + info + '\'' +
                ", price=" + price +
                ", image=" + image +
                ", ischecked=" + ischecked +
                '}';
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
