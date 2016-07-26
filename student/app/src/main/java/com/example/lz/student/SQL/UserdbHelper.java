package com.example.lz.student.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lz on 2016/5/16.
 */
public class UserdbHelper extends SQLiteOpenHelper {
    static final String USERDB = "user.db";
    static final int VERSION = 1;
    public UserdbHelper(Context context) {
        super(context,  USERDB, null,VERSION);
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
