package com.example.lz.newsbeta.Activity;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lz.newsbeta.Entity.News;
import com.example.lz.newsbeta.Fragment.HotFragment;
import com.example.lz.newsbeta.Fragment.NewsFragment;
import com.example.lz.newsbeta.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by lz on 2016/7/8.
 */
public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    LinearLayout bottom1,bottom2,bottom3;
    NewsFragment newsFragment;
    HotFragment hotFragment;
    FragmentManager fm;
    FragmentTransaction ft;
    ImageView iv1,iv2,iv3;
    TextView tv1,tv2,tv3,log;
    List<News> news;
    List<String> type;
    CircleImageView circleImageView;
    int selectimage[] ={R.mipmap.new_selected,R.mipmap.collect_selected,R.mipmap.find_selected};
    int defaultimage[] ={R.mipmap.new_unselected,R.mipmap.collect_unselected,R.mipmap.find_defult};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getView();
        setView();    }

    public void getView() {
        setContentView(R.layout.home_activity);
        findView();
    }

    public void setView() {

        toolbar.setTitle("NewsApp");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        actionBarDrawerToggle =new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        actionBarDrawerToggle.syncState();
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        bottom1.setOnClickListener(this);
        bottom2.setOnClickListener(this);
        bottom3.setOnClickListener(this);
        newsFragment = new NewsFragment();
        hotFragment =new HotFragment();
        fm=getSupportFragmentManager();
        iv1.setImageResource(selectimage[0]);
        ft =fm.beginTransaction();
        tv1.setTextColor(getResources().getColor(R.color.orange));
        ft.add(R.id.home_fragmrntpage_rl,newsFragment).add(R.id.home_fragmrntpage_rl,hotFragment).hide(newsFragment).hide(hotFragment).show(newsFragment).commit();
    }

    public void findView(){
        toolbar= (Toolbar) findViewById(R.id.home_toolbar);
        drawerLayout= (DrawerLayout) findViewById(R.id.home_dl);
       bottom1 = (LinearLayout) findViewById(R.id.home_buttom_one_ll);
        bottom2 = (LinearLayout) findViewById(R.id.home_buttom_two_ll);
        bottom3 = (LinearLayout) findViewById(R.id.home_buttom_three_ll);
        iv1= (ImageView) findViewById(R.id.home_buttom_one_iv);
        iv2 = (ImageView) findViewById(R.id.home_buttom_two_iv);
        iv3 = (ImageView) findViewById(R.id.home_buttom_three_iv);
        tv1= (TextView) findViewById(R.id.home_buttom_one_tv);
        tv2= (TextView) findViewById(R.id.home_buttom_two_tv);
        tv3= (TextView) findViewById(R.id.home_buttom_three_tv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_buttom_one_ll:
                iv1.setImageResource(selectimage[0]);
                iv2.setImageResource(defaultimage[1]);
                iv3.setImageResource(defaultimage[2]);
                tv1.setTextColor(getResources().getColor(R.color.orange));
                tv2.setTextColor(Color.GRAY);
                tv3.setTextColor(Color.GRAY);
                ft =fm.beginTransaction();
                ft.hide(hotFragment).show(newsFragment);
                ft.commit();
                break;
            case R.id.home_buttom_two_ll:
                iv1.setImageResource(defaultimage[0]);
                iv2.setImageResource(selectimage[1]);
                iv3.setImageResource(defaultimage[2]);
                tv2.setTextColor(getResources().getColor(R.color.orange));
                tv1.setTextColor(Color.GRAY);
                tv3.setTextColor(Color.GRAY);
                ft =fm.beginTransaction();
                ft.hide(newsFragment).show(hotFragment);
                ft.commit();
                break;
            case R.id.home_buttom_three_ll:
                iv1.setImageResource(defaultimage[0]);
                iv2.setImageResource(defaultimage[1]);
                iv3.setImageResource(selectimage[2]);
                tv3.setTextColor(getResources().getColor(R.color.orange));
                tv2.setTextColor(Color.GRAY);
                tv1.setTextColor(Color.GRAY);
                ft =fm.beginTransaction();
                ft.hide(newsFragment).hide(hotFragment);
                ft.commit();
                break;
        }
    }



}
