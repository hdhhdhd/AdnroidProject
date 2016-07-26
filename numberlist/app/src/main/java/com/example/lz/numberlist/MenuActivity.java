package com.example.lz.numberlist;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lz.numberlist.Adapter.MyAdapter;
import com.example.lz.numberlist.SQL.Classlist;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lz on 2016/5/10.
 */
public class MenuActivity extends BaseActivity{
    ListView lv;
    File file;
    List<Classlist> list;
    MyAdapter ma ;
    SQLiteDatabase db;
    Boolean flag;
    @Override
    protected void getView() {
        flag =false;
       setContentView(R.layout.mainlv);
        list =new ArrayList<Classlist>();
        if (Environment.getExternalStorageDirectory().equals(Environment.MEDIA_MOUNTED)) {
            file = new File("data"+File.separator+"data"+File.separator+Environment.getDataDirectory() + File.separator + "hh" +File.separator+ "lpk.db");

        } else {
            file = new File("data"+File.separator+"data"+File.separator+getPackageName() + File.separator + "hh" +File.separator+ "lpk.db");

        }
        Tools.Create(file);
        getMessage();
        db=Tools.openSQL(file);
        Cursor cursor = db.rawQuery("select * from classlist",null);
        if (cursor!=null){

            while (cursor.moveToNext()){
                int index = cursor.getInt(cursor.getColumnIndex("idx"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                Classlist cl= new Classlist(index,name);
                list.add(cl);
            }
        }
        if (cursor!=null){
            cursor.close();
        }
        if (db!=null) {
            db.close();
        }
        ma =new MyAdapter(this,list);
        lv = (ListView) findViewById(R.id.mainlv_menu_lv);
        list.add(0,new Classlist(0,"本地电话"));
        lv.setAdapter(ma);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in;
                if(position==0){
                    in = new Intent();
                    in.setAction(Intent.ACTION_DIAL);
                }else {
                    Classlist classlist = list.get(position);
                    in = new Intent(MenuActivity.this, SecMenu.class);
                    Bundle b = new Bundle();
                    int index = classlist.getIndex();
                    b.putInt("index", index);
                    in.putExtras(b);
                }
                startActivity(in);
            }
        });


    }

    @Override
    protected void setView() {


        }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                if (flag){
                    finish();

                }else {
                    flag=!flag;
                    Toast.makeText(this,"再按一次退出",Toast.LENGTH_SHORT).show();
                    return true;
                }
                break;
        }
        return super.onKeyDown(keyCode, event);

    }

    protected void getMessage(){
        if (file.exists()&&file.length()<1){
            Tools.Copy(this,"commonnum.db",file);
        }

    }



}
