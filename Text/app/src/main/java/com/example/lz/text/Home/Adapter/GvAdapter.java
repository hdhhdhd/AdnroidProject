package com.example.lz.text.Home.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lz.text.Home.View.HomeTitle;
import com.example.lz.text.R;

import java.util.List;

/**
 * Created by lz on 2016/5/19.
 */
public class GvAdapter extends BaseAdapter {
    Context context;
    List<HomeTitle> list;

    public GvAdapter(Context context, List<HomeTitle> list) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh=null;
        if (convertView==null){
            vh =new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.home_item,null);
            vh.iv= (ImageView) convertView.findViewById(R.id.homeitem_iv);
            vh.tv = (TextView) convertView.findViewById(R.id.homeitem_tv);
            convertView.setTag(vh);
        }else {
            vh= (ViewHolder) convertView.getTag();
        }
        HomeTitle ht=list.get(position);
        vh.iv.setImageResource(ht.getID());
        vh.tv.setText(ht.getHometitle());
        return convertView;
    }class ViewHolder{
        ImageView iv;
        TextView tv;
    }
}
