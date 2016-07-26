package com.example.lz.student.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lz on 2016/6/14.
 */
public class UserDbHelper extends SQLiteOpenHelper {
    static final String USERDB = "user.db";
    static final int VERSION = 1;
    public UserDbHelper(Context context) {
        super(context,USERDB, null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user( _id integer ,name text,password text,type text)");
        db.execSQL("create table student( _id integer ,name text,age text,major text,teacher text,degree text,grade text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
