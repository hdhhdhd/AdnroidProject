package com.example.lz.newsbeta.SaveMessage;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by lz on 2016/7/10.
 */
public class SaveMessage {
    Context context;

    public SaveMessage(Context context) {
        this.context = context;
    }
    public void setMessage(){
        SharedPreferences spf =context.getSharedPreferences("hh.txt",Context.MODE_PRIVATE);
        SharedPreferences.Editor ed =spf.edit();
        String b ="真";
        ed.putString("hh",b);
        ed.commit();
    }
    public String getMessage(){
        SharedPreferences spf =context.getSharedPreferences("hh.txt",Context.MODE_PRIVATE);
        String b =spf.getString("hh","假");
        return b;
    }
}
