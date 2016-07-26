package com.example.lz.text.ClearRubbish.ClearRubbishAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lz.text.ClearRubbish.Data.RubbishFileInfo;
import com.example.lz.text.R;
import com.example.lz.text.Tools;

import java.util.List;

/**
 * Created by lz on 2016/6/7.
 */
public class ClearRubbishAdapter extends BaseAdapter{
    private LayoutInflater layoutInflater;
    private List<RubbishFileInfo> list;
    Context mC;

    public List<RubbishFileInfo> getList() {
        return list;
    }

    public void setList(List<RubbishFileInfo> list) {
        this.list = list;
    }

    public ClearRubbishAdapter(List<RubbishFileInfo> list, Context mC) {

        this.list = list;
        this.mC = mC;
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
            view=layoutInflater.inflate(R.layout.clearrubbish_item,null);
            vh=new ViewHolder();
            vh.name_tv= (TextView) view.findViewById(R.id.clearrubbishitem_name_tv);
            vh.size_tv= (TextView) view.findViewById(R.id.clearrubbishitem_size_tv);
            vh.iv= (ImageView) view.findViewById(R.id.showappitem_icon_iv);
            view.setTag(vh);
        }else{
            vh= (ViewHolder) view.getTag();
        }
        RubbishFileInfo rubbishFileInfo=list.get(position);
        vh.name_tv.setText(rubbishFileInfo.getApkname());
        vh.iv.setImageDrawable(rubbishFileInfo.getIcon());
        vh.size_tv.setText(Tools.getFileSize(rubbishFileInfo.getSize()));

        return view;
    }
    class ViewHolder{
        TextView name_tv,size_tv;
        ImageView iv;
    }
    CompoundButton.OnCheckedChangeListener onCheckedChangeListener=new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        }
    };
}
