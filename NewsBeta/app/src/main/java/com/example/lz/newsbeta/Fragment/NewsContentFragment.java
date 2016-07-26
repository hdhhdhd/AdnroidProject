package com.example.lz.newsbeta.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lz.newsbeta.Activity.WebViewActivity;
import com.example.lz.newsbeta.Adapter.RecyclerViewAdapter;
import com.example.lz.newsbeta.BaseFragment;
import com.example.lz.newsbeta.Entity.News;
import com.example.lz.newsbeta.R;
import com.example.lz.newsbeta.Tools.VolleyManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lz on 2016/7/14.
 */
public class NewsContentFragment extends Fragment {
    RecyclerView rcv;
    RecyclerViewAdapter ra;
    List<News> newses ;
    View view;
    VolleyManager vm;
    String content;
    LinearLayoutManager mLayoutManager;
    SwipeRefreshLayout swipeRefreshLayout;
    public String getContent() {

        return content;
    }

    public void setContent(String content) {

        this.content = content;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.newscontent,null);
        putView();
        setView();
        return view;
    }


    public void putView() {
        vm =new VolleyManager(getContext());
        rcv = (RecyclerView) view.findViewById(R.id.all_rcv);
        newses=new ArrayList<News>();
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    public void setView() {
        getnews();
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.newinfo_swiperefresh);
        //第一次加载显示进度条
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        //设置下拉刷新的进度条颜色（最多四种）
        swipeRefreshLayout.setColorSchemeResources(R.color.pink, R.color.orange,
                R.color.green, R.color.blue);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getnews();
                swipeRefreshLayout.setRefreshing(true);
            }
        });
    }


    public void getnews(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                vm.setHandImg(new VolleyManager.HandImg() {
                    @Override
                    public void hand(Bitmap bitmap, String name, String url, String date, String type,String imgurl) {
                        News news = new News(bitmap,name,url,date,type,imgurl);
                        newses.add(news);
                        Log.i("newses",newses.toString());
                        handler.sendEmptyMessage(0);
                    }

                });
                vm.getResult("http://v.juhe.cn/toutiao/index?type="+content+"&key=6ffe77bc3b58ae8db1a1677b8cae0aea");
            }

        }).start();
    }
    Handler handler;

    {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        ra = new RecyclerViewAdapter(newses, getContext());
                        rcv.setItemAnimator(new DefaultItemAnimator());
                        rcv.setAdapter(ra);
                        rcv.addOnScrollListener(new OnScrollListener() {
                            @Override
                            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                                super.onScrollStateChanged(recyclerView, newState);

                            }

                            @Override
                            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                                super.onScrolled(recyclerView, dx, dy);
                            }
                        });
                        ra.setNewsList(newses);
                        ra.setRecyclerViewOnclickListener(new RecyclerViewAdapter.RecyclerViewOnclickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent i = new Intent(getActivity(),WebViewActivity.class);
                                Bundle b =new Bundle();
                                b.putString("url",newses.get(position).getNewsurl());
                                b.putParcelable("news",newses.get(position));
                                i.putExtras(b);
                                startActivity(i);
                            }

                            @Override
                            public void onItemLongClick(View view, int position) {

                            }


                        });
                        ra.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                        break;
                }
            }
        };
    }
}
