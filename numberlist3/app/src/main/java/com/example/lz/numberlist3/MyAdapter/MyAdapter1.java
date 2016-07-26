package com.example.lz.numberlist3.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lz.numberlist3.R;
import com.example.lz.numberlist3.data.NumberInfo;

import java.util.List;

/**
 * Created by lz on 2016/5/13.
 */
public class MyAdapter1 extends BaseAdapter{
    Context context;
    List<NumberInfo> list;
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

    public MyAdapter1(Context context, List<NumberInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh =null;
        if (convertView==null) {
            vh =new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.secmenu_item, null);
            vh.name = (TextView) convertView.findViewById(R.id.secmenu_name_tv);
            vh.num = (TextView) convertView.findViewById(R.id.secmenu_num_tv);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        NumberInfo numberInfo =list.get(position);
        vh.name.setText(numberInfo.getName());
        vh.num.setText(numberInfo.getNum());

        return convertView;
    }class ViewHolder{
        TextView name;
        TextView num;
    }
}
