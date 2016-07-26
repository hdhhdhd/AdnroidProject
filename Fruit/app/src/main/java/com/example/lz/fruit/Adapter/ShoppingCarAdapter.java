package com.example.lz.fruit.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.lz.fruit.DB.ShopingCarDb;
import com.example.lz.fruit.R;
import com.example.lz.fruit.entity.Fruit;

import java.util.List;

/**
 * Created by lz on 2016/7/6.
 */
public class ShoppingCarAdapter extends BaseAdapter{
    List<Fruit> list;
    Context context;
    ShopingCarDb sd;
    public List<Fruit> getList() {
        return list;
    }

    public void setList(List<Fruit> list) {
        this.list = list;
    }

    public ShoppingCarAdapter(List<Fruit> list, Context context) {

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

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        sd =new ShopingCarDb(context);
        ViewHolder vh =null;
        if (convertView==null){
            vh=new ViewHolder();
            convertView = LayoutInflater.from(context).inflate( R.layout.shoppingcaritem,null);
            vh.iv= (ImageView) convertView.findViewById(R.id.shoppingcaritem_img);
            vh.name = (TextView) convertView.findViewById(R.id.shoppingcaritem_name_tv);
            vh.prince = (TextView) convertView.findViewById(R.id.shoppingcaritem_prince_tv);
            vh.minus = (Button) convertView.findViewById(R.id.shoppingcaritem_minus_btn);
            vh.add = (Button) convertView.findViewById(R.id.shoppingcaritem_add_btn);
            vh.et = (EditText) convertView.findViewById(R.id.shoppingcaritem_sum_et);
            vh.tb = (ToggleButton) convertView.findViewById(R.id.shoppingcaritem_tgb);
            vh.sum = (TextView) convertView.findViewById(R.id.shoppingcaritem_sum_tv);
            convertView.setTag(vh);
        }else {
            vh= (ViewHolder) convertView.getTag();
        }
        vh.et.setText(list.get(position).getSum()+"");
        vh.tb.setTag(position);
        vh.tb.setChecked(list.get(position).ischecked());
        vh.iv.setImageResource(list.get(position).getImage());
        vh.name.setText(list.get(position).getName());
        vh.prince.setText("单价"+list.get(position).getPrice()+"元");
        vh.sum.setText("金额:"+list.get(position).getSum()*list.get(position).getPrice()+"元");
        vh.tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int position= (int) buttonView.getTag();
                list.get(position).setIschecked(isChecked);
            }
        });
        final ViewHolder finalVh = vh;
        vh.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count1=Integer.parseInt(finalVh.et.getText().toString());
                if (count1>list.get(position).getAllnum()){
                    Toast.makeText(context,"没有库存了",Toast.LENGTH_SHORT);
                }else {
                    count1++;
                }
                finalVh.et.setText(count1 + "");
                Fruit fruit =list.get(position);
                list.get(position).setSum(count1);
                sd.changesum(fruit,list.get(position));
                finalVh.sum.setText("金额:"+list.get(position).getSum()*list.get(position).getPrice()+"元");
            }
        });

        vh.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count=Integer.parseInt(finalVh.et.getText().toString());
                if (count<0){
                    count=0;
                }else {
                    count--;
                }

                finalVh.et.setText(count+"");
                Fruit fruit =list.get(position);
                list.get(position).setSum(count);
                sd.changesum(fruit,list.get(position));
                finalVh.sum.setText("金额:"+list.get(position).getSum()*list.get(position).getPrice()+"元");
            }
        });

        return convertView;
    }class ViewHolder{
        ImageView iv;
        TextView name,prince,sum;
        Button add,minus;
        EditText et;
        ToggleButton tb;
    }

}
