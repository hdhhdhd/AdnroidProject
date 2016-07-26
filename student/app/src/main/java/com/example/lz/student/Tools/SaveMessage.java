package com.example.lz.student.Tools;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.lz.student.entity.User;

/**
 * Created by lz on 2016/5/16.
 */
public class SaveMessage {
    Context cnt;
    User user;
    public SaveMessage(Context cnt) {
        this.cnt = cnt;
    }
   public void setMessage(String username,String password,String type){
        SharedPreferences spf = cnt.getSharedPreferences("username.txt",Context.MODE_PRIVATE);
        SharedPreferences.Editor ed =spf.edit();
        ed.putString("username",username);
        ed.putString("password",password);
        ed.putString("type",type);
        ed.commit();
    }
    public User getMessage(){
        SharedPreferences spf = cnt.getSharedPreferences("username.txt",Context.MODE_PRIVATE);
        String username = spf.getString("username","no");
        String password = spf.getString("password","no");
        String type = spf.getString("type","no");
        user=new User(username,password,type);
        return user;
    }
}
