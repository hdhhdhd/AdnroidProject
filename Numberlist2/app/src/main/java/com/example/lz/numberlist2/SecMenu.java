package com.example.lz.numberlist2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.lz.numberlist2.Adapter.NumberAdapter1;
import com.example.lz.numberlist2.data.NumberInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lz on 2016/5/13.
 */
public class SecMenu extends BaseActivity{
    NumberAdapter1 ma;
    ListView lv;
    List<NumberInfo> list;
    File file;
    @Override
    protected void getView() {
      setContentView(R.layout.secmenu);
        lv = (ListView) findViewById(R.id.secmenu_lv);
        list =new ArrayList<NumberInfo>();
        Intent in =this.getIntent();
        Bundle b =in.getExtras();
        int index =b.getInt("index");
        if (Environment.getExternalStorageDirectory().equals(Environment.MEDIA_MOUNTED)){
          file=new File("data"+File.separator+"data"+File.separator+Environment.getDataDirectory()+File.separator+"NumberInfo");
        }else {
            file =new File("data"+File.separator+"data"+File.separator+getPackageName() + File.separator +"NumberInfo.db");
        }
        list=Tools.getNumberInfo(file,index);
        ma =new NumberAdapter1(list,this);
        lv.setAdapter(ma);
    }



    @Override
    protected void setView() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder ab =new AlertDialog.Builder(SecMenu.this);
                final NumberInfo ni =list.get(position);
                ab.setTitle("提示");
                ab.setMessage("是否拨打"+ni.getName()+"的号码:"+ni.getNumber());
                ab.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent in =new Intent();
                        in.setAction(Intent.ACTION_CALL);
                        in.setData(Uri.parse("tel://"+ni.getNumber()));
                        startActivity(in);
                    }
                });
                ab.setPositiveButton("取消",null);
                ab.create().show();
            }
        });
    }
}
