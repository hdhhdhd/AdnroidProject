package com.example.lz.myapplication1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by lz on 2016/5/7.
 */
public class AddInfo extends Activity implements View.OnClickListener {
    EditText name, age, major, teacher, degree, grade;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getview();
        setview();
    }

    protected void getview() {
        setContentView(R.layout.addinfo);
        name = (EditText) findViewById(R.id.adinfo_name_ed);
        age = (EditText) findViewById(R.id.adinfo_age_ed);
        major = (EditText) findViewById(R.id.adinfo_major_ed);
        teacher = (EditText) findViewById(R.id.adinfo_teacher_ed);
        degree = (EditText) findViewById(R.id.adinfo_degree_ed);
        grade = (EditText) findViewById(R.id.adinfo_grade_ed);
        btn = (Button) findViewById(R.id.adinfo_sure_btn);
    }

    protected void setview() {
         btn.setOnClickListener(this);
    }

    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确定提交吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name1 = name.getText().toString();
                String age1 = age.getText().toString();
                String major1 = major.getText().toString();
                String teacher1 = teacher.getText().toString();
                String degree1 = degree.getText().toString();
                String grade1 = grade.getText().toString();
                Student stu = new Student(name1, age1, major1, teacher1, degree1, grade1);
                if (name1.length() > 0 && age1.length() > 0 && major1.length() > 0 && teacher1.length() > 0 &&
                        degree1.length() > 0 && grade1.length() > 0) {
                    Intent in = new Intent(AddInfo.this, Info.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("student", stu);
                    in.putExtras(bundle);
                    AddInfo.this.startActivity(in);
                } else {
                    Toast.makeText(AddInfo.this, "请输入完整信息", Toast.LENGTH_SHORT).show();
                    name.setText("");
                    age.setText("");
                    major.setText("");
                    teacher.setText("");
                    degree.setText("");
                    grade.setText("");
                }
                dialog.dismiss();
                Toast.makeText(AddInfo.this, "添加成功", Toast.LENGTH_SHORT).show();

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.adinfo_sure_btn:

                dialog();
        }
    }
}

