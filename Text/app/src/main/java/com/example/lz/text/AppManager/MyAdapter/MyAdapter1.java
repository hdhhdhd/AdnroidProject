package com.example.lz.text.AppManager.MyAdapter;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.lz.text.AppManager.data.AppInfo;
import com.example.lz.text.R;

import java.util.List;

/**
 * Created by lz on 2016/5/24.
 */
public class MyAdapter1 extends BaseAdapter{
    List<AppInfo> list;
    Context mC;
    int normalid;
    boolean isFing;

    public List<AppInfo> getList() {
        return list;
    }

    public void setList(List<AppInfo> list) {
        this.list = list;
    }

    public MyAdapter1(List<AppInfo> list, Context mC) {
        this.list = list;
        this.mC = mC;
        normalid= R.mipmap.ic_launcher;//设置快速滑动时候的默认图片ID
    }

    public boolean isFing() {
        return isFing;
    }

    public void setFing(boolean fing) {
        isFing = fing;
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
        ViewHolder vh=null;
        if(view==null){
            view= LayoutInflater.from(mC).inflate(R.layout.showapp_item,null);
            vh=new ViewHolder();
            vh.name= (TextView) view.findViewById(R.id.showappitem_name_tv);
            vh.packagename= (TextView) view.findViewById(R.id.showappitem_packagename_tv);
            vh.viersion= (TextView) view.findViewById(R.id.showappitem_version_tv);
            vh.check_tog= (ToggleButton) view.findViewById(R.id.showappitem_select_tb);
            vh.icon_img= (ImageView) view.findViewById(R.id.showappitem_icon_iv);
            view.setTag(vh);
        }else{
            vh= (ViewHolder) view.getTag();
        }

        vh.check_tog.setTag(position);//设置绑定标签

        AppInfo ai=list.get(position);
        vh.check_tog.setChecked(ai.ischeck());//设置显示状态


        PackageInfo packageInfo=ai.getPackageInfo();
        PackageManager pm=mC.getPackageManager();
        vh.name.setText(packageInfo.applicationInfo.loadLabel(pm).toString());//程序名
        vh.packagename.setText(packageInfo.packageName);//包名
        vh.viersion.setText(packageInfo.versionName);//版本
        if(isFing){
            vh.icon_img.setImageResource(normalid);
        }else{
            vh.icon_img.setImageDrawable(packageInfo.applicationInfo.loadIcon(pm));//图标
        }

        vh.check_tog.setOnCheckedChangeListener(onCheckedChangeListener);//绑定监听
        return view;
    }
    class ViewHolder{
        TextView name,packagename,viersion;
        ToggleButton check_tog;
        ImageView icon_img;
    }
    CompoundButton.OnCheckedChangeListener onCheckedChangeListener=new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            int position= (int) buttonView.getTag();
            list.get(position).setIscheck(isChecked);
        }
    };
}
