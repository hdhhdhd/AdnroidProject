package com.example.lz.student;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lz.student.SQL.Studentdb;
import com.example.lz.student.Tools.Tools;
import com.example.lz.student.entity.Student;

import java.io.Serializable;

/**
 * Created by lz on 2016/5/17.
 */
public class FindInfo extends BaseActivity implements View.OnClickListener{
    EditText find;
    Button btn;
    Studentdb studentdb;

    @Override
    protected void getView() {
        setContentView(R.layout.find);
        find = (EditText) findViewById(R.id.find_et);
        btn = (Button) findViewById(R.id.find_btn);
    }

    @Override
    protected void setView() {
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.change_btn:
                String name=find.getText().toString();
                studentdb =new Studentdb(this);
                Student student =studentdb.find(name);
                if (student!=null) {
                    Intent in =new Intent(this,ShowFindInfo.class);
                    Bundle b=new Bundle();
                    b.putSerializable("find",student);
                    in.putExtras(b);
                    startActivity(in);
                }else {
                    Toast.makeText(this,"学生不存在",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
