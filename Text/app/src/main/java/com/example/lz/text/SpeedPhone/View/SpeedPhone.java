package com.example.lz.text.SpeedPhone.View;

import android.app.ActivityManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.lz.text.BaseActivity;
import com.example.lz.text.SpeedPhone.SpeedPhoneAdapter.SpeedPhoneAdapter;
import com.example.lz.text.SpeedPhone.data.AppProcess;
import com.example.lz.text.R;
import com.example.lz.text.Tools;

import java.util.List;

/**
 * Created by lz on 2016/5/19.
 */
public class SpeedPhone extends BaseActivity implements View.OnClickListener{
    TextView phonename,version,ram;
    SpeedPhoneAdapter spa;
    List<AppProcess> list;
    ListView lv;
    View view;
    ToggleButton tb;
    long l;
    String str;
    double free,total,cz,pgb;
    int pgb1;
    Button speed,showprocess;
    int index;
    Thread thread;
    ProgressBar pb,loading;
    private Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    loading.setVisibility(View.GONE);
                    lv.setVisibility(View.VISIBLE);
                    spa.setList(list);
                    spa.notifyDataSetChanged();
                    break;
            }
        }
    };
    @Override
    public void getView() {
        setContentView(R.layout.speedphone);
        lv = (ListView) findViewById(R.id.speedphone_lv);
        phonename = (TextView) findViewById(R.id.speedphone_phonename_tv);
        version = (TextView) findViewById(R.id.speedphone_version_tv);
        ram = (TextView) findViewById(R.id.speedphone_ram_tv);
        view =findViewById(R.id.speedphone_title);
        tb = (ToggleButton) findViewById(R.id.speedphone_selectall_tb);
        speed = (Button) findViewById(R.id.speedphone_clear_btn);
        showprocess = (Button) findViewById(R.id.speedphone_show_btn);
        pb= (ProgressBar) findViewById(R.id.speedphone_ram_pb);
        loading = (ProgressBar) findViewById(R.id.speedphone_loading_pb);
        index =0;
        list =Tools.getAppProcess(this,index);
        spa =new SpeedPhoneAdapter(list,this);
        lv.setAdapter(spa);
    }

    @Override
    public void setView() {
        getData();
        phonename.setText( Build.BRAND);
        version.setText(Build.MODEL + " Android" + Build.VERSION.RELEASE);
        ram.setText(str);
        Tools.setTitle(view,"手机加速",this);
        showprocess.setText("显示系统进程");
        pb.setMax(100);
        pb.setProgress(pgb1);
        setListener();
    }
    public void getData(){
        l=Tools.getPhoneTotalRamMemory()-Tools.getPhoneFreeRamMemory(this);
        str ="已用内存："+Tools.getFileSize(l)+"/"+Tools.getFileSize(Tools.getPhoneTotalRamMemory());
        free =  Tools.getPhoneFreeRamMemory(this);
        total =  Tools.getPhoneTotalRamMemory();
        cz =total -free;
        pgb =cz/total;
        pgb1 = (int) (pgb*100);
    }
    public void setListener(){
        speed.setOnClickListener(this);
        showprocess.setOnClickListener(this);
        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                for (int i=0;i<list.size();i++){
                    list.get(i).setIscheck(isChecked);
                }
                spa.notifyDataSetChanged();
            }
        });
    }
    @Override
    public void onClick(View v) {
        ActivityManager activityManager = (ActivityManager) this.getSystemService(this.ACTIVITY_SERVICE);
        switch (v.getId()){
            case R.id.speedphone_clear_btn:
                        for (int i=0;i<list.size();i++) {
                            if (list.get(i).getIscheck()) {
                                activityManager.killBackgroundProcesses(list.get(i).getPackagename());
                            }
                        }

                thread =new Thread(new Getdata());
                thread.start();
                setView();
                break;
            case R.id.speedphone_show_btn:
                loading.setVisibility(View.VISIBLE);
                lv.setVisibility(View.GONE);
                if(index==0) {
                    index = 1;
                    showprocess.setText("只显示用户进程");

                }else {
                    index = 0;
                    showprocess.setText("显示系统进程");
                }
                thread =new Thread(new Getdata());
                thread.start();
                setView();
                break;
        }
    }
    class Getdata implements Runnable {
        @Override
        public void run() {
            list =Tools.getAppProcess(SpeedPhone.this,index);
            handler.sendEmptyMessage(0);
        }
    }
}
