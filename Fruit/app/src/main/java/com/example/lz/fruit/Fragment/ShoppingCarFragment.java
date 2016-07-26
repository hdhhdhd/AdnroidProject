package com.example.lz.fruit.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.lz.fruit.Activity.BuyActivity;
import com.example.lz.fruit.Adapter.FruitAdapter;
import com.example.lz.fruit.Adapter.ShoppingCarAdapter;
import com.example.lz.fruit.BaseFragment;
import com.example.lz.fruit.DB.FruitDb;
import com.example.lz.fruit.DB.ShopingCarDb;
import com.example.lz.fruit.R;
import com.example.lz.fruit.entity.Fruit;

import java.util.List;

/**
 * Created by lz on 2016/7/6.
 */
public class ShoppingCarFragment extends BaseFragment implements View.OnClickListener{
    View view,view1;
    ListView lv;
    List<Fruit> list;
    ShoppingCarAdapter sa;
    ShopingCarDb sd;
    String username;
    Button delete,pay;
    ToggleButton alltb;
    TextView sum;
    int a=0,b=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.shoppingcarfragment,null);
        return view;
    }

    @Override
    public void getview() {
//        view1= getActivity().findViewById(R.id.shoppingcar_list);
        delete = (Button) getActivity().findViewById(R.id.shoppingfragment_delete_btn);
        pay = (Button) getActivity().findViewById(R.id.shoppingcarfragment_pay_btn);
        alltb = (ToggleButton) getActivity().findViewById(R.id.shoppingcarfrangmrnt_tgb);
        sum = (TextView) getActivity().findViewById(R.id.shoppingcarfragmebt_sum_tv);
        lv = (ListView) getActivity().findViewById(R.id.list);
    }

    @Override
    public void setview() {
        getData();
        delete.setOnClickListener(this);
        pay.setOnClickListener(this);
        alltb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (list!=null) {
                    if(b==0){
                        for (int i = 0; i < list.size(); i++) {
                            list.get(i).setIschecked(true);
                            sa.notifyDataSetChanged();
                            b=1;
                        }
                    }else {
                        for (int i = 0; i < list.size(); i++) {
                            list.get(i).setIschecked(false);
                            sa.notifyDataSetChanged();
                            b=0;
                        }
                    }

                }
            }
        });
        sum.setText(check(list)+"元");
    }
    public void getData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        }).start();
    }
    Handler handler= new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    Intent intent =getActivity().getIntent();
                    Bundle bundle =intent.getExtras();
                    username =bundle.getString("username");
                    sd =new ShopingCarDb(getActivity());
                    list =sd.find(username);
                    if (list!=null) {
                        sa=new ShoppingCarAdapter(list,getActivity());
                        lv.setAdapter(sa);
                        int c=0;
                        for (int i=0;i<list.size();i++){
                            c+=list.get(i).getSum()*list.get(i).getPrice();
                        }
                        sum.setText(c+"元");
                    }
                    break;
            }

        }
    };

    @Override
    public void onClick(View v) {
        ShopingCarDb sd1 =new ShopingCarDb(getActivity());
        switch (v.getId()){
            case R.id.shoppingfragment_delete_btn:
                for (int i=0;i<list.size();i++){
                    if (list.get(i).ischecked()) {
                        sd1.deleteone(list.get(i).getName());
                    }
                }
                list=sd1.find(username);
                sa.setList(list);
                sa.notifyDataSetChanged();
                lv.setAdapter(sa);
                sum.setText(check(list)+"元");
                break;
            case R.id.shoppingcarfragment_pay_btn:
                if (alltb.isChecked()){
                    sd1.delete(username);
                }else {
                    for (int i=0;i<list.size();i++){
                        if (list.get(i).ischecked()) {
                            sd1.deleteone(list.get(i).getName());
                            list.get(i).setAllnum(list.get(i).getAllnum()-list.get(i).getSum());
                        }
                    }
                }
                list=sd.find(username);
                sa.setList(list);
                sa.notifyDataSetChanged();
                lv.setAdapter(sa);
                sum.setText(check(list)+"元");
                break;
        }
    }
    public int check(List<Fruit> list){
        int sum=0;
        if (list!=null) {
            for (int i = 0;i<list.size();i++ ){
                if (list.get(i).ischecked()){
                    sd.delete(list.get(i).getName());
                    list.remove(list.get(i));
                    i--;
                    sum+=list.get(i).getSum()*list.get(i).getPrice();
                    if (a==1) {
                        sd.delete(list.get(i).getName());
                        list.get(i).setAllnum(list.get(i).getAllnum()-list.get(i).getSum());
                        sd.add(list.get(i));
                    }
                }
            }
        }
        return sum;
    }
}
