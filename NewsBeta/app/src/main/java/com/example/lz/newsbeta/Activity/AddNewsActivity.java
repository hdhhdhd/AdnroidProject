package com.example.lz.newsbeta.Activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.lz.newsbeta.BaseActivity;
import com.example.lz.newsbeta.Entity.AddnewsChanged;
import com.example.lz.newsbeta.R;
import com.example.lz.newsbeta.Tools.SpfManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by lz on 2016/7/19.
 */
public class AddNewsActivity extends BaseActivity implements View.OnClickListener{
    Button toutiao_show,shehui_show,guonei_show,guoji_show,keji_show,junshi_show,
            toutiao_click,shehui_click,guonei_click,guoji_click,keji_click,junshi_click;
    List<AddnewsChanged> changeList;
    ImageView back;
    SpfManager spfManager;

    @Override
    public void getView() {
        setContentView(R.layout.addtips_activity);
        spfManager=SpfManager.getspfManager(this);
        FindView();
    }

    @Override
    public void setView() {
        changeList.add(new AddnewsChanged(toutiao_show,toutiao_click));
        changeList.add(new AddnewsChanged(shehui_show,shehui_click));
        changeList.add(new AddnewsChanged(guonei_show,guonei_click));
        changeList.add(new AddnewsChanged(guoji_show,guoji_click));
        changeList.add(new AddnewsChanged(keji_show,keji_click));
        changeList.add(new AddnewsChanged(junshi_show,junshi_click));
        for (int i = 0;i<changeList.size();i++){
            String isshow=spfManager.getshow(changeList.get(i).getShow().getId()+"");
            if (isshow.equals("yes")){
                changeList.get(i).getShow().setVisibility(View.VISIBLE);
            }else {
                changeList.get(i).getShow().setVisibility(View.GONE);
            }
        }

        //设置添加点击监听
        for (int i =0;i<changeList.size();i++){
            changeList.get(i).getShow().setOnClickListener(this);
            changeList.get(i).getClick().setOnClickListener(this);
        }
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        tabchange(v);

        switch (v.getId()){
            case R.id.add_nws_back:
                finish();
                break;
        }
    }
    public void tabchange(View v){
        for (int i =0;i<changeList.size();i++){
            AddnewsChanged add= changeList.get(i);
            if (add.getClick().getId()==v.getId()){
                add.getShow().setVisibility(View.VISIBLE);
                spfManager.saveshow(add.getShow().getId()+"","yes");
            }
            if (add.getShow().getId()==v.getId()){
                add.getShow().setVisibility(View.GONE);
                spfManager.saveshow(add.getShow().getId()+"","no");
            }
        }
        Intent i = new Intent();
        i.setAction("changed");
        sendBroadcast(i);
    }
    public void FindView(){
        back= (ImageView) findViewById(R.id.add_nws_back);
        toutiao_show= (Button) findViewById(R.id.add_news_toutiao);
        shehui_show= (Button) findViewById(R.id.add_news_shehui);
        guonei_show= (Button) findViewById(R.id.add_news_guonei);
        guoji_show= (Button) findViewById(R.id.add_news_guoji);
        keji_show= (Button) findViewById(R.id.add_news_keji);
        junshi_show= (Button) findViewById(R.id.add_news_junshi);
        toutiao_click= (Button) findViewById(R.id.add_toutiao);
        shehui_click= (Button) findViewById(R.id.add_shehui);
        guonei_click= (Button) findViewById(R.id.add_guonei);
        guoji_click= (Button) findViewById(R.id.add_guoji);
        keji_click= (Button) findViewById(R.id.add_keji);
        junshi_click= (Button) findViewById(R.id.add_junshi);
        changeList=new ArrayList<AddnewsChanged>();
    }
}
