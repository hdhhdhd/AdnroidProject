package com.example.lz.newsbeta.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lz.newsbeta.Activity.WebViewActivity;
import com.example.lz.newsbeta.Adapter.HotAdapter;
import com.example.lz.newsbeta.BaseFragment;
import com.example.lz.newsbeta.Entity.News;
import com.example.lz.newsbeta.R;
import com.example.lz.newsbeta.Tools.VolleyManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lz on 2016/7/12.
 */
public class HotFragment extends BaseFragment{
    RecyclerView rcv;
    HotAdapter hotAdapter;
    List<News> newsList;
    SwipeRefreshLayout swipeRefreshLayout;
    View view;
    VolleyManager vm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.newscontent,null);
        putView();
        setView();
        return view;
    }

    @Override
    public void putView() {
        rcv= (RecyclerView) view.findViewById(R.id.all_rcv);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.newinfo_swiperefresh);
        rcv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        newsList=new ArrayList<News>();
        vm=new VolleyManager(getActivity());
    }

    @Override
    public void setView() {
         loaddata();
    }
    public void loaddata(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                vm.setHandImg(new VolleyManager.HandImg() {
                    @Override
                    public void hand(Bitmap bitmap, String name, String url, String date, String type,String imgurl) {
                        News news = new News(bitmap,name,url,date,type,imgurl);
                        newsList.add(news);
                        Log.i("msg",newsList.toString());
                        handler.sendEmptyMessage(0);
                    }
                });
                vm.getResult("http://v.juhe.cn/toutiao/index?type=top&key=cd66223698f51efea187f9daa8e9cc73");
            }
        }).start();

    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    hotAdapter=new HotAdapter(getActivity(),newsList);
                    rcv.setAdapter(hotAdapter);
                    hotAdapter.setRecyclerOnClikListener(new HotAdapter.RecyclerOnClikListener() {
                        @Override
                        public void onItemClik(View view, int position) {
                            Intent i = new Intent(getActivity(), WebViewActivity.class);
                            Bundle b = new Bundle();
                            b.putString("url",newsList.get(position).getNewsurl());
                            b.putParcelable("news",newsList.get(position));
                            i.putExtras(b);
                            startActivity(i);
                        }

                        @Override
                        public void onItemLongClik(View view, int position) {

                        }
                    });
                    break;
            }
        }
    };
}
