package com.example.lz.text.CheckPhone.View;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.lz.text.BaseActivity;
import com.example.lz.text.CheckPhone.PhoneInfoAdapter.PhoneInfoAdapter;
import com.example.lz.text.CheckPhone.data.PhoneInfo;
import com.example.lz.text.R;
import com.example.lz.text.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lz on 2016/5/19.
 */
public class CheckPhone extends BaseActivity implements View.OnClickListener{
    PhoneInfoAdapter pa;
    ListView lv;
    List<PhoneInfo> list;
    TextView tv;
    ProgressBar pb;
    MyReceiver mr;
    View view;


    @Override
    public void getView() {
        setContentView(R.layout.checkphone);
        lv = (ListView) findViewById(R.id.checkphone_phoneinfo_lv);

        tv = (TextView) findViewById(R.id.checkphone_nowbattery_tv);
        pb = (ProgressBar) findViewById(R.id.checkphone_Battery_pb);
        view =findViewById(R.id.checkphone_title);
        getReceiver();
    }
   public void getReceiver(){
       mr =new MyReceiver();
       IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
       registerReceiver(mr,intentFilter);
   }
    public void getInfo() {
        list = new ArrayList<PhoneInfo>();
        list.add(new PhoneInfo(Build.MODEL, Build.VERSION.RELEASE, R.mipmap.setting_info_icon_version));
        long l = Tools.getPhoneTotalRamMemory();
        long l1 = Tools.getPhoneFreeRamMemory(this);
        String str = "全部运行内存" + Tools.getFileSize(l);
        String str1 = "剩余运行内存" + Tools.getFileSize(l1);
        list.add(new PhoneInfo(str, str1, R.mipmap.setting_info_icon_space));
        String cpuname = "CPU名称" + Tools.getPhoneCpuName();
        String cpunum = "CPU数量" + (Tools.getPhoneCpuNumber() + "");
        list.add(new PhoneInfo(cpuname, cpunum, R.mipmap.setting_info_icon_cpu));
        String res = Tools.getResolution(this);
        String camera = Tools.getMaxPhotoSize();
        list.add(new PhoneInfo(res, camera, R.mipmap.setting_info_icon_camera));
        String root = "";
        if (Tools.isRoot()) {
            root = "是否root 是";
        } else {
            root = "是否root 否";
        }
        list.add(new PhoneInfo("基带版本" + Build.RADIO, root, R.mipmap.setting_info_icon_root));
        pa = new PhoneInfoAdapter(this, list);
    }

    @Override
    public void setView() {
        getInfo();

        lv.setAdapter(pa);
        pb.setOnClickListener(this);
        Tools.setTitle(view,"手机检测",this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.checkphone_Battery_pb:
                Intent intent =new Intent();
                AlertDialog.Builder ab =new AlertDialog.Builder(CheckPhone.this);
                ab.setTitle("电池电量信息");
                ab.setMessage("当前电量："+mr.level+"\n"+"电池温度："+mr.t);
                ab.create().show();
                break;

        }
    }

    class MyReceiver extends BroadcastReceiver {
        int level;
        double t;
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
                t=intent.getIntExtra("temperature", 0);
               level  = intent.getIntExtra("level", 0);
                int scale = intent.getIntExtra("scale", 100);
                pb.setProgress(level);
                tv.setText(((level * 100) / scale) + "%");
                pa.notifyDataSetChanged();
            }
        }
    }
}
