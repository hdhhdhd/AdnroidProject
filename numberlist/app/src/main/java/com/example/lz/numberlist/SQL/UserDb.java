package com.example.lz.numberlist.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lz.numberlist.data.User;

/**
 * Created by lz on 2016/5/11.
 */
public class UserDb {
    Context context;
    SQLiteDatabase db;

    public UserDb(Context context) {
        this.context = context;
        UserDbHelper helper = new UserDbHelper(context);
    }
    public void add(User user){
        ContentValues cv =new ContentValues();
        cv.put("name",user.getUsername());
        cv.put("age",user.getAge());
        db.insert("user","_id",cv);
    }
    public void change(User u,User u1 ){
         ContentValues cv = new ContentValues();
        cv.put("name",u.getUsername());
        db.update("user",cv,"name=?",new String[]{u1.getUsername()});
    }
    public User find(String name){
        Cursor cursor  =null;
        User user = null;
        cursor =db.rawQuery("select * from user where name=?",new  String[]{name});
        if (cursor!=null){
            while (cursor.moveToNext()){
                String username = cursor.getString(cursor.getColumnIndex("name"));
                String userage = cursor.getString(cursor.getColumnIndex("age"));
                user = new User(username,userage);
            }
        }
        return user;
    }
    public void delete(String name){
        db.delete("user","name=?",new String[]{name});
    }
}
