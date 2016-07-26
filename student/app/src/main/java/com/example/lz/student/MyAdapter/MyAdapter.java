package com.example.lz.student.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lz.student.R;
import com.example.lz.student.entity.Student;

import java.util.List;

/**
 * Created by lz on 2016/5/17.
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh =null;
        if (convertView==null){
            vh =new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.details_item,null);
            vh.name = (TextView) convertView.findViewById(R.id.detailsitem_name1_tv);
            vh.major= (TextView) convertView.findViewById(R.id.detailsitem_major1_tv);
            convertView.setTag(vh);
        }else {
            vh= (ViewHolder) convertView.getTag();
        }
        Student student =list.get(position);
        vh.name.setText(student.getName());
        vh.major.setText(student.getMajor());
        return convertView;
    }
    class ViewHolder{
        TextView name;
        TextView major;
    }
}
