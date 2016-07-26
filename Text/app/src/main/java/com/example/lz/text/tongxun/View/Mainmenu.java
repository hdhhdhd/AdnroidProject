package com.example.lz.text.tongxun.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lz.text.BaseActivity;
import com.example.lz.text.R;
import com.example.lz.text.Tools;
import com.example.lz.text.tongxun.NumAdapter.NumberAdapter;
import com.example.lz.text.tongxun.data.Classlist;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lz on 2016/5/22.
 */
public class Mainmenu extends BaseActivity{
    ListView lv;
    List<Classlist> list;
    NumberAdapter ma;
    File file;
    boolean flag;
    View view;
    @Override
    public void getView() {
        setContentView(R.layout.nummenu);
        lv = (ListView) findViewById(R.id.mainmenu_lv);
        list =new ArrayList<Classlist>();
        flag =false;
        view =findViewById(R.id.numtitle_lo);
    }

    @Override
    public void setView() {
        Tools.setTitle(view,"通讯大全",this);
        getData();
        lv.setAdapter(ma);
        setListener();
    }
    public void getData(){
        if (Environment.getExternalStorageDirectory().equals(Environment.MEDIA_MOUNTED)){
            file = new File("data"+File.separator+"data"+File.separator+Environment.getDataDirectory()+File.separator+"NumberInfo");

        } else {
            file = new File("data"+File.separator+"data"+File.separator+getPackageName() + File.separator +"NumberInfo.db");
        }
        Tools.Createfile(file);
        Tools.Copy(this,"commonnum.db",file);
        list= Tools.getClasslist(file);
        ma =new NumberAdapter(this,list);
        list.add(0,new Classlist("本地电话",0));
    }
    public void setListener (){
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in;
                if (position==0){
                    in = new Intent();
                    in.setAction(Intent.ACTION_DIAL);
                }else {
                    in=new Intent(Mainmenu.this,SecMenu.class);
                    Classlist classlist =list.get(position);
                    Bundle b =new Bundle();
                    int index =classlist.getIndex();
                    String title =classlist.getName();
                    b.putInt("index",index);
                    b.putString("title",title);
                    in.putExtras(b);
                }
                startActivity(in);
            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (flag) {
                    finish();
                } else {
                    flag = !flag;
                    Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                    return true;
                }
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
}
