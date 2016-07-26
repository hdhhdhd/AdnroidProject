package com.example.lz.student;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lz.student.SQL.Studentdb;
import com.example.lz.student.entity.Student;

/**
 * Created by lz on 2016/5/17.
 */
public class DeleteInfo extends BaseActivity implements View.OnClickListener{
    EditText find;
    Button btn;
    Studentdb studentdb;

    @Override
    protected void getView() {
        setContentView(R.layout.delete);
        find = (EditText) findViewById(R.id.delete_et);
        btn = (Button) findViewById(R.id.delete_btn);
    }

    @Override
    protected void setView() {
       btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.delete_btn:
                String name=find.getText().toString();
                studentdb =new Studentdb(this);
                Student student =studentdb.find(name);
                if (student!=null) {
                    studentdb.delete(name);
                    Toast.makeText(this,"删除成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this,"学生不存在",Toast.LENGTH_SHORT).show();
                }
             break;
        }
    }
}
