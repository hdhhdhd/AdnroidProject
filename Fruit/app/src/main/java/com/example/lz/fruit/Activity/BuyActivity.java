package com.example.lz.fruit.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lz.fruit.BaseActivity;
import com.example.lz.fruit.DB.ShopingCarDb;
import com.example.lz.fruit.R;
import com.example.lz.fruit.Tools.Tools;
import com.example.lz.fruit.entity.Fruit;

/**
 * Created by lz on 2016/7/6.
 */
public class BuyActivity extends BaseActivity implements View.OnClickListener{
    View view;
    ImageView iv;
    TextView info,name;
    Button minus,add,buy;
    EditText sum;
    Fruit fruit;
    ShopingCarDb scd;
    @Override
    public void getView() {
        setContentView(R.layout.buyactivity);
        view=findViewById(R.id.buyactivity_title);
        iv= (ImageView) findViewById(R.id.buy_iv);
        info = (TextView) findViewById(R.id.buy_info_tv);
        name = (TextView) findViewById(R.id.buy_name_tv);
        minus= (Button) findViewById(R.id.buy_minus_btn);
        add = (Button) findViewById(R.id.buy_add_btn);
        buy = (Button) findViewById(R.id.buy_btn);
        sum = (EditText) findViewById(R.id.buy_sum_et);
    }

    @Override
    public void setView() {
        scd =new ShopingCarDb(this);
        getData();
        iv.setImageResource(fruit.getImage());
        info.setText(fruit.getInfo());
        name.setText(fruit.getName());
        Tools.setTitle(view,this);
        minus.setOnClickListener(this);
        add.setOnClickListener(this);
        buy.setOnClickListener(this);
        sum.setText("0");
    }
    public void getData(){
        Intent intent =this.getIntent();
        if (intent!=null) {
            Bundle bundle = intent.getExtras();
            fruit = (Fruit) bundle.getSerializable("fruit");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buy_minus_btn:
                int count=Integer.parseInt(sum.getText().toString());
                if (count<0){
                    count=0;
                }else {
                    count--;
                }
                sum.setText(count+"");
                break;
            case R.id.buy_add_btn:
                int count1=Integer.parseInt(sum.getText().toString());
                if (count1>fruit.getAllnum()){
                    Toast.makeText(this,"没有库存了",Toast.LENGTH_SHORT);
                }else {
                    count1++;
                }
                sum.setText(count1 + "");
                break;
            case R.id.buy_btn:
                String num =sum.getText().toString();
                int num1= Integer.parseInt(num);
                fruit.setSum(num1);
                scd.add(fruit);
                finish();
                break;
        }

    }
}
