package com.example.lz.text.AppManager.View;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.lz.text.BaseActivity;
import com.example.lz.text.AppManager.MyAdapter.MyAdapter1;
import com.example.lz.text.AppManager.data.AppInfo;
import com.example.lz.text.R;
import com.example.lz.text.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lz on 2016/5/24.
 */
public class SecAppMeanagerMenu extends BaseActivity implements View.OnClickListener{
    View view;
    ListView lv;
    ToggleButton tg;
    MyAdapter1 ma;
    List<AppInfo> list;
    String title;
    TextView delete;
    MyReceiver myReceiver;
    Intent in;
    Bundle b;
    @Override
    public void getView() {
     setContentView(R.layout.showapp);
        lv = (ListView) findViewById(R.id.showapp_lv);
        view =findViewById(R.id.showapp_title);
        tg = (ToggleButton) findViewById(R.id.showapp_allselect_tb);
        delete = (TextView) findViewById(R.id.showapp_delete_tv);
        getReceiver();
    }
 public void getReceiver(){
     myReceiver =new MyReceiver();
     IntentFilter intentFilter=new IntentFilter();
     intentFilter.addDataScheme("package");
     intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
     registerReceiver(myReceiver,intentFilter);
 }
    public void getData(){
        in =this.getIntent();
        b=in.getExtras();
        int index =b.getInt("index");
        title =b.getString("title");
        list =new ArrayList<AppInfo>();
        list = Tools.getAppinfo(this,index);
        ma =new MyAdapter1(list,this);

    }
    @Override
    public void setView() {
        getData();
        Tools.setTitle(view,title,this);
        lv.setAdapter(ma);
        setListener();
    }
  public void setListener(){
      delete.setOnClickListener(this);
      lv.setOnScrollListener(new AbsListView.OnScrollListener() {
          @Override
          public void onScrollStateChanged(AbsListView view, int scrollState) {
              ma = (MyAdapter1) lv.getAdapter();
              switch (scrollState){
                  case SCROLL_STATE_FLING:
                      ma.setFing(true);
                      break;
                  case SCROLL_STATE_TOUCH_SCROLL://1 滑动
                      ma.setFing(false);
                      break;
                  case SCROLL_STATE_IDLE://0停止
                      ma.setFing(false);
                      ma.notifyDataSetChanged();
                      break;
              }
          }

          @Override
          public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

          }
      });
      tg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              for (int i=0;i<list.size();i++){
                  list.get(i).setIscheck(isChecked);
              }
              ma.notifyDataSetChanged();

          }
      });
  }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.showapp_delete_tv:
              MyAdapter1 ma1 = (MyAdapter1) lv.getAdapter();
                List<AppInfo> list1 =ma1.getList();
                for (int i=0;i<list1.size();i++){
                    if (list1.get(i).ischeck()){
                        String packageName = list1.get(i).getPackageInfo().packageName;
                        Intent intent = new Intent(Intent.ACTION_DELETE);
                        intent.setData(Uri.parse("package:" + packageName));
                        startActivity(intent);
                    }
                }
                break;
        }
    }
    class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_PACKAGE_REMOVED)) {
                in =SecAppMeanagerMenu.this.getIntent();
                b = in.getExtras();
                int index =b.getInt("index");
                list = Tools.getAppinfo(SecAppMeanagerMenu.this,index);
                ma.setList(list);
                ma.notifyDataSetChanged();
            }

        }

    }
}
