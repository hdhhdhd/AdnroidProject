package com.example.lz.text.Home.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lz.text.R;

import java.util.List;

/**
 * Created by lz on 2016/5/20.
 */
public class SettingAdapter extends BaseAdapter{
    Context context;
    List<String> list;
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

    public SettingAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh =null;
        if (convertView==null){
            vh=new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.settingitem,null);
            vh.tv = (TextView) convertView.findViewById(R.id.settingitem_item_tv);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.tv.setText(list.get(position));
        return convertView;
    }class ViewHolder{
        TextView tv;
    }
}
