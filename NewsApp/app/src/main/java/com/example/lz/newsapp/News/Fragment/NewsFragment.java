package com.example.lz.newsapp.News.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lz.newsapp.News.Adapter.NewsFragmentAdapter;
import com.example.lz.newsapp.News.BaseFragment;
import com.example.lz.newsapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lz on 2016/7/3.
 */
public class NewsFragment extends BaseFragment{
    TabLayout tabLayout;
    View view;
    TextView textView;
    NewsFragmentAdapter na;
    List<String> tabname;
    List<Fragment> fragmentList;
    ViewPager viewPager;
    @Nullable

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.news,null);
        return view;
    }

    @Override
    public void getview() {
        tabLayout= (TabLayout) getActivity().findViewById(R.id.news_tab);
        viewPager= (ViewPager) getActivity().findViewById(R.id.news_viewpager);
    }

    @Override
    public void setview() {
        setlist();
        na=new NewsFragmentAdapter(getFragmentManager(),tabname,fragmentList);
        viewPager.setAdapter(na);
        tabLayout.setTabsFromPagerAdapter(na);
        tabLayout.setupWithViewPager(viewPager);
        na.notifyDataSetChanged();

    }
    public void setlist(){
        tabname=new ArrayList<String>();
        tabname.add("头条");
        tabname.add("社会");
        tabname.add("国内");
        tabname.add("国际");
        tabname.add("科技");
        tabname.add("军事");
        fragmentList=new ArrayList<Fragment>();
        fragmentList.add(new SocietyNews());
        fragmentList.add(new InternationalNews());
        fragmentList.add(new WarFragment());
        fragmentList.add(new TechnologyNews());
        fragmentList.add(new CNNews());
        fragmentList.add(new HotNewsFragment());

    }
}
