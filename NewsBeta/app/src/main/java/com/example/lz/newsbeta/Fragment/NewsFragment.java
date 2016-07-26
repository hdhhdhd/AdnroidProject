package com.example.lz.newsbeta.Fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.lz.newsbeta.Activity.AddNewsActivity;
import com.example.lz.newsbeta.Adapter.NewsFragmentAdapter;
import com.example.lz.newsbeta.BaseFragment;
import com.example.lz.newsbeta.Entity.News;
import com.example.lz.newsbeta.Entity.VolleySingleton;
import com.example.lz.newsbeta.R;
import com.example.lz.newsbeta.Tools.SpfManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lz on 2016/7/10.
 */
public class NewsFragment extends BaseFragment implements View.OnClickListener{
    TabLayout tabLayout;
    View view;
    List<String> tabname,getname;
    List<Fragment> fragmentList;
   ImageView addnews;
    NewsContentFragment newsContentFragment[];
    SpfManager spfManager;
     MyReceiver mr;
    List<Integer> but_id_list;
    ViewPager viewPager;
    NewsFragmentAdapter na;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.newsfragment, null);
        putView();
        setView();
        return view;
    }


    @Override
    public void putView() {

        fragmentList = new ArrayList<Fragment>();
        newsContentFragment = new NewsContentFragment[6];
        tabLayout = (TabLayout) view.findViewById(R.id.news_tab);
        viewPager = (ViewPager) view.findViewById(R.id.news_viewpager);
       addnews = (ImageView) view.findViewById(R.id.news_addtips_iv);
        spfManager = SpfManager.getspfManager(getContext());
    }

    @Override
    public void setView() {
        getTitle();
        setlist();
        addnews.setOnClickListener(this);
        na =new NewsFragmentAdapter(getFragmentManager());

        na.setList(fragmentList);
        na.setTabnamelist(getname);
        viewPager.setAdapter(na);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(getname.size());
        na.notifyDataSetChanged();

    }
    public void getTitle(){
        tabname=new ArrayList<String>();
        but_id_list=new ArrayList<Integer>();
        but_id_list.add(R.id.add_news_toutiao);
        but_id_list.add(R.id.add_news_shehui);
        but_id_list.add(R.id.add_news_guonei);
        but_id_list.add(R.id.add_news_guoji);
        but_id_list.add(R.id.add_news_keji);
        but_id_list.add(R.id.add_news_junshi);
        tabname.add("头条");
        tabname.add("社会");
        tabname.add("国内");
        tabname.add("国际");
        tabname.add("科技");
        tabname.add("军事");
    }
    @Override
    public void onStart() {
        super.onStart();
        if (mr==null){
            mr=new MyReceiver();
            IntentFilter intentFilter=new IntentFilter();
            intentFilter.addAction("changed");
            getActivity().registerReceiver(mr,intentFilter);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(mr);
    }

    public void setlist(){
        getname=new ArrayList<String>();
        String one= spfManager.isone();
        //是否登录
        if (one.equals("yesone")){
            //没有登录则设置所有tab为显示状态
            for (int i =0;i<but_id_list.size();i++){
                spfManager.saveshow(but_id_list.get(i)+"","yes");
            }
            spfManager.saveone("noone");
        }
        //从共享参数中获取状态为显示的tab
        for (int i =0;i<but_id_list.size();i++){
            String isshow= spfManager.getshow(but_id_list.get(i)+"");
            Log.i("msg",but_id_list.get(i)+""+isshow+"");
            if (isshow.equals("yes")){
                getname.add(tabname.get(i));
            }
        }
        fragmentList=new ArrayList<Fragment>();
        //循环添加tab
        for (int i=0;i<getname.size();i++){
            NewsContentFragment newsfragment=new NewsContentFragment();

            switch (getname.get(i)){
                case "头条":
                    newsfragment.setContent("top");
                    break;
                case "社会":
                    newsfragment.setContent("shehui");
                    break;
                case "国内":
                    newsfragment.setContent("guonei");
                    break;
                case "国际":
                    newsfragment.setContent("guoji");
                    break;
                case "科技":
                    newsfragment.setContent("keji");
                    break;
                case "军事":
                    newsfragment.setContent("junshi");
                    break;
            }
            fragmentList.add(newsfragment);

        }
        Log.i("num",fragmentList.size()+"");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.news_addtips_iv:
                Intent i = new Intent(getActivity(), AddNewsActivity.class);
                startActivity(i);
                break;
        }
    }

    class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String content = intent.getAction();
            if (content.equals("changed")) {
                getTitle();
                setlist();

                tabLayout.setupWithViewPager(viewPager);
                na.setList(fragmentList);
                na.setTabnamelist(tabname);
                viewPager.setAdapter(na);
                na.notifyDataSetChanged();
                viewPager.setOffscreenPageLimit(tabname.size());
            }
        }
    }
}
