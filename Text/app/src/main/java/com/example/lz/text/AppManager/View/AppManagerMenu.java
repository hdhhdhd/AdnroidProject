package com.example.lz.text.AppManager.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.lz.text.BaseActivity;
import com.example.lz.text.AppManager.MyAdapter.MyAdapter;
import com.example.lz.text.R;
import com.example.lz.text.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lz on 2016/5/19.
 */
public class AppManagerMenu extends BaseActivity{
    ListView lv;
    MyAdapter ma;
    List<String> list;
    View view;
    ProgressBar phonememory,sdmemory;
    TextView phone,sd;
    CakeView cv;
    String phonememorystr,sdmemorystr;
    float f1,f2;
    double d,d1;
    int p,p1;
    @Override
    public void getView() {
      setContentView(R.layout.appmanager);
        lv= (ListView) findViewById(R.id.appmanger_lv);
        view =findViewById(R.id.appmanager_title);
        phonememory = (ProgressBar) findViewById(R.id.appmanager_phonememory_pb);
        sdmemory = (ProgressBar) findViewById(R.id.appmanager_sdmemory_pb);
        phone = (TextView) findViewById(R.id.appmanager_freephonememory_tv);
        sd = (TextView) findViewById(R.id.appmanager_freesdmemory_tv);
        cv = (CakeView) findViewById(R.id.appmanager_cake_cv);
    }
    public void getData(){
        list =new ArrayList<String>();
        String str ="所有软件";
        String str1 ="系统软件";
        String str2 ="本机软件";
        list.add(str);
        list.add(str1);
        list.add(str2);
        ma =new MyAdapter(this,list);
        phonememorystr = "可用："+Tools.getFileSize(Tools.getPhoneSelfSDCardFreeSize())+"/"+Tools.getFileSize(Tools.getPhoneSelfSDCardSize());
        sdmemorystr ="可用："+Tools.getFileSize(Tools.getPhoneOutSDCardFreeSize(this))+"/"+Tools.getFileSize(Tools.getPhoneOutSDCardSize(this));
        f1 =(Tools.getPhoneSelfSDCardSize()*100)/Tools.getPhoneAllSize();
        f2 =(Tools.getPhoneOutSDCardSize(this)*100)/Tools.getPhoneAllSize();
        d =(Tools.getPhoneSelfSDCardFreeSize()*100)/Tools.getPhoneSelfSDCardSize();
        p = (int) (d);
        d1=0;
        if (Tools.getPhoneOutSDCardSize(this)!=0) {
            d1 = (Tools.getPhoneOutSDCardFreeSize(this)*100) / Tools.getPhoneOutSDCardSize(this);
        }
         p1 = (int) (d1);

    }

    @Override
    public void setView() {
        getData();
        lv.setAdapter(ma);
        Tools.setTitle(view,"软件管理",this);
        cv.setDraw(f1,f2);
        phone.setText(phonememorystr);
        sd.setText(sdmemorystr);
        phonememory.setMax(100);
        phonememory.setProgress(p);
        sdmemory.setMax(100);
        sdmemory.setProgress(p1);
        setListener();
    }
    public void setListener(){
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in;
                Bundle b=new Bundle();;
                int index;
                String str;
                switch (position){
                    case 0:
                        in =new Intent(AppManagerMenu.this,SecAppMeanagerMenu.class);
                        index=position;
                        str =list.get(position);
                        b.putString("title",str);
                        b.putInt("index",index);
                        in.putExtras(b);
                        startActivity(in);
                        break;
                    case 1:
                        in =new Intent(AppManagerMenu.this,SecAppMeanagerMenu.class);
                        index =position;
                        b.putInt("index",index);
                        str =list.get(position);
                        b.putString("title",str);
                        in.putExtras(b);
                        startActivity(in);
                        break;
                    case 2:
                        in =new Intent(AppManagerMenu.this,SecAppMeanagerMenu.class);
                        index =position;
                        b.putInt("index",index);
                        str =list.get(position);
                        b.putString("title",str);
                        in.putExtras(b);
                        startActivity(in);
                        break;
                }
            }
        });
    }
}
