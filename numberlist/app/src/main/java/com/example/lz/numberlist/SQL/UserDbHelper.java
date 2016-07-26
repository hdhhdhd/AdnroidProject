package com.example.lz.numberlist.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lz on 2016/5/11.
 */
public class UserDbHelper extends SQLiteOpenHelper{
    static final String USERDB = "user.db";
    static final int VERSION = 1;
    public UserDbHelper(Context context) {
        super(context, USERDB, null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user( _id integer PRIMARY KEY autoincrementname,name text,age text)");
}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
