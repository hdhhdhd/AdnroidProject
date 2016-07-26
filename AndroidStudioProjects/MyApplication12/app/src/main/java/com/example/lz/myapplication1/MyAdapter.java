package com.example.lz.myapplication1;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lz on 2016/5/7.
 */
public class MyAdapter extends BaseAdapter{
    Context context;
    List<Student> list;

    public MyAdapter(Context context, List<Student> list) {
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
        ViewHolder vh;
        if (view==null){
          vh=new ViewHolder();
            view= LayoutInflater.from(context).inflate(R.layout.info,null);
            vh.name= (TextView) view.findViewById(R.id.info_name1_tv);
            vh.major= (TextView) view.findViewById(R.id.info_major1_tv);
            view.setTag(vh);
        }else {
            vh= (ViewHolder) view.getTag();
        }
          Student stu =list.get(position);
        vh.name.setText(stu.getName());
        vh.major.setText(stu.getMajor());
        return view;
    }
    class ViewHolder{
        TextView name;
        TextView major;
    }
}
