package com.example.lz.text.CheckPhone.PhoneInfoAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lz.text.CheckPhone.data.PhoneInfo;
import com.example.lz.text.R;

import java.util.List;

/**
 * Created by lz on 2016/5/26.
 */
public class PhoneInfoAdapter extends BaseAdapter {
    Context context;
    List<PhoneInfo> list;

    public PhoneInfoAdapter(Context context, List<PhoneInfo> list) {
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
        ViewHolder vh =null;
        if (convertView==null){
            vh =new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.checkphoneitem,null);
            vh.iv = (ImageView) convertView.findViewById(R.id.checkphone_icon_iv);
            vh.name1 = (TextView) convertView.findViewById(R.id.checkphone_info1_tv);
            vh.name2 = (TextView) convertView.findViewById(R.id.checkphone_info2_tv);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        switch (position%3) {
            case 0:
                vh.iv.setBackgroundResource(R.drawable.green);
                break;
            case 1:
                vh.iv.setBackgroundResource(R.drawable.red);
                break;
            case 2:
                vh.iv.setBackgroundResource(R.drawable.notification_information_progress_yellow);
                break;
        }
        vh.iv.setImageResource(list.get(position).getIconid());
        vh.name1.setText(list.get(position).getName1());
        vh.name2.setText(list.get(position).getName2());
        return convertView;
    }class ViewHolder{
        ImageView iv;
        TextView name1;
        TextView name2;
    }
}
