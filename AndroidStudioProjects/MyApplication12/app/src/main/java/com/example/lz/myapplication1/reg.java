package com.example.lz.myapplication1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by lz on 2016/5/7.
 */
public class reg extends Activity implements View.OnClickListener{
    Tools tools =new Tools();
    Button btn;
    EditText username,password,tel,yzm;
    TextView yz;
    String yzm1;
    User u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getview();
        setview();
    }

    public void getview() {
        yzm1=getyzm();
        setContentView(R.layout.reg);
        username = (EditText) findViewById(R.id.re_username_et);
        password = (EditText) findViewById(R.id.re_password_et);
        tel = (EditText) findViewById(R.id.re_tel_et);
        yzm = (EditText) findViewById(R.id.re_yz_et);
        yz = (TextView) findViewById(R.id.re_yzm_tv);
        btn = (Button) findViewById(R.id.re_reg_btn);
        yz.setText(yzm1);
    }

    public void setview() {

        btn.setOnClickListener(this);

    };
    public  String  getyzm(){
        Random rd =new Random();
        int i =rd.nextInt(9000)+1000;
        return i+"";
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.re_reg_btn:
                String s =username.getText().toString();
                String s1 =password.getText().toString();
                String s2 =tel.getText().toString();
                String s3 =yzm.getText().toString();
                u =new User(s,s1,s2);
                if(s.length()>0&&s1.length()>0&&s2.length()>0&&s3.equals(yzm1)) {
                    tools.l.add(u);
                    Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
                    Intent in =new Intent(this,log.class);
                    Bundle bundle =new Bundle();
                    bundle.putSerializable("user",u);
                    in.putExtras(bundle);
                    this.startActivity(in);
                }else {
                    Toast.makeText(this,"请输入完整信息",Toast.LENGTH_SHORT).show();
                    username.setText("");
                    password.setText("");
                    tel.setText("");
                    yz.setText(getyzm());
                }
                break;

        }

    }
}
