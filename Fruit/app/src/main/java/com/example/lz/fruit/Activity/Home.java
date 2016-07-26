package com.example.lz.fruit.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.example.lz.fruit.BaseActivity;
import com.example.lz.fruit.BaseFragmentActivity;
import com.example.lz.fruit.DB.FruitDb;
import com.example.lz.fruit.Fragment.FruitFragment;
import com.example.lz.fruit.Fragment.SettingFragment;
import com.example.lz.fruit.Fragment.ShoppingCarFragment;
import com.example.lz.fruit.R;
import com.example.lz.fruit.entity.Fruit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lz on 2016/7/5.
 */
public class Home extends BaseFragmentActivity implements View.OnClickListener{
    FragmentManager fm;
    FragmentTransaction ft;
    View fruit,shoppingcar,setting;
    FruitDb fd;
    List<Fruit> list;
    @Override
    public void getView() {
    setContentView(R.layout.home);
     fruit =findViewById(R.id.homr_fruit_rl);
        shoppingcar =findViewById(R.id.home_shoppingcar_rl);
        setting=findViewById(R.id.home_setting_rl);
        fd =new FruitDb(this);
        list =fd.find();
        if (list.size()<0){
            Data();
        }
    }

    @Override
    public void setView() {
        fm = getSupportFragmentManager();
        ft=fm.beginTransaction();
        ft.replace(R.id.home_fragment,new FruitFragment());
        ft.commit();
        fruit.setOnClickListener(this);
        shoppingcar.setOnClickListener(this);
        setting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.homr_fruit_rl:
                ft=fm.beginTransaction();
                ft.replace(R.id.home_fragment,new FruitFragment());
                break;
            case R.id.home_shoppingcar_rl:
                ft=fm.beginTransaction();
                ft.replace(R.id.home_fragment,new ShoppingCarFragment());
                break;
            case R.id.home_setting_rl:
                ft=fm.beginTransaction();
                ft.replace(R.id.home_fragment,new SettingFragment());
                break;
        }
        ft.commit();
    }
    public void Data(){
        Fruit apple =new Fruit("苹果","This is Fucking apple",0,4,R.mipmap.apple);
        Fruit orange=new Fruit("桔子","This is Fucking orange",0,5,R.mipmap.orange);
        Fruit lemon =new Fruit("柠檬","This is Fucking lemon",0,6,R.mipmap.lemon);
        Fruit mihoutao =new Fruit("猕猴桃","This is Fucking mihoutao",0,7,R.mipmap.mihoutao);
        fd.add(apple);
        fd.add(orange);
        fd.add(lemon);
        fd.add(mihoutao);
    }
}
