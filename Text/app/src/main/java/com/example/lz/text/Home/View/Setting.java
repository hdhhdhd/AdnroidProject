package com.example.lz.text.Home.View;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.ToggleButton;

import com.example.lz.text.BaseActivity;
import com.example.lz.text.Home.Adapter.SettingAdapter;
import com.example.lz.text.Home.SaveMessage.SaveMessageSetting;
import com.example.lz.text.Tools;
import com.example.lz.text.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lz on 2016/5/20.
 */
public class Setting extends BaseActivity implements View.OnClickListener{
    SettingAdapter sa;
    List<String> list;
    ListView lv;
    View view;
    ToggleButton start,tz,mes;
    NotificationManager nm;
    NotificationCompat.Builder nb;
    SaveMessageSetting sms;


    public void getView(){
        sms =new SaveMessageSetting(this);
        setContentView(R.layout.setting);
        view=findViewById(R.id.sontitle_lo);
        lv = (ListView) findViewById(R.id.setting_lv);
        tz= (ToggleButton) findViewById(R.id.setting_tztitle_tb);

    }
    public void setView(){
        getData();
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Tools.setTitle(view,"设置",this);
        lv.setAdapter(sa);
        isChecked();
        setListener();
    }
    public void getData(){
        list =new ArrayList<String>();
        String str ="帮助说明";
        String str1 ="意见反馈";
        String str2 ="好友分享";
        String str3 ="版本更新";
        String str4 ="关于我们";
        list.add(str);
        list.add(str1);
        list.add(str2);
        list.add(str3);
        list.add(str4);
        sa =new SettingAdapter(this,list);
    }
    public void isChecked(){
        String b =sms.getMessage();
        if (b.equals("真")){
            tz.setChecked(true);
        }else {
            tz.setChecked(false);
            cancel();
        }
    }
    public void setListener(){
        tz.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    String b ="真";
                    sms.setMessage(b);
                    show();
                }else {
                    cancel();
                    String b ="假";
                    sms.setMessage(b);
                }
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in;
                Bundle b =new Bundle();
                switch (position){
                    case 0:
                        b.putInt("index",1);
                        in=new Intent(Setting.this,MainActivity.class);
                        in.putExtras(b);
                        startActivity(in);
                        break;
                    case 4:
                        in =new Intent(Setting.this,AboutUs.class);
                        startActivity(in);
                        break;
                }
            }
        });

    }
    public void show(){
        nb=new NotificationCompat.Builder(this);
        nb.setTicker("新通知");
        nb.setSmallIcon(R.mipmap.ic_launcher1);
        nb.setWhen(System.currentTimeMillis());
        nb.setDefaults(Notification.DEFAULT_ALL);
        Intent in =new Intent(this,Home.class);
        RemoteViews rv =new RemoteViews(this.getPackageName(),R.layout.tz);
        nb.setContent(rv);
        PendingIntent pi =PendingIntent.getActivity(this,0,in,PendingIntent.FLAG_UPDATE_CURRENT);
        nb.setContentIntent(pi);
        nb.setAutoCancel(true);
        Notification nf =nb.build();
        nf.flags=Notification.FLAG_NO_CLEAR;
        nm.notify(0,nf);

    }
    public void cancel(){
        if (nm!=null){
            nm.cancel(0);
        }else {
            nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            nm.cancel(0);
        }
    }

    @Override
    public void onClick(View v) {

    }
}
