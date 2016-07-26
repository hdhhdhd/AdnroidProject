package com.example.lz.text.Home.SaveMessage;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by lz on 2016/5/20.
 */
public class SaveMessageSetting {
    Context context;

    public SaveMessageSetting(Context context) {
        this.context = context;
    }
    public void setMessage(String b){
        SharedPreferences spf =context.getSharedPreferences("setting.txt",Context.MODE_PRIVATE);
        SharedPreferences.Editor ed =spf.edit();
        ed.putString("hh",b);
        ed.commit();
    }
    public String getMessage(){
        SharedPreferences spf =context.getSharedPreferences("setting.txt",Context.MODE_PRIVATE);
        String b =spf.getString("hh","假");
        return b;
    }
}
