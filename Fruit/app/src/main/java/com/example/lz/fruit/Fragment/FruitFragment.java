package com.example.lz.fruit.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lz.fruit.Activity.BuyActivity;
import com.example.lz.fruit.Adapter.FruitAdapter;
import com.example.lz.fruit.BaseFragment;
import com.example.lz.fruit.DB.FruitDb;
import com.example.lz.fruit.R;
import com.example.lz.fruit.entity.Fruit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lz on 2016/7/5.
 */
public class FruitFragment extends BaseFragment{
    View view,view1;
    ListView lv;
    List<Fruit> list;
    FruitAdapter fa;
    FruitDb fd;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fruitfragment,null);
        return view;
    }

    @Override
    public void getview() {
//        view1= getActivity().findViewById(R.id.fruitfragment_list);
        lv = (ListView) getActivity().findViewById(R.id.list);

    }

    @Override
    public void setview() {
         getData();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent(getActivity(), BuyActivity.class);
                Intent intent1 =getActivity().getIntent();
                Bundle bundle1 =intent1.getExtras();
                String username =bundle1.getString("username");
                Bundle bundle =new Bundle();
                Fruit fruit =list.get(position);
                fruit.setUesername(username);
                bundle.putSerializable("fruit",fruit);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
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
                    fd =new FruitDb(getActivity());
                    list =fd.find();
                    if (list!=null) {
                        fa=new FruitAdapter(list,getActivity());
                        lv.setAdapter(fa);
                    }
                    break;
            }

        }
    };

}
