package com.example.lz.student;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lz.student.SQL.Studentdb;
import com.example.lz.student.entity.Student;

/**
 * Created by lz on 2016/5/17.
 */
public class ShowFindInfo extends BaseActivity implements View.OnClickListener{
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
        Student student = (Student) b.getSerializable("find");
        name.setText(student.getName());
        age.setText(student.getAge());
        major.setText(student.getMajor());
        teacher.setText(student.getTeacher());
        degree.setText(student.getDegree());
        grade.setText(student.getGrade());
    }

    @Override
    public void onClick(View v) {

    }
}
