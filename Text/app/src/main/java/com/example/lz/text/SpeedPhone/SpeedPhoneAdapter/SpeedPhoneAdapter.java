package com.example.lz.text.SpeedPhone.SpeedPhoneAdapter;

import android.content.Context;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.lz.text.SpeedPhone.data.AppProcess;
import com.example.lz.text.R;

import java.util.List;

/**
 * Created by lz on 2016/5/27.
 */
public class SpeedPhoneAdapter extends BaseAdapter {
    Context context;
    List<AppProcess> list;
    int normalid;
    boolean isFing;

    public SpeedPhoneAdapter(List<AppProcess> list, Context context) {
        this.list = list;
        this.context = context;
        normalid =R.mipmap.ic_launcher;
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

    public List<AppProcess> getList() {
        return list;
    }

    public void setList(List<AppProcess> list) {
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PackageManager pm = context.getPackageManager();
        ViewHolder vh=null;
                 if (convertView==null){
                     vh=new ViewHolder();
                     convertView = LayoutInflater.from(context).inflate(R.layout.speedphoneitem,null);
                     vh.name = (TextView) convertView.findViewById(R.id.speedphoneitem_appname_tv);
                     vh.ram = (TextView) convertView.findViewById(R.id.speedphoneitem_ram_tv);
                     vh.icon = (ImageView) convertView.findViewById(R.id.speedphoneitem_icon_iv);
                     vh.type = (TextView) convertView.findViewById(R.id.speedphoneitem_type_tv);
                     vh.tb = (ToggleButton) convertView.findViewById(R.id.speedphoneitem_select_tb);
                     convertView.setTag(vh);
                 }else {
                     vh= (ViewHolder) convertView.getTag();
                 }
                 AppProcess ap =list.get(position);
               vh.tb.setTag(position);
               vh.tb.setChecked(ap.getIscheck());
               vh.name.setText(ap.getName());
               vh.ram.setText(ap.getRam());
               vh.type.setText(ap.getType());
        if(isFing){
            vh.icon.setImageResource(normalid);
        }else{
            vh.icon.setImageDrawable(ap.getIcon());//图标
        }
        vh.tb.setOnCheckedChangeListener(onCheckedChangeListener);
        return convertView;

    }class ViewHolder{
        TextView name;
        TextView ram;
        TextView type;
        ImageView icon;
        ToggleButton tb;
    }CompoundButton.OnCheckedChangeListener onCheckedChangeListener=new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            int position= (int) buttonView.getTag();
            list.get(position).setIscheck(isChecked);
        }
    };
}
