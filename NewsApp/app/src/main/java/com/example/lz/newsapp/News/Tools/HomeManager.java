package com.example.lz.newsapp.News.Tools;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;

import com.example.lz.newsapp.CollectFragment;
import com.example.lz.newsapp.FindFragment;
import com.example.lz.newsapp.News.Entity.HomeButton;
import com.example.lz.newsapp.News.Fragment.NewsFragment;
import com.example.lz.newsapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lz on 2016/7/3.
 */
public class HomeManager {
    FragmentActivity fa ;

    public HomeManager(FragmentActivity fa) {
        this.fa = fa;
    }

    List<HomeButton> list;
    public void setHomeButtomChange(){
        list=new ArrayList<HomeButton>();
        list.add(new HomeButton((ImageView) fa.findViewById(R.id.home_buttom_one_iv), R.mipmap.new_unselected,R.mipmap.new_selected,new NewsFragment()));
        list.add(new HomeButton((ImageView) fa.findViewById(R.id.home_buttom_two_iv),R.mipmap.collect_unselected,R.mipmap.collect_selected,new CollectFragment()));
        list.add(new HomeButton((ImageView) fa.findViewById(R.id.home_buttom_three_iv),R.mipmap.find_defult,R.mipmap.find_selected,new FindFragment()));
        for (int i=0;i<list.size();i++){
            list.get(i).getImageView().setOnClickListener((View.OnClickListener) fa);
        }
        list.get(0).getImageView().setImageResource(list.get(0).getChickafter());

    }
    public void setbuttomchick(View v){
        HomeButton hb = null;
        for (int i=0;i<list.size();i++){
            hb=list.get(i);
            if(hb.getImageView().getId()==v.getId()){
                hb.getImageView().setImageResource(hb.getChickafter());
                FragmentManager fm = fa.getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.appframe_page,hb.getChickfragment());
                ft.commit();
            }else {
                hb.getImageView().setImageResource(hb.getChickbefore());
            }
        }
    }
}
