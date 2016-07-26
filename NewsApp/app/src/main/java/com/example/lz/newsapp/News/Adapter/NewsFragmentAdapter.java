package com.example.lz.newsapp.News.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by lz on 2016/7/3.
 */
public class NewsFragmentAdapter extends FragmentPagerAdapter {
    List<String> tabnamelist;
    List<Fragment> fragmentList;

    public List<String> getTabnamelist() {
        return tabnamelist;
    }

    public void setTabnamelist(List<String> tabnamelist) {
        this.tabnamelist = tabnamelist;
    }

    public List<Fragment> getFragmentList() {
        return fragmentList;
    }

    public void setFragmentList(List<Fragment> fragmentList) {
        this.fragmentList = fragmentList;
    }

    public NewsFragmentAdapter(FragmentManager fm, List<String> tabname, List<Fragment> fragments) {
        super(fm);
        this.fragmentList=fragments;
        this.tabnamelist=tabname;

    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabnamelist.get(position);
    }
}
