package com.example.lz.fruit.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lz on 2016/7/5.
 */
public class DbHelper extends SQLiteOpenHelper {
    static final String USERDB = "user.db";
    static final int VERSION = 1;
    public DbHelper(Context context) {
        super(context,USERDB, null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user( _id integer ,name text,password text,type text)");
        db.execSQL("create table fruit( _id integer ,name text,info text,allnum text,sum text,prince text,image text)");
        db.execSQL("create table shoppingcar( _id integer ,username text,name text,info text,allnum text,sum text,prince text,image text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
