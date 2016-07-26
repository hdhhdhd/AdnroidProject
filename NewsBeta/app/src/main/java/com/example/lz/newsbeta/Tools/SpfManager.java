package com.example.lz.newsbeta.Tools;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by lz on 2016/7/19.
 */
public class SpfManager {
    static Context mC;
    SharedPreferences.Editor spfe;
    SharedPreferences spf;
    private SpfManager(){

    }
    private static SpfManager spfManager;

    public static SpfManager getspfManager(Context context){
        mC=context;
        if(spfManager==null){
            spfManager=new SpfManager();
        }
        return spfManager;
    }

    /**
     * 获取共享参数保存实例
     * @param name
     */
    public void savemassage(String name){
        spfe=mC.getSharedPreferences(name,Context.MODE_PRIVATE).edit();
    }

    /**
     * 获取共享参数获取实例
     * @param name
     */
    public void getmassage(String name){
        spf=mC.getSharedPreferences(name,Context.MODE_PRIVATE);
    }

    /**
     * 是否登录方法
     * @return 返回登录的用户名，没有账户登录则返回no
     */
    public String islogin(){
        getmassage("user");
        String name=spf.getString("name","no");
        return name;
    }

    /**
     * 保存登录用户名
     * @param name
     */
    public void savelogin(String name){
        savemassage("user");
        spfe.putString("name",name);
        spfe.commit();
    }

    /**
     * 保存tab是否为显示状态
     * @param id
     * @param isshow
     */
    public void saveshow(String id,String isshow){
        savemassage("isshow");
        spfe.putString(id,isshow);
        spfe.commit();
    }

    /**
     * 加载tab是否为显示状态
     * @param id
     * @return
     */
    public String getshow(String id){
        getmassage("isshow");
        String isshow= spf.getString(id,"no");
        return isshow;
    }

    /**
     * 是否初次登录
     * @param
     * @return
     */
    public String isone(){
        getmassage("isone");
        String isone=spf.getString("one","yesone");
        return isone;
    }
    public void saveone(String s){
        savemassage("isone");
        spfe.putString("one",s);
        spfe.commit();
    }
}
