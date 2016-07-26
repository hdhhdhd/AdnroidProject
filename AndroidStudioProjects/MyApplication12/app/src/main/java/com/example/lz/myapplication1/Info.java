package com.example.lz.myapplication1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;


/**
 * Created by lz on 2016/5/7.
 */
public class Info extends Activity {
    ListView lvw ;
    MyAdapter ma;
    Tools tools;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getview();
       setview();
    }
    protected void getview(){
        tools=new Tools();
        setContentView(R.layout.lv);
        lvw= (ListView) findViewById(R.id.lv_lv);
        Intent in =this.getIntent();
        Bundle b=in.getExtras();
        if(b!=null) {
            Student stu = (Student) b.getSerializable("student");
//        Student stu = (Student) in.getSerializableExtra("student");
            tools.liststu.add(stu);
            ma = new MyAdapter(this, tools.liststu);
            lvw.setAdapter(ma);
        }
    }
    protected void setview(){
         tools =new Tools();
        lvw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student  stu=tools.liststu.get(position);
                Intent in = new Intent(Info.this,details.class);
                Bundle bundle =new Bundle();
                bundle.putSerializable("stu",stu);
                bundle.putInt("index",position);
                in.putExtras(bundle);
                startActivity(in);
            }
        });
        lvw.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(Info.this);
                builder.setTitle("提示");
                builder.setMessage("是否删除?");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tools.liststu.remove(position);
                        ma.notifyDataSetChanged();
                        lvw.invalidate();
                        dialog.dismiss();
                        Toast.makeText(Info.this,"删除成功",Toast.LENGTH_SHORT).show();
                    }
                });
               builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       dialog.dismiss();
                   }
               });
                builder.create().show();
                return true;
            }
        });
    }



}
