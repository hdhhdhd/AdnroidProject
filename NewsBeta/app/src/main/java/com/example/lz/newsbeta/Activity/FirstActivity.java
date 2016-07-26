package com.example.lz.newsbeta.Activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.example.lz.newsbeta.Adapter.ViewPagerAdapter;
import com.example.lz.newsbeta.R;
import com.example.lz.newsbeta.SaveMessage.SaveMessage;
import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener{
    ViewPager viewPager;
    ViewPagerAdapter va;
    Button btn;
    List<View> list;
    SaveMessage sm;
    View view,pager,pager1,pager2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Loading();
    }
    public void Loading(){
        setContentView(R.layout.activity_first);
        sm=new SaveMessage(this);
        String a =sm.getMessage();
        if (a.equals("假")) {
            getView();
            setView();
        } else {
            Intent intent = new Intent(this, Loading.class);
            startActivity(intent);
            finish();
        }
    }
    public void getView(){
        view =findViewById(R.id.first_vp);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager_vp);
        btn = (Button) findViewById(R.id.viewpager_pass_btn);
        pager= LayoutInflater.from(this).inflate(R.layout.viewpageritem,null);
        pager1=LayoutInflater.from(this).inflate(R.layout.viewpageritem1,null);
        pager2 =LayoutInflater.from(this).inflate(R.layout.viewpageritem2,null);
    }
    public void setView(){
        getList();
        sm.setMessage();
        va=new ViewPagerAdapter(list);
        viewPager.setAdapter(va);
        setListener();
    }
    public void getList(){
        list = new ArrayList<View>();
        list.add(pager);
        list.add(pager1);
        list.add(pager2);
    }
    public void setListener(){
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
              switch (position){
                  case 0:
                      btn.setVisibility(View.GONE);
                      break;
                  case 1:

                      btn.setVisibility(View.GONE);
                      break;
                  case 2:
                      btn.setVisibility(View.VISIBLE);
                      break;
              }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.viewpager_pass_btn:
                Intent in = new Intent(this, Loading.class);
                startActivity(in);
                finish();
                break;
        }
    }
}
