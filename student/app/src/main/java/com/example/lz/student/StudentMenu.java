package com.example.lz.student;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lz.student.Tools.Tools;

/**
 * Created by lz on 2016/5/17.
 */
public class StudentMenu extends BaseActivity implements View.OnClickListener {
    TextView show;
    Button out;
    @Override
    protected void getView() {
        setContentView(R.layout.studentmenu);
        show = (TextView) findViewById(R.id.studentmenu_show_tv);
        out = (Button) findViewById(R.id.studentmenu_out_btn);
    }

    @Override
    protected void setView() {
        show.setOnClickListener(this);
        out.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.studentmenu_show_tv:
                Tools.goTo(this, AllInfo.class);
                break;
            case R.id.studentmenu_out_btn:
                Tools.goTo(this,reglog.class);
                break;
        }
    }
}
