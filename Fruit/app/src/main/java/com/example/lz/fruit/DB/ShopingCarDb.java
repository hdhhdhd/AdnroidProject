package com.example.lz.fruit.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lz.fruit.entity.Fruit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lz on 2016/7/6.
 */
public class ShopingCarDb {
    Context context;
    SQLiteDatabase db;

    public ShopingCarDb(Context context) {
        this.context = context;
        DbHelper helper = new DbHelper(context);
        db=helper.getReadableDatabase();
    }
    public void add(Fruit fruit) {
        ContentValues cv = new ContentValues();
        cv.put("username",fruit.getUesername());
        cv.put("name", fruit.getName());
        cv.put("allnum",fruit.getAllnum());
        cv.put("sum",fruit.getSum());
        cv.put("prince",fruit.getPrice());
        cv.put("image",fruit.getImage());
        db.insert("shoppingcar", null, cv);
    }

    public void changesum(Fruit fruit, Fruit fruit1) {
        ContentValues cv = new ContentValues();
        cv.put("sum", fruit.getSum());
        db.update("shoppingcar", cv, "sum=?", new String[]{fruit1.getSum()+""});
    }

    public List<Fruit> find(String username) {
        Cursor cursor = null;
        List <Fruit> fruit = new ArrayList<Fruit>();
        Fruit f;
        cursor = db.rawQuery("select * from shoppingcar where username=?", new String[]{username});
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int allnum =cursor.getInt(cursor.getColumnIndex("allnum"));
                int sum =cursor.getInt(cursor.getColumnIndex("sum"));
                int prince=cursor.getInt(cursor.getColumnIndex("prince"));
                int image =cursor.getInt(cursor.getColumnIndex("image"));
                f=new Fruit(name,sum,prince,image);
                f.setAllnum(allnum);
                fruit.add(f);
            }
        }
        return fruit;
    }

    public void delete(String username) {
        db.delete("shoppingcar", "username=?", new String[]{username});
    }
    public void deleteone(String name){
        db.delete("shoppingcar","name=?",new String[]{name});
    }
}
