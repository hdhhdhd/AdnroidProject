package com.example.lz.student.FragmentAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MainActivity extends Activity implements View.OnClickListener{
    public static String path="http://v.juhe.cn/weather/index?format=2&cityname=";
    public static String path1="http://v.juhe.cn/weather/index";
    public static String path2="http://apis.juhe.cn/cook/query.php";

    Button http_get,http_post,client_get,client_post;
    TextView show_tv;
    EditText ed;
    String result;
    String cityname;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    show_tv.setText(result);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        http_get= (Button) findViewById(R.id.main_httpget);
        http_post= (Button) findViewById(R.id.main_httpPOST);
        client_get= (Button) findViewById(R.id.main_clientget);
        client_post= (Button) findViewById(R.id.main_clientpost);
        ed= (EditText) findViewById(R.id.city_ed);
        show_tv= (TextView) findViewById(R.id.show_msg);
        http_post.setOnClickListener(this);
        client_get.setOnClickListener(this);
        http_get.setOnClickListener(this);
        client_post.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_httpget:
                try {
                    cityname=URLEncoder.encode(ed.getText().toString(),"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String pathhttpget=path;
                StringBuffer sb=new StringBuffer(pathhttpget);
                sb.append(cityname);
                sb.append("&key=0c57d6719b5942afbbb88daab00ce39e");//把Key添加
                pathhttpget=sb.toString();
                 loadData(pathhttpget,0);
                break;
            case R.id.main_httpPOST:
                loadData(path2,3);
                break;
            case R.id.main_clientget:
                try {
                    cityname=URLEncoder.encode(ed.getText().toString(),"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String pathhttpget1=path;
                StringBuffer sb1=new StringBuffer(pathhttpget1);
                sb1.append(cityname);
                sb1.append("&key=0c57d6719b5942afbbb88daab00ce39e");//把Key添加
                pathhttpget1=sb1.toString();
                loadData(pathhttpget1,1);

                break;
            case R.id.main_clientpost:

                loadData(path2,2);
                break;
        }
    }
    void loadData(final String path, final int position){
       new Thread(new Runnable() {
           @Override
           public void run() {
               switch (position){
                   case 0:
                       result=HttpUtils.getDataByHttpUrlGET(path);
                       break;
                   case 1:
                       result=HttpUtils.getDataByClientGet(path);
                       break;
                   case 2:
//                       try {
//                           cityname=URLEncoder.encode(ed.getText().toString(),"utf-8");
//                       } catch (UnsupportedEncodingException e) {
//                           e.printStackTrace();
//                       }
                       result=HttpUtils.getDataByClientPost(path2,"家常菜");
                       break;
                   case 3:
                       result=HttpUtils.getDataByHttpUrlPOST(path2,"家常菜");
                       break;
               }

               handler.sendEmptyMessage(0);
           }
       }) .start();
    }

}
