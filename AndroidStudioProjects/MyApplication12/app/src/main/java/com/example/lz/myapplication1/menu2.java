package com.example.lz.myapplication1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by lz on 2016/5/7.
 */
public class menu2 extends Activity implements View.OnClickListener{
    Button add,remove,change,find;
    Intent in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getview();
        setview();
    }
    protected void getview(){
        setContentView(R.layout.menu2);
        add = (Button) findViewById(R.id.menu2_add_btn);
        remove = (Button) findViewById(R.id.menu2_remove_btn);
        change = (Button) findViewById(R.id.menu2_change_btn);
        find = (Button) findViewById(R.id.menu2_find_btn);
    }
    protected void setview(){
        add.setOnClickListener(this);
        remove.setOnClickListener(this);
        change.setOnClickListener(this);
        find.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.menu2_add_btn:
              in=new Intent(this,AddInfo.class);
              startActivity(in);
              break;
          case R.id.menu2_remove_btn:
              in = new Intent(this,Info.class);
              startActivity(in);
              break;
          case R.id.menu2_change_btn:
              in = new Intent(this,Info.class);
              startActivity(in);
              break;
      }
    }
}
