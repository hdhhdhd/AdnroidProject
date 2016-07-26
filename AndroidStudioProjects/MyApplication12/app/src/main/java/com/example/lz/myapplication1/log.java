package com.example.lz.myapplication1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by lz on 2016/5/7.
 */
public class log extends Activity implements View.OnClickListener{
    Button btn;
    EditText username,password;
    User u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getview();
        setview();
    }

    public void getview() {
        setContentView(R.layout.log);
        username = (EditText) findViewById(R.id.log_username_et);
        password = (EditText) findViewById(R.id.log_password_et);
        btn = (Button) findViewById(R.id.log_log_btn);

    }


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
                Intent in =new Intent(log.this,menu2.class);
                dialog.dismiss();
                Toast.makeText(log.this,"登陆成功",Toast.LENGTH_SHORT).show();
                startActivity(in);
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
                u = (User) in.getSerializableExtra("user");
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
