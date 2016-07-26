package com.example.lz.student;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lz.student.SQL.Userdb;
import com.example.lz.student.Tools.SaveMessage;
import com.example.lz.student.Tools.Tools;
import com.example.lz.student.entity.User;

/**
 * Created by lz on 2016/5/16.
 */
public class reglog extends BaseActivity implements View.OnClickListener{
    RelativeLayout rllog,rlreg;
    TextView log,reg;
    EditText username,username1,password,password1;
    Spinner type;
    Button logbtn,regbtn;
    Userdb userdb;
    SaveMessage sm;
    @Override
    protected void getView() {
       setContentView(R.layout.logreg);
        log= (TextView) findViewById(R.id.logreg_log_tv);
        reg = (TextView) findViewById(R.id.logreg_reg_tv);
        logbtn = (Button) findViewById(R.id.logreg_log_btn);
        regbtn = (Button) findViewById(R.id.logreg_reg_btn);
        rllog = (RelativeLayout) findViewById(R.id.log);
        rlreg = (RelativeLayout) findViewById(R.id.reg);
        username = (EditText) findViewById(R.id.log_username_et);
        username1 = (EditText) findViewById(R.id.reg_password_et);
        password = (EditText) findViewById(R.id.log_password_et);
        password1 = (EditText) findViewById(R.id.reg_password_et);
        type = (Spinner) findViewById(R.id.reg_type_sp);
    }

    @Override
    protected void setView() {
        sm =new SaveMessage(this);
         log.setOnClickListener(this);
        reg.setOnClickListener(this);
        logbtn.setOnClickListener(this);
        regbtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        userdb =new Userdb(this);
        switch (v.getId()){
            case R.id.logreg_log_tv:
                rllog.setVisibility(View.VISIBLE);
                rlreg.setVisibility(View.GONE);
                break;
            case R.id.logreg_reg_tv:
                rllog.setVisibility(View.GONE);
                rlreg.setVisibility(View.VISIBLE);
                break;
            case R.id.logreg_log_btn:
                String name = username.getText().toString();
                String pwd =password.getText().toString();

                User user =userdb.find(name);
                User u =sm.getMessage();
                if (u==null) {
                    if (user != null) {
                        String qx = user.gettype();
                        if (pwd.equals(user.getPassword()) && qx.equals("老师")) {
                            Tools.goTo(this, TeacherMenu.class);
                            sm.setMessage(name,pwd,user.gettype());
                        } else if (pwd.equals(user.getPassword()) && qx.equals("学生")) {
                            Tools.goTo(this, StudentMenu.class);
                            sm.setMessage(name,pwd,user.gettype());
                        } else {
                            Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "用户名不存在", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    if (u.gettype().equals("老师")){
                        Tools.goTo(this, TeacherMenu.class);
                    }else {
                        Tools.goTo(this, StudentMenu.class);
                    }
                }
                break;
            case R.id.logreg_reg_btn:
                    String name1 =username1.getText().toString();
                    String pwd1 =password1.getText().toString();
                    String qx = type.getSelectedItem().toString();
                if (name1!=null&&pwd1!=null&&qx!=null&&qx.equals("老师")) {
                    User user1 = new User(name1, pwd1, qx);
                    userdb.add(user1);
                    sm.setMessage(name1,pwd1,qx);
                    Tools.goTo(this,TeacherMenu.class);
                }else if (name1!=null&&pwd1!=null&&qx!=null&&qx.equals("学生")){
                    User user1 = new User(name1, pwd1, qx);
                    userdb.add(user1);
                    sm.setMessage(name1,pwd1,qx);
                    Tools.goTo(this,StudentMenu.class);
                }else {
                    Toast.makeText(this,"信息不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
