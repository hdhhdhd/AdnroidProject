package com.example.lz.numberlist;


import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.util.Log;




import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lz on 2016/5/10.
 */
public class Tools {

    public static void Copy(Context context, String path, File tofile) {
        AssetManager am = context.getAssets();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        byte b[] = new byte[1024];
        int len = 0;
        try {
            bis=new BufferedInputStream(am.open(path));
            bos=new BufferedOutputStream(new FileOutputStream(tofile));
            while((len=bis.read(b))!=-1){
                bos.write(b,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(bos!=null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bis!=null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

//        try {
//            bis = new BufferedInputStream(am.open(path));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            bos = new BufferedOutputStream(new FileOutputStream(tofile));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            while ((len = bis.read(b))!=-1) {
//                bos.write(b, 0, len);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        if (bis != null) {
//            try {
//                bis.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public static void Create(File file) {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();

        }
        if (!file.exists()) {

            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static SQLiteDatabase openSQL(File file){
        SQLiteDatabase db=null;
        db=SQLiteDatabase.openOrCreateDatabase(file,null);
        return db;
    }
}