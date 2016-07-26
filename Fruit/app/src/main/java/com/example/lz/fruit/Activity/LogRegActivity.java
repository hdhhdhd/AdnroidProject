package com.example.lz.fruit.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lz.fruit.BaseActivity;
import com.example.lz.fruit.DB.UserDb;
import com.example.lz.fruit.R;
import com.example.lz.fruit.entity.User;

import java.util.List;


public class LogRegActivity extends BaseActivity {
    View view;
    EditText username,password;
    TextView typetv;
    Spinner type;
    Button btn;
    UserDb userDb;
    User user,user1;
    Intent in;
    int a=0;
    String name,pwd;
    @Override
    public void getView() {
        setContentView(R.layout.logreg);
        view =findViewById(R.id.logreg_view);
        username = (EditText) view.findViewById(R.id.logreg_username_et);
        password = (EditText) view.findViewById(R.id.logreg_password_et);
        type = (Spinner) view.findViewById(R.id.logreg_type_sp);
        typetv = (TextView) view.findViewById(R.id.logreg_type_tv);
        btn = (Button) view.findViewById(R.id.logreg_btn);
    }

    @Override
    public void setView() {
   btn.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           if (a==0){
               Log();
           }else {
               Reg();
           }

       }
   });
    }
    public void Log() {
        userDb = new UserDb(this);
         name = username.getText().toString();
         pwd = password.getText().toString();
        if (name != null && pwd != null) {
            user = new User(name, pwd);
            user1=userDb.find(name);
            if (user1!=null){
                if (user.equals(user1)){
                   in=new Intent(this,Home.class);
                    Bundle bundle =new Bundle();
                    bundle.putString("username",name);
                    in.putExtras(bundle);
                    startActivity(in);
                    finish();
                }else if (!user.getPassword().equals(user1.getPassword())){
                    Toast.makeText(this,"密码错误",Toast.LENGTH_SHORT).show();
                }
            }else {
                Reg();
            }
        }else {
            Toast.makeText(this,"用户名/密码不能为空",Toast.LENGTH_SHORT).show();
        }
    }
    public void Reg() {
        if (name != null && pwd != null) {
            if (a == 0) {
                type.setVisibility(View.VISIBLE);
                typetv.setVisibility(View.VISIBLE);
                Toast.makeText(this, "用户名不存在，请先注册", Toast.LENGTH_SHORT).show();
                username.setText(null);
                password.setText(null);
                a = 1;
            } else {
                name =username.getText().toString();
                user1=userDb.find(name);
                if (user1==null) {
                    String typestr = type.getSelectedItem().toString();
                    user.setType(typestr);
                    userDb.add(user);
                    in=new Intent(this,Home.class);
                    Bundle bundle =new Bundle();
                    bundle.putString("username",name);
                    in.putExtras(bundle);
                    startActivity(in);
                    finish();
                    Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "用户名已存在", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }


}
