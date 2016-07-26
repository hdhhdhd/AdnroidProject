package com.example.lz.fruit.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lz.fruit.entity.Fruit;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lz on 2016/7/5.
 */
public class FruitDb {
    Context context;
    SQLiteDatabase db;

    public FruitDb(Context context) {
        this.context = context;
        DbHelper helper = new DbHelper(context);
        db=helper.getReadableDatabase();
    }
    public void add(Fruit fruit) {
        ContentValues cv = new ContentValues();
        cv.put("name", fruit.getName());
        cv.put("info", fruit.getInfo());
        cv.put("allnum",fruit.getAllnum());
        cv.put("sum",fruit.getSum());
        cv.put("prince",fruit.getPrice());
        cv.put("image",fruit.getImage());
        db.insert("fruit", null, cv);
    }

    public void change(Fruit fruit, Fruit fruit1) {
        ContentValues cv = new ContentValues();
        cv.put("name", fruit.getName());
        db.update("fruit", cv, "name=?", new String[]{fruit1.getName()});
    }

    public List<Fruit> find() {
        Cursor cursor = null;
       List <Fruit> fruit = new ArrayList<Fruit>();
        Fruit f;
        cursor = db.rawQuery("select * from fruit ", new String[]{});
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String info = cursor.getString(cursor.getColumnIndex("info"));
                int allnum =cursor.getInt(cursor.getColumnIndex("allnum"));
                int sum =cursor.getInt(cursor.getColumnIndex("sum"));
                int prince=cursor.getInt(cursor.getColumnIndex("prince"));
                int image =cursor.getInt(cursor.getColumnIndex("image"));
                f=new Fruit(name, info,sum,prince,image);
                f.setAllnum(allnum);
                fruit.add(f);
            }
        }
        return fruit;
    }

    public void delete(String name) {
        db.delete("fruit", "name=?", new String[]{name});
    }
}
