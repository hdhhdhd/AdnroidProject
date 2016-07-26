package com.example.lz.newsbeta.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lz.newsbeta.Entity.News;
import com.example.lz.newsbeta.R;

import java.util.List;

/**
 * Created by lz on 2016/7/21.
 */
public class HotAdapter extends RecyclerView.Adapter<HotAdapter.MyViewHolder> {
    Context mC;
    List<News> newsList;

    public HotAdapter(Context mC, List<News> newsList) {
        this.mC = mC;
        this.newsList = newsList;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mC).inflate(R.layout.hotrecyclerview_item,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        News news=newsList.get(position);
        holder.img.setImageBitmap(news.getNewsimg());
        holder.textView.setText(news.getNewsname());
        if (recyclerOnClikListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int ps=holder.getLayoutPosition();
                    recyclerOnClikListener.onItemClik(v,ps);
                }
            });
        }
    }
    private RecyclerOnClikListener recyclerOnClikListener;

    public RecyclerOnClikListener getRecyclerOnClikListener() {
        return recyclerOnClikListener;
    }

    public void setRecyclerOnClikListener(RecyclerOnClikListener recyclerOnClikListener) {
        this.recyclerOnClikListener = recyclerOnClikListener;
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.hotrecyclerviewitem_iv);
            textView = (TextView) itemView.findViewById(R.id.hotrecyclerviewitem_tv);
        }
    }
    public interface RecyclerOnClikListener{
        void onItemClik(View view,int position);
        void onItemLongClik(View view,int position);
    }
}
