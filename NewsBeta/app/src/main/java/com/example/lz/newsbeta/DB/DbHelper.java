package com.example.lz.newsbeta.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lz on 2016/7/21.
 */
public class DbHelper extends SQLiteOpenHelper {
    static final String USERDB = "user.db";
    static final int VERSION = 1;
    public DbHelper(Context context) {
        super(context,USERDB, null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table news( _id integer ,imgurl text,newsname text,newsurl text,newsdate text,newstype text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
