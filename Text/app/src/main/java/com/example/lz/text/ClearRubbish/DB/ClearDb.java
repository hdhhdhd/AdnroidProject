package com.example.lz.text.ClearRubbish.DB;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by lz on 2016/6/7.
 */
public class ClearDb {
    /**
     * 创建文件
     * @param context 上下文对象
     * @return 返回文件对象
     */
    public static File createFile(Context context){
        File file=null;
        if(Environment.getExternalStorageDirectory().equals(Environment.MEDIA_MOUNTED)){
            file=new File("data"+File.separator+"data"+File.separator+Environment.getExternalStorageDirectory()+File.separator+"MyFile"+File.separator+"clear.db");
        }else{
            file=new File("data"+File.separator+"data"+File.separator+context.getPackageName()+File.separator+"MyFile"+File.separator+"clear.db");
        }
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * 复制数据库文件
     * @param context 上下文对象
     * @param path 本地数据库路径
     * @param tofile 复制到的目标文件
     */
    public static void copyFile(Context context,String path,File tofile){
        AssetManager am=context.getAssets();
        BufferedInputStream bis=null;
        BufferedOutputStream bos=null;
        byte[] b=new byte[1024];
        int len=0;
        try {
            bis=new BufferedInputStream(am.open(path));
            bos=new BufferedOutputStream(new FileOutputStream(tofile));
            while((len=bis.read(b))!=-1){
                bos.write(b,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(bis!=null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bos!=null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
