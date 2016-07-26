package com.example.lz.numberlist;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by lz on 2016/5/10.
 */
public class SaveMessage {
    Context cnt;

    public SaveMessage(Context cnt) {
        this.cnt = cnt;
    }
    protected void getMessage(String str){
        SharedPreferences spf = cnt.getSharedPreferences("username.txt",Context.MODE_PRIVATE);
        SharedPreferences.Editor ed =spf.edit();
        ed.putString("username",str);
        ed.commit();
    }
    protected String setMessage(){
        SharedPreferences spf = cnt.getSharedPreferences("username.txt",Context.MODE_PRIVATE);
        String str = spf.getString("username","no");
        return str;
    }
}
