package com.example.lz.fruit.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lz.fruit.DB.FruitDb;
import com.example.lz.fruit.R;
import com.example.lz.fruit.entity.Fruit;

import java.util.List;

/**
 * Created by lz on 2016/7/5.
 */
public class FruitAdapter extends BaseAdapter {
    List<Fruit> list ;
    Context context;
    public FruitAdapter(List<Fruit> list, Context context) {
        this.list = list;
        this.context = context;
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

    public List<Fruit> getList() {
        return list;
    }

    public void setList(List<Fruit> list) {
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
           ViewHolder vh =null;
        if (convertView==null){
            vh =new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.fruitlistitem,null);
            vh.imageView= (ImageView) convertView.findViewById(R.id.fruit_img);
            vh.allnum = (TextView) convertView.findViewById(R.id.fruit_Allnum_tv);
            vh.name = (TextView) convertView.findViewById(R.id.fruit_name_tv);
            vh.prince = (TextView) convertView.findViewById(R.id.fruit_prince_tv);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
          vh.imageView.setImageResource(list.get(position).getImage());
          vh.name.setText(list.get(position).getName());
        vh.prince.setText("单价："+list.get(position).getPrice());
        return convertView;
    }class ViewHolder {
        ImageView imageView;
        TextView name,prince,allnum;

    }
}
