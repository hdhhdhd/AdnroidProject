package com.example.lz.numberlist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.lz.numberlist.Adapter.MyAdapter1;
import com.example.lz.numberlist.SQL.NumberInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lz on 2016/5/12.
 */
public class SecMenu extends BaseActivity{
    MyAdapter1 ma;
    ListView lv;
    List<NumberInfo> list;
    SQLiteDatabase db;
    File file;

    @Override
    protected void getView() {
      setContentView(R.layout.secmenu);
        lv = (ListView) findViewById(R.id.secmenu_lv);
       Intent in  =this.getIntent();
        list = new ArrayList<NumberInfo>();
        Bundle b  =in.getExtras();
        int index = b.getInt("index");
        if (Environment.getExternalStorageDirectory().equals(Environment.MEDIA_MOUNTED)) {
            file = new File("data"+File.separator+"data"+File.separator+Environment.getDataDirectory() + File.separator + "hh" +File.separator+ "lpk.db");
        } else {
            file = new File("data"+File.separator+"data"+File.separator+getPackageName() + File.separator + "hh" +File.separator+ "lpk.db");
        }
        db =Tools.openSQL(file);
        Cursor cursor = db.rawQuery("select * from table"+index,null);
        if (cursor!=null){

            while (cursor.moveToNext()){
                String num = cursor.getString(cursor.getColumnIndex("number"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                NumberInfo ni= new NumberInfo(num,name);
                list.add(ni);
            }
        }
        if (cursor!=null){
            cursor.close();
        }
        if (db!=null) {
            db.close();
        }
        ma  = new MyAdapter1(this,list);
        lv.setAdapter(ma);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder ab =new AlertDialog.Builder(SecMenu.this);
                final NumberInfo numberInfo =list.get(position);
                ab.setTitle("提示");
                ab.setMessage("是否拨打"+numberInfo.getName()+"的电话:"+numberInfo.getNumber());
                ab.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent in =new Intent();
                        in.setAction(Intent.ACTION_CALL);
                        in.setData(Uri.parse("tel://"+numberInfo.getNumber()));
                            startActivity(in);
                    }
                });
                ab.setPositiveButton("取消", null);
                ab.create().show();
            }
        });
    }

    @Override
    protected void setView() {

    }
}
