package com.example.lz.numberlist2;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lz.numberlist2.data.Classlist;
import com.example.lz.numberlist2.data.NumberInfo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lz on 2016/5/13.
 */
public class Tools {
    public static void Copy(Context context, String path, File tofile){
        AssetManager am =context.getAssets();
        BufferedInputStream bis=null;
        BufferedOutputStream bos= null;
        byte b[]=new byte[1024];
        int len = 0;
        try {
            bis =new BufferedInputStream(am.open(path));
            bos = new BufferedOutputStream(new FileOutputStream(tofile));
            while ((len=bis.read(b))!=-1){
                bos.write(b,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bis!=null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos!=null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void Createfile(File file){
        if (!file.getParentFile().exists()){
            file.mkdirs();
        }
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static List<Classlist>  getClasslist(File file){
        SQLiteDatabase db =null;
        db = SQLiteDatabase.openOrCreateDatabase(file,null);
        List<Classlist> list = new ArrayList<Classlist>();
        Cursor cursor = db.rawQuery("select * from classlist",null);
        if (cursor!=null){

            while (cursor.moveToNext()){
                int index = cursor.getInt(cursor.getColumnIndex("idx"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                Classlist cl= new Classlist(name,index);
                list.add(cl);
            }
        }
        if (cursor!=null){
            cursor.close();
        }
        if (db!=null) {
            db.close();
        }
        return list;
    }
    public static List<NumberInfo> getNumberInfo(File file,int index){
        SQLiteDatabase db =null;
        db =SQLiteDatabase.openOrCreateDatabase(file,null);
        List<NumberInfo> list =new ArrayList<NumberInfo>();
        Cursor cursor = db.rawQuery("select * from table"+index,null);
        if (cursor!=null){
            while (cursor.moveToNext()){
                String num = cursor.getString(cursor.getColumnIndex("number"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                NumberInfo ni= new NumberInfo(name,num);
                list.add(ni);
            }
        }
        if (cursor!=null){
            cursor.close();
        }
        if (db!=null) {
            db.close();
        }
        return list;
    }
}
