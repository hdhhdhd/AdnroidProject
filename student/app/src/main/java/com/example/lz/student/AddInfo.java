package com.example.lz.student;


import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lz.student.SQL.Studentdb;
import com.example.lz.student.entity.Student;

/**
 * Created by lz on 2016/5/17.
 */
public class AddInfo extends BaseActivity implements View.OnClickListener{
EditText name,age,teacher;
    Spinner major,degree,grade;
    Studentdb studentdb;
    Button sure;
    Student student;

    @Override
    protected void getView() {

        setContentView(R.layout.addinfo);
        name= (EditText) findViewById(R.id.adinfo_name_ed);
        age= (EditText) findViewById(R.id.adinfo_age_ed);
        teacher = (EditText) findViewById(R.id.adinfo_teacher_ed);
        major = (Spinner) findViewById(R.id.adinfo_major_sp);
        degree = (Spinner) findViewById(R.id.adinfo_degree_sp);
        grade = (Spinner) findViewById(R.id.adinfo_grade_sp);
        sure = (Button) findViewById(R.id.adinfo_sure_btn);
    }

    @Override
    protected void setView() {

        sure.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.adinfo_sure_btn:
                studentdb =new Studentdb(this);
                String name1 =name.getText().toString();
                String age1 =age.getText().toString();
                String teacher1 =teacher.getText().toString();
                String major1 =major.getSelectedItem().toString();
                String degree1 =degree.getSelectedItem().toString();
                String grade1 =grade.getSelectedItem().toString();
                student =new Student(name1,age1,major1,teacher1,degree1,grade1);
                if (name1!=null&&age1!=null&&teacher1!=null&&major1!=null&&degree1!=null&&grade1!=null) {
                    studentdb.add(student);
                    Toast.makeText(this,"添加成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this,"信息不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
