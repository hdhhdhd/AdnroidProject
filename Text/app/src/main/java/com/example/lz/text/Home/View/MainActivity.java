package com.example.lz.text.Home.View;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.lz.text.Home.Adapter.MyAdapter;
import com.example.lz.text.Home.SaveMessage.SaveMessage;
import com.example.lz.text.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ViewPager viewPager;
   MyAdapter ma;
    Button pass;
    List<View> list;
    View view1,view2,view3;
    TabLayout tab;
    boolean b;
    SaveMessage sm;
    int index=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       Loading();
    }
    public void Loading(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.viewpager);
        sm=new SaveMessage(this);
        String a =sm.getMessage();
        Intent in =this.getIntent();
        Bundle b =in.getExtras();
        if (b!=null){
            index =b.getInt("index");
        }
        if (index==0) {
            if (a.equals("假")) {
                getView();
                setView();
            } else {
                Intent intent = new Intent(this, Loading.class);
                startActivity(intent);
                finish();
            }
        }else {
            getView();
            setView();
        }
    }
    public  void getView(){
        viewPager = (ViewPager) findViewById(R.id.viewpager_vp);
        view1 = LayoutInflater.from(this).inflate(R.layout.viewpager_item,null);
        view2 =LayoutInflater.from(this).inflate(R.layout.viewpager_item1,null);
        view3 =LayoutInflater.from(this).inflate(R.layout.viewpager_item2,null);
        pass = (Button) findViewById(R.id.viewpager_pass_btn);
        tab = (TabLayout) findViewById(R.id.tab);
    }
    public void setView(){
        getList();
        sm.setMessage();
        pass.setOnClickListener(this);
        viewPager.setAdapter(ma);
        tab.setupWithViewPager(viewPager);
        tab.setTabsFromPagerAdapter(ma);
        tab.setSelectedTabIndicatorColor(Color.parseColor("#00000000"));
        tab.getTabAt(0).setIcon(R.mipmap.adware_style_default);
        setListener();
    }
    public void getList(){
        list = new ArrayList<View>();
        list.add(view1);
        list.add(view2);
        list.add(view3);
        ma =new MyAdapter(list);
    }
    public void setListener(){
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i=0;i<3;i++){
                    if(position==i){
                        tab.getTabAt(i).setIcon(R.mipmap.adware_style_selected);
                    }else{
                        tab.getTabAt(i).setIcon(R.mipmap.adware_style_default);
                    }
                }
                if (position==2){
                    pass.setVisibility(View.VISIBLE);
                }else {
                    pass.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state){
                    case 1:
                        b=true;
                        break;
                    case 2:
                        b=false;
                        break;
                    case 0:
                        if (b&&(viewPager.getCurrentItem()==list.size()-1)){
                            if (index==0) {
                                Intent in = new Intent(MainActivity.this, Loading.class);
                                startActivity(in);
                                finish();
                            }else{
                                MainActivity.this.finish();
                            }
                        }
                        break;
                }
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.viewpager_pass_btn:
                if (index==0) {
                    Intent in = new Intent(this, Loading.class);
                    startActivity(in);
                }else {
                    MainActivity.this.finish();
                }
                break;
        }
    }
}
