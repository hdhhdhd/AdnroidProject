package com.example.lz.myapplication.reglog;

import android.content.Context;
import android.media.Image;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lz.myapplication.R;

import java.util.List;

/**
 * Created by lz on 2016/5/6.
 */
public class MyAdapter extends BaseAdapter {
    Context context;
    List<user> list;
    public MyAdapter(Context context, List<user> list) {
        this.context = context;
        this.list = list;
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
    public View getView(int position, View view, ViewGroup parent) {
        Viewholder vh ;
        if (view==null){
           vh =new Viewholder();
            view= LayoutInflater.from(context).inflate(R.layout.hh,null);
            vh.tv= (TextView) view.findViewById(R.id.hh_name_tv);


            view.setTag(vh);
        }else {
            vh= (Viewholder) view.getTag();
        }
        user  u=list.get(position);
        vh.tv.setText(u.getName());
        return view;

    }
    class Viewholder{
        TextView tv;

    }
}
