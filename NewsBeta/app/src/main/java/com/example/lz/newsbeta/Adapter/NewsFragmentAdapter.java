package com.example.lz.newsbeta.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lz on 2016/7/10.
 */
public class NewsFragmentAdapter extends FragmentStatePagerAdapter{
    List<String> tabnamelist;
    List<Fragment> list;

    public NewsFragmentAdapter(FragmentManager fm) {
        super(fm);
        this.list=new ArrayList<Fragment>();
        this.tabnamelist=new ArrayList<String>();
    }

    public List<Fragment> getList() {
        return list;
    }

    public void setList(List<Fragment> list) {
        this.list = list;
    }

    public List<String> getTabnamelist() {
        return tabnamelist;
    }

    public void setTabnamelist(List<String> tabnamelist) {
        this.tabnamelist = tabnamelist;
    }


    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabnamelist.get(position);
    }
}
