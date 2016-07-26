package com.example.lz.student.Entity;

/**
 * Created by lz on 2016/6/14.
 */
public class User {
    private String username;
    private String password;
    private String type;

    public User(String username, String password, String type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public String getUsername() {

        return username;
    }

    public void setuserUsername(String userusername) {
        this.username = userusername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String gettype() {
        return type;
    }

    public void settype(String type) {
        this.type = type;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (username.equals(user.username)) return false;
        return password.equals(user.password);

    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
