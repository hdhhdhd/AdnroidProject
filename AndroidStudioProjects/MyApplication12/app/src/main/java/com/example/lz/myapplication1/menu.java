package com.example.lz.myapplication1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by lz on 2016/5/7.
 */
public class menu extends Activity implements View.OnClickListener{
    Button btn ;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getview();
        setview();
    }

    public void getview() {
        setContentView(R.layout.menu);
        btn = (Button) findViewById(R.id.menu_reg_btn);
        btn1 = (Button) findViewById(R.id.menu_log_btn);
    }
    public void setview() {
        btn.setOnClickListener(this);
        btn1.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.menu_reg_btn:
                Intent in =new Intent(this,reg.class);
                startActivity(in);
                break;
            case R.id.menu_log_btn:
                Intent in1 =new Intent(this,log.class);
                startActivity(in1);
                break;
        }
    }
}
