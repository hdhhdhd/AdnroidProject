package com.example.lz.student;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.lz.student.MyAdapter.MyAdapter;
import com.example.lz.student.SQL.Studentdb;
import com.example.lz.student.Tools.Tools;
import com.example.lz.student.entity.Student;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lz on 2016/5/17.
 */
public class AllInfo extends BaseActivity implements View.OnClickListener{
    ListView lv;
    List<Student> list;
    Studentdb studentdb;
    MyAdapter ma;
    @Override
    protected void getView() {
        setContentView(R.layout.details);
        lv = (ListView) findViewById(R.id.details_lv);
    }

    @Override
    protected void setView() {
        studentdb=new Studentdb(this);
        list =new ArrayList<Student>();
        list=studentdb.find();
        ma =new MyAdapter(this,list);
        lv.setAdapter(ma);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in =new Intent(AllInfo.this,DetailInfo.class);
                Bundle b =new Bundle();
                Student student =list.get(position);
                b.putSerializable("stu",student);
                in.putExtras(b);
                startActivity(in);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
