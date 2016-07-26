package com.example.lz.student;

import android.view.View;
import android.widget.Button;
import android.widget.Button;
import android.widget.Toast;

import com.example.lz.student.SQL.Studentdb;
import com.example.lz.student.Tools.Tools;
import com.example.lz.student.entity.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lz on 2016/5/17.
 */
public class TeacherMenu extends BaseActivity implements View.OnClickListener{
    Button show,add,remove,change,find;
    Studentdb studentdb;
    Button out;
    @Override
    protected void getView() {
        setContentView(R.layout.mianmenu);
        show = (Button) findViewById(R.id.menu_show_tv);
        add = (Button) findViewById(R.id.menu_add_tv);
        remove = (Button) findViewById(R.id.menu_delete_tv);
        change = (Button) findViewById(R.id.menu_change_tv);
        find = (Button) findViewById(R.id.menu_find_tv);
        out = (Button) findViewById(R.id.menu_out_btn);
        studentdb =new Studentdb(this);
    }

    @Override
    protected void setView() {
       show.setOnClickListener(this);
        add.setOnClickListener(this);
        remove.setOnClickListener(this);
        change.setOnClickListener(this);
        find.setOnClickListener(this);
        out.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.menu_show_tv:
                List<Student> list=studentdb.find();
                if (list!=null&&list.size()>1) {
                    Tools.goTo(this, AllInfo.class);
                }else {
                    Toast.makeText(this,"没有学生信息",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.menu_add_tv:
                Tools.goTo(this,AddInfo.class);
                break;
            case R.id.menu_delete_tv:
                Tools.goTo(this,DeleteInfo.class);
                break;
            case R.id.menu_change_tv:
                Tools.goTo(this,FindChange.class);
                break;
            case R.id.menu_find_tv:
                Tools.goTo(this,FindInfo.class);
                break;
            case R.id.menu_out_btn:
                Tools.goTo(this,reglog.class);
                break;
        }
    }
}
