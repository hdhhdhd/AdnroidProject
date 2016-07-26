package com.example.lz.numberlist3;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lz.numberlist3.MyAdapter.MyAdapter;
import com.example.lz.numberlist3.data.Classlist;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lz on 2016/5/13.
 */
public class Mainmenu extends BaseActivity{
    ListView lv;
    List<Classlist> list;
    MyAdapter ma;
    File file;
    boolean b;
    @Override
    protected void getView() {
        setContentView(R.layout.mainmenu);
        lv = (ListView) findViewById(R.id.mainmenu_lv);
        b =false;
        if (Environment.getExternalStorageDirectory().equals(Environment.MEDIA_MOUNTED)){
            file = new File("data"+File.separator+"data"+File.separator+Environment.getDataDirectory()+File.separator+"NumberInfo");

        } else {
            file = new File("data"+File.separator+"data"+File.separator+getPackageName() + File.separator +"NumberInfo.db");
        }
        Tools.Createfile(file);
        Tools.Copy(this,"commonnum.db",file);
        list=new ArrayList<Classlist>();

        list=Tools.getClasslist(file);
        ma=new MyAdapter(this,list);
        list.add(0,new Classlist("本地电话",0));
        lv.setAdapter(ma);
    }

    @Override
    protected void setView() {
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
                    b.putInt("index",index);
                    in.putExtras(b);

                }
                startActivity(in);
            }

        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                if (b){
                    finish();
                }else {
                    b=!b;
                    Toast.makeText(this,"再按一次退出",Toast.LENGTH_SHORT).show();
                    return true;
                }
                break;

        }
        return super.onKeyDown(keyCode, event);

    }
}
