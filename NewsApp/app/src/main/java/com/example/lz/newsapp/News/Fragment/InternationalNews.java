package com.example.lz.newsapp.News.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.lz.newsapp.News.Activity.WebViewActivity;
import com.example.lz.newsapp.News.Adapter.NewsAdapter;
import com.example.lz.newsapp.News.BaseFragment;
import com.example.lz.newsapp.News.Entity.News;
import com.example.lz.newsapp.News.Tools.NetWorkManager;
import com.example.lz.newsapp.R;

import java.util.List;

/**
 * Created by lz on 2016/7/3.
 */
public class InternationalNews extends BaseFragment {
    View view;
    ListView listView;
    NewsAdapter newsAdapter;
    String result;
    List<News> newses;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.news,null);
        return view;
    }

    @Override
    public void getview() {
        listView= (ListView) getActivity().findViewById(R.id.newslist_lv);
    }

    @Override
    public void setview() {
        getnews();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), WebViewActivity.class);
                Bundle b = new Bundle();
                b.putString("url",newses.get(position).getNewsurl());
                i.putExtras(b);
                startActivity(i);
            }
        });
    }
    public void getnews(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                NetWorkManager nwm=new NetWorkManager(getActivity());
                result=NetWorkManager.httpcookbookpost("http://v.juhe.cn/toutiao/index","guoji","cd66223698f51efea187f9daa8e9cc73");
                newses=nwm.jsonnetwork(result);
                handler.sendEmptyMessage(0);
            }
        }).start();
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    newsAdapter=new NewsAdapter(newses,getActivity());
                    listView.setAdapter(newsAdapter);
                    newsAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };
}
