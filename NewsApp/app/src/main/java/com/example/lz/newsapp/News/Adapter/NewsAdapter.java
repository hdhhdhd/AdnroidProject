package com.example.lz.newsapp.News.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lz.newsapp.News.Entity.News;
import com.example.lz.newsapp.R;

import java.util.List;

/**
 * Created by lz on 2016/7/3.
 */
public class NewsAdapter extends BaseAdapter {
    List<News> list;
    Context context;
    int normalid;
    boolean isFing;

    public List<News> getList() {
        return list;
    }

    public void setList(List<News> list) {
        this.list = list;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public boolean isFing() {
        return isFing;
    }

    public void setFing(boolean fing) {
        isFing = fing;
    }

    public NewsAdapter(List<News> list, Context context) {
        this.list = list;
        this.context = context;
        normalid= R.mipmap.ic_launcher;

    }
    @Override
    public int getCount() {
            return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler viewHodler =null;
        if (convertView==null){
            convertView=View.inflate(context, R.layout.newsitem,null);
            viewHodler=new ViewHodler();
            viewHodler.img= (ImageView) convertView.findViewById(R.id.newsitem_iv);
            viewHodler.name= (TextView) convertView.findViewById(R.id.newsitem_tv);
            convertView.setTag(viewHodler);
        }else {
            viewHodler= (ViewHodler) convertView.getTag();
        }
        if (isFing){
            viewHodler.img.setImageResource(normalid);
        }else {
            viewHodler.img.setImageBitmap(list.get(position).getNewsimg());
        }
        viewHodler.name.setText(list.get(position).getNewsname());
        return convertView;
    }
    public class ViewHodler{
        ImageView img;
        TextView name;
    }
}
