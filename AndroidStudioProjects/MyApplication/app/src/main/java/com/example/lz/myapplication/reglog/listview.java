package com.example.lz.myapplication.reglog;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.lz.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lz on 2016/5/6.
 */
public class listview extends Activity {
    ListView listview;

    List<user> list =new  ArrayList<user>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lv);

        listview= (ListView) findViewById(R.id.lv_);


        for (int i=0;i<20;i++){
            String s="name";
            String s1= "zy"+i;
            user u =new user(s1,"","");
            list.add(u);
        }
//        SimpleAdapter sa =new SimpleAdapterAdapter(this,list,R.layout.hh,new String[]{"name","age"},new int[]{R.id.hh_name_tv,R.id.hh_age_tv});
        MyAdapter ad =new MyAdapter(this,list);
        listview.setAdapter(ad);
    }
}
