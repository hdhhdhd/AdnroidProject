package com.example.lz.text.Home.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lz.text.Tools;
import com.example.lz.text.R;
import com.example.lz.text.SpeedPhone.View.SpeedPhone;
import com.example.lz.text.AppManager.View.AppManagerMenu;
import com.example.lz.text.CheckPhone.View.CheckPhone;
import com.example.lz.text.FileManager.View.FileManagerActivity;
import com.example.lz.text.ClearRubbish.View.ClearRubbish;
import com.example.lz.text.Home.Adapter.GvAdapter;
import com.example.lz.text.tongxun.View.Mainmenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lz on 2016/5/18.
 */
public class Home extends Activity implements View.OnClickListener{
    ImageView left,right;
    List<HomeTitle> list;
    GvAdapter ga;
    GridView gv;
    ClearView cv;
    TextView tv;
    ImageView iv;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getView();
        setView();
    }
    public void getView(){
        setContentView(R.layout.home);
        gv = (GridView) findViewById(R.id.home_gv);
        getHomeTitle();
        ga =new GvAdapter(this,list);
        gv.setAdapter(ga);
        right = (ImageView) findViewById(R.id.home_right_iv);
        left = (ImageView) findViewById(R.id.home_left_iv);
        right.setOnClickListener(this);
        left.setOnClickListener(this);
        cv= (ClearView) findViewById(R.id.home_clear_cv);
        tv = (TextView) findViewById(R.id.home_clear_tv);
        iv = (ImageView) findViewById(R.id.home_clear_iv);
        btn = (Button) findViewById(R.id.home_clear_btn);

    }
    public void setView(){
        int d = (int) ((Tools.getPhoneTotalRamMemory()-Tools.getPhoneFreeRamMemory(this))*100/Tools.getPhoneTotalRamMemory());
        tv.setText(d+"%");
        int angle=d*360/100;
        cv.setAngleWithAnim(angle);
        iv.setOnClickListener(this);
        btn.setOnClickListener(this);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in;
                switch (position){
                    case 0:
                        in =new Intent(Home.this, SpeedPhone.class);
                        startActivity(in);
                        break;
                    case 1:
                        in =new Intent(Home.this, AppManagerMenu.class);
                        startActivity(in);
                        break;
                    case 2:
                        in =new Intent(Home.this, CheckPhone.class);
                        startActivity(in);
                        break;
                    case 3:
                        in =new Intent(Home.this,Mainmenu.class);
                        startActivity(in);
                        break;
                    case 4:
                        in =new Intent(Home.this,FileManagerActivity.class);
                        startActivity(in);
                        break;
                    case 5:
                        in =new Intent(Home.this,ClearRubbish.class);
                        startActivity(in);
                        break;
                }
            }
        });

    }
    public void getHomeTitle(){
        list=new ArrayList<HomeTitle>();
        HomeTitle ht1 =new HomeTitle("手机加速",SpeedPhone.class,R.mipmap.icon_rocket);
        HomeTitle ht2 =new HomeTitle("软件管理", AppManagerMenu.class,R.mipmap.icon_softmgr);
        HomeTitle ht3 =new HomeTitle("手机检测", CheckPhone.class,R.mipmap.icon_phonemgr);
        HomeTitle ht4 =new HomeTitle("通讯大全", Mainmenu.class,R.mipmap.icon_telmgr);
        HomeTitle ht5 =new HomeTitle("文件管理", FileManagerActivity.class,R.mipmap.icon_filemgr);
        HomeTitle ht6 =new HomeTitle("垃圾清理", ClearRubbish.class,R.mipmap.icon_sdclean);
        list.add(ht1);
        list.add(ht2);
        list.add(ht3);
        list.add(ht4);
        list.add(ht5);
        list.add(ht6);
    }

    @Override
    public void onClick(View v) {
        Intent in;
        int angle=0;
        int d=0;
        switch (v.getId()){
            case R.id.home_right_iv:
                 in =new Intent(this,Setting.class);
                startActivity(in);
                break;
            case R.id.home_left_iv:
                 in =new Intent(this,AboutUs.class);
                startActivity(in);
            case R.id.home_clear_iv:
                Tools.killALLProcesses(this);
               d = (int) ((Tools.getPhoneTotalRamMemory()-Tools.getPhoneFreeRamMemory(this))*100/Tools.getPhoneTotalRamMemory());
               angle =d*360/100;
                cv.setAngleWithAnim(angle);
               break;
            case R.id.home_clear_btn:
                Tools.killALLProcesses(this);
                 d = (int) ((Tools.getPhoneTotalRamMemory()-Tools.getPhoneFreeRamMemory(this))*100/Tools.getPhoneTotalRamMemory());
                 angle=d*360/100;
                cv.setAngleWithAnim(angle);
                break;

        }
    }

}
