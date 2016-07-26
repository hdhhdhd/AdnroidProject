package com.example.lz.newsbeta.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lz.newsbeta.Entity.News;
import com.example.lz.newsbeta.R;

import java.util.List;

/**
 * Created by lz on 2016/7/12.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    List<News> newsList;
    Context mC;
    final int ONE_TYPE=1;
    final int TWO_TYPE=2;
    int type;
    public RecyclerViewAdapter(List<News> newsList, Context mC) {
        this.newsList = newsList;
        this.mC = mC;
        Log.i("list",newsList.toString());
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder viewHolder = null;
        if (viewType==ONE_TYPE){
            View view= LayoutInflater.from(mC).inflate(R.layout.news_item_two,parent,false);
            viewHolder =new MyViewHolder(view);
        }
        if (viewType==TWO_TYPE){
            View view= LayoutInflater.from(mC).inflate(R.layout.newsrecyclerviewitem,parent,false);
            viewHolder =new MyViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        if (type==ONE_TYPE){
            holder.img.setImageBitmap(newsList.get(position).getNewsimg());
            holder.name.setText(newsList.get(position).getNewsname());
        }
        if (type==TWO_TYPE){
            holder.img.setImageBitmap(newsList.get(position).getNewsimg());
            holder.name.setText(newsList.get(position).getNewsname());
            holder.newstype.setText(newsList.get(position).getNewstype());
            holder.date.setText(newsList.get(position).getNewsdate());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerViewOnclickListener.onItemClick(v,position);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                recyclerViewOnclickListener.onItemLongClick(v,position);
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            type=ONE_TYPE;//将第0项的type类型设为1
            return ONE_TYPE;
        }else {
            type=TWO_TYPE;//其他项的type类型设为2，若不设置，type值全部为0；
            return TWO_TYPE;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView name,date,newstype;
        public MyViewHolder(View itemView) {
            super(itemView);
            Log.i("type",type+"");
            if (type==ONE_TYPE){
                img= (ImageView) itemView.findViewById(R.id.newsitem_img_iv);
                name= (TextView) itemView.findViewById(R.id.newsitem_news_tv);
            }
            if (type==TWO_TYPE){
                img= (ImageView) itemView.findViewById(R.id.newsrecycleritem_iv);
                name= (TextView) itemView.findViewById(R.id.newsrecycleritem_tv);
                date= (TextView) itemView.findViewById(R.id.newsnext_newstime_tv);
                newstype= (TextView) itemView.findViewById(R.id.newsnext_newsfrom_tv);
            }

        }
    }
    public RecyclerViewOnclickListener recyclerViewOnclickListener;

    public RecyclerViewOnclickListener getRecyclerViewOnclickListener() {
        return recyclerViewOnclickListener;
    }

    public void setRecyclerViewOnclickListener(RecyclerViewOnclickListener recyclerViewOnclickListener) {
        this.recyclerViewOnclickListener = recyclerViewOnclickListener;
    }

    public interface RecyclerViewOnclickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }

}
