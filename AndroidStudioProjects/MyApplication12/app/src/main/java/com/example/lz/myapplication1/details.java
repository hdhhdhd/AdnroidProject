package com.example.lz.myapplication1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by lz on 2016/5/7.
 */
public class details extends Activity implements View.OnClickListener{
    EditText name,age,major,teacher,degree,grade;
    Button btn ;
    Student stu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getview();
        setview();
    }
    protected void getview(){
        setContentView(R.layout.detailinfo);
       name = (EditText) findViewById(R.id.detshow_name_ed);
        age = (EditText) findViewById(R.id.detshow_age_ed);
        major = (EditText) findViewById(R.id.detshow_major_ed);
        teacher = (EditText) findViewById(R.id.detshow_teacher_ed);
        degree = (EditText) findViewById(R.id.detshow_degree_ed);
        grade = (EditText) findViewById(R.id.detshow_grade_ed);
    }
    protected void setview(){
        Intent in =this.getIntent();
        Bundle b =in.getExtras();
        Student stu = (Student) b.getSerializable("stu");
        name.setText(stu.getName());
        age.setText(stu.getAge());
        major.setText(stu.getMajor());
        teacher.setText(stu.getTeacher());
        degree.setText(stu.getDegree());
        grade.setText(stu.getGrade());
        change();
        btn.setOnClickListener(this);
    }
    protected void change(){
        String namestr = name.getText().toString();
        String agestr = age.getText().toString();
        String majorstr = major.getText().toString();
        String teacherstr = teacher.getText().toString();
        String degreestr = degree.getText().toString();
        String gradestr =  grade.getText().toString();
        name.setText(namestr);
        age.setText(agestr);
        major.setText(majorstr);
        teacher.setText(teacherstr);
        degree.setText(degreestr);
        grade.setText(gradestr);
        stu = new Student(namestr,agestr,majorstr,teacherstr,degreestr,gradestr);
    }
    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.det_sure_btn:
               Intent in =this.getIntent();
               Bundle b =in.getExtras();
               if (b!=null) {
                   int pos = b.getInt("index");
                   Tools tools = new Tools();
                   tools.liststu.set(pos, stu);
                   Intent in1 = new Intent(this, Info.class);
                   startActivity(in1);
               }
       }
    }
}
