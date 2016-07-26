package com.example.lz.student;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lz.student.MyAdapter.MyAdapter;
import com.example.lz.student.MyAdapter.MyAdapter1;
import com.example.lz.student.SQL.Studentdb;
import com.example.lz.student.SQL.Userdb;
import com.example.lz.student.entity.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lz on 2016/5/17.
 */
public class DetailInfo extends BaseActivity{
TextView name,age,major,teacher,degree,grade;

    @Override
    protected void getView() {
       setContentView(R.layout.allinfolv);
        name= (TextView) findViewById(R.id.allinfo_name_tv);
        age= (TextView) findViewById(R.id.allinfo_age_tv);
        major = (TextView) findViewById(R.id.allinfo_major_tv);
        teacher = (TextView) findViewById(R.id.allinfo_teacher_tv);
        degree = (TextView) findViewById(R.id.allinfo_degree_tv);
        grade = (TextView) findViewById(R.id.allinfo_grade_tv);
    }

    @Override
    protected void setView() {
        Intent in =this.getIntent();
        Bundle b=in.getExtras();
        if (b!=null) {
            Student student = (Student) b.getSerializable("stu");
            name.setText(student.getName());
            age.setText(student.getAge());
            major.setText(student.getMajor());
            teacher.setText(student.getTeacher());
            degree.setText(student.getDegree());
            grade.setText(student.getGrade());
        }
    }
}
