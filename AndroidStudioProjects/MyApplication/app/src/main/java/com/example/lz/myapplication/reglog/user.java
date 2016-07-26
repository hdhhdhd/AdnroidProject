package com.example.lz.myapplication.reglog;

import java.io.Serializable;

/**
 * Created by lz on 2016/5/5.
 */
public class user implements Serializable{
     private String name;
     private String password;
     private String tel;

    public user(String name, String password, String tel) {
        this.name = name;
        this.password = password;
        this.tel = tel;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        user user = (user) o;

        if (!name.equals(user.name)) return false;
        return password.equals(user.password);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}

