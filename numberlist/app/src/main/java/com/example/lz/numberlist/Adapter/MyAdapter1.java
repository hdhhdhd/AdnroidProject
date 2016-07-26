package com.example.lz.numberlist.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lz.numberlist.R;
import com.example.lz.numberlist.SQL.Classlist;
import com.example.lz.numberlist.SQL.NumberInfo;

import java.util.List;

/**
 * Created by lz on 2016/5/12.
 */
public class MyAdapter1 extends BaseAdapter{
    Context context;
    List<NumberInfo> list;

    public MyAdapter1(Context context, List<NumberInfo> list) {
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
        ViewHolder vh ;
        if (convertView==null){
            vh =new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.secmenu_item,null);
            vh.name = (TextView) convertView.findViewById(R.id.secmenuitem_name_tv);
            vh.num = (TextView) convertView.findViewById(R.id.secmenuitem_num_tv);
            convertView.setTag(vh);
        }
        else {
            vh= (ViewHolder) convertView.getTag();
        }
       NumberInfo numberInfo = list.get(position);
        vh.name.setText(numberInfo.getName());
        vh.num.setText(numberInfo.getNumber());
        return convertView;
    }class ViewHolder{
        TextView name;
        TextView num;
    }
}
