package com.example.lz.text.tongxun.NumAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lz.text.R;
import com.example.lz.text.tongxun.data.Classlist;

import java.util.List;

/**
 * Created by lz on 2016/5/22.
 */
public class NumberAdapter extends BaseAdapter{
    Context context;
    List<Classlist> list;
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

    public NumberAdapter(Context context, List<Classlist> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder =null;
        if (convertView==null){
            viewHolder =new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.nummenu_item,null);
            viewHolder.name = (TextView) convertView.findViewById(R.id.mainmenuitem_name_tv);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Classlist classlist =list.get(position);
        viewHolder.name.setText(classlist.getName());
        return convertView;
    }class ViewHolder{
        TextView name;
    }
}
