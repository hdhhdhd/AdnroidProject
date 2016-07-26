package com.example.lz.numberlist.data;

/**
 * Created by lz on 2016/5/11.
 */
public class User {
    private String username;
    private String age;

    public User(String username, String age) {
        this.username = username;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
