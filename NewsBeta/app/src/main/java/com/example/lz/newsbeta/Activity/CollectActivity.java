package com.example.lz.newsbeta.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.lz.newsbeta.Adapter.RecyclerViewAdapter;
import com.example.lz.newsbeta.BaseActivity;
import com.example.lz.newsbeta.DB.CollectDb;
import com.example.lz.newsbeta.Entity.News;
import com.example.lz.newsbeta.R;

import java.util.List;

/**
 * Created by lz on 2016/7/22.
 */
public class CollectActivity extends BaseActivity{
    RecyclerView recyclerView;
    CollectDb cb;
    List<News> news;
    RecyclerViewAdapter ra;
    @Override
    public void getView() {
        setContentView(R.layout.recyclerview);
        recyclerView = (RecyclerView) findViewById(R.id.all_rcv);
        cb =new CollectDb(this);
    }

    @Override
    public void setView() {
         news =cb.find();
        if (news!=null) {
            ra = new RecyclerViewAdapter(news, this);
            recyclerView.setAdapter(ra);
            ra.recyclerViewOnclickListener = new RecyclerViewAdapter.RecyclerViewOnclickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent i = new Intent(CollectActivity.this, WebViewActivity.class);
                    Bundle b = new Bundle();
                    News news1 = news.get(position);
                    b.putSerializable("news", news1);
                    i.putExtras(b);
                    startActivity(i);
                }

                @Override
                public void onItemLongClick(View view, int position) {

                }


            };
        }
    }
}
