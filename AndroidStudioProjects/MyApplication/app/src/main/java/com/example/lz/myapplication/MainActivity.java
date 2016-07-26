package com.example.lz.myapplication;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class MainActivity extends baseActivity {
    EditText name, age, school;
    TextView tv;
    Button btn;



    @Override
    public void getview() {
        setContentView(R.layout.zwjs);
        name = (EditText) findViewById(R.id.name_et);
        age = (EditText) findViewById(R.id.age_et);
        school = (EditText) findViewById(R.id.school_et);
        btn= (Button) findViewById(R.id.sure_btn);
        tv= (TextView) findViewById(R.id.sure_tv);
    }

    @Override
    public void setview() {

        btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v){
                String s = name.getText().toString();
                String s1 =age.getText().toString();
                String s2 =school.getText().toString();
                    tv.setText("姓名："+s+"\n"+"年龄："+s1+"\n"+"学校："+s2);
            }
        });
    }


    }

