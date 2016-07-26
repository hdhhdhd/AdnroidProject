package com.example.lz.text.ClearRubbish.View;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lz.text.BaseActivity;
import com.example.lz.text.ClearRubbish.DB.DbClearPathManager;
import com.example.lz.text.FileManager.Tool.FileManager;
import com.example.lz.text.ClearRubbish.ClearRubbishAdapter.ClearRubbishAdapter;
import com.example.lz.text.ClearRubbish.Data.RubbishFileInfo;
import com.example.lz.text.R;
import com.example.lz.text.Tools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lz on 2016/5/19.
 */
public class ClearRubbish extends BaseActivity implements View.OnClickListener{
    private long totalSize = 0; // 用来保存总大小的变量
    private ProgressBar pb_loading;
    private ClearRubbishAdapter rubbishFileAdapter;
    private TextView tv_totalsize,bt;
    View top,lv_rubbishListview;
    List<RubbishFileInfo> list;

    @Override
    public void getView() {
        setContentView(R.layout.clearrubbish);
        top = findViewById(R.id.clearrubbish_title);
        tv_totalsize = (TextView) findViewById(R.id.clearrubbish_size_tv);
        pb_loading = (ProgressBar) findViewById(R.id.clearrubbish_titleloading_pb);
        lv_rubbishListview = findViewById(R.id.clearrubbish_rubbish_lv);
        bt= (TextView) findViewById(R.id.clearrubbish_clear_tv);
    }

    @Override
    public void setView() {
        Tools.setTitle(top, "垃圾清理", this);

        try {
            asyncLoaddata();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                long size = (Long) msg.obj;
                totalSize += size;
                tv_totalsize.setText(Tools.getFileSize(totalSize));
            }
            if (msg.what == 2) {
                Log.i("msg","执行");
                list = (ArrayList<RubbishFileInfo>) msg.obj;
                Log.i("msg","执行"+list.size());
                pb_loading.setVisibility(View.GONE);
                lv_rubbishListview.setVisibility(View.VISIBLE);
                rubbishFileAdapter = new ClearRubbishAdapter(list,ClearRubbish.this);
                Tools.setListView(lv_rubbishListview,rubbishFileAdapter);
                rubbishFileAdapter.setList(list);
                rubbishFileAdapter.notifyDataSetChanged();
                bt.setOnClickListener( ClearRubbish.this);
            }
        }
    };

    private void asyncLoaddata() throws IOException {
//        Log.i("msg","异步加载");
//        InputStream path = getResources().getAssets().open("clearpath.db");
//        Log.i("msg","走到这了");
//        DbClearPathManager.readUpdateDB(path);
//        Log.i("msg", "数据存储");
        File file= DbClearPathManager.createFile(this);
        DbClearPathManager.copyFile(this,"clearpath.db",file);
        final ArrayList<RubbishFileInfo> rubbishFileInfos = DbClearPathManager.getPhoneRubbishfile(getApplicationContext(),file);
        rubbishFileAdapter = new ClearRubbishAdapter(rubbishFileInfos,ClearRubbish.this);
        Tools.setListView(lv_rubbishListview,rubbishFileAdapter);
        Log.i("msg",rubbishFileInfos.size()+"");
        totalSize = 0;
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (RubbishFileInfo rubbishFileInfo : rubbishFileInfos) {
                    File file = new File(rubbishFileInfo.getFilepath());
                    long size = FileManager.getFileSize(file);
                    rubbishFileInfo.setSize(size);
                    // 更新全部大小
                    Message msg = handler.obtainMessage();
                    msg.what = 1;
                    msg.obj = size;
                    handler.sendMessage(msg);
                }
                Log.i("msg","执行");
                // 全部加载完毕 更新UI
                Message msg = handler.obtainMessage();
                msg.what = 2;
                msg.obj = rubbishFileInfos;
                handler.sendMessage(msg);
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clearrubbish_clear_tv:
                delete();
                break;
        }
    }

    void delete() {
        Toast.makeText(this,"执行删除", Toast.LENGTH_SHORT).show();
        list=rubbishFileAdapter.getList();
        if(list.size()>0){
            for (int i = 0; i < list.size(); i++) {
                RubbishFileInfo ri = list.get(i);
                File file = new File(ri.getFilepath());
//            long size = file.length();
                if (file.delete()) {
                    FileManager.getFileManager().deleteFile(file);
                }

            }
            list.clear();
            rubbishFileAdapter.setList(list);
            rubbishFileAdapter.notifyDataSetChanged();
            totalSize=0;
            tv_totalsize.setText(Tools.getFileSize(totalSize));
        }else{
            Toast.makeText(this,"没有可删除项",Toast.LENGTH_SHORT).show();
        }



    }
}
