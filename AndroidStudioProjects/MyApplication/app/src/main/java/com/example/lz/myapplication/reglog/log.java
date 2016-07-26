package com.example.lz.myapplication.reglog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lz.myapplication.R;

/**
 * Created by lz on 2016/5/5.
 */
public class log extends baseActivity implements View.OnClickListener {
    Button btn;
    EditText username,password;
    user u;
    @Override
    public void getview() {
        setContentView(R.layout.log);
        username = (EditText) findViewById(R.id.log_username_et);
        password = (EditText) findViewById(R.id.log_password_et);
        btn = (Button) findViewById(R.id.log_log_btn);

    }

    @Override
    public void setview() {
         btn.setOnClickListener(this);
    }
    protected void dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确认登陆吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(log.this,"登陆成功",Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.log_log_btn:
               Tools tools =new Tools();
               Intent in = this.getIntent();
               u = (user) in.getSerializableExtra("user");
               for (int i=0;i<tools.l.size();i++) {
                   if (u.equals(tools.l.get(i))) {
                       dialog();
                   } else {
                       Toast.makeText(this, "用户名或密码有误", Toast.LENGTH_SHORT).show();
                   }
               }
               break;
       }
    }
}
