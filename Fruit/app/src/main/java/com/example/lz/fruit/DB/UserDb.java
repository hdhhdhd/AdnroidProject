package com.example.lz.fruit.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lz.fruit.entity.User;

/**
 * Created by lz on 2016/7/5.
 */
public class UserDb {
    Context context;
    SQLiteDatabase db;

    public UserDb(Context context) {
        this.context = context;
        DbHelper helper = new DbHelper(context);
        db=helper.getReadableDatabase();
    }

    public void add(User user) {
        ContentValues cv = new ContentValues();
        cv.put("name", user.getUsername());
        cv.put("password", user.getPassword());
        cv.put("type",user.getType());
        db.insert("user", null, cv);
    }

    public void change(User u, User u1) {
        ContentValues cv = new ContentValues();
        cv.put("name", u.getUsername());
        db.update("user", cv, "name=?", new String[]{u1.getUsername()});
    }

    public User find(String name) {
        Cursor cursor = null;
        User user = null;
        cursor = db.rawQuery("select * from user where name=?", new String[]{name});
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String username = cursor.getString(cursor.getColumnIndex("name"));
                String password = cursor.getString(cursor.getColumnIndex("password"));
                user = new User(username, password);
            }
        }
        return user;
    }

    public void delete(String name) {
        db.delete("user", "name=?", new String[]{name});
    }
}
