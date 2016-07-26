package com.example.lz.newsbeta.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lz.newsbeta.Entity.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lz on 2016/7/21.
 */
public class CollectDb {
    Context context;
    SQLiteDatabase db;

    public CollectDb(Context context) {
        this.context = context;
        DbHelper helper = new DbHelper(context);
        db=helper.getReadableDatabase();
    }
    public void add(News news) {
        ContentValues cv = new ContentValues();
        cv.put("newsimg", news.getImgurl());
        cv.put("newsname", news.getNewsname());
        cv.put("newsurl",news.getNewsurl());
        cv.put("newsdate",news.getNewsdate());
        cv.put("newstype",news.getNewstype());
        db.insert("fruit", null, cv);
    }


    public List<News> find() {
        Cursor cursor = null;
        List <News> fruit = new ArrayList<News>();
        News f;
        cursor = db.rawQuery("select * from news ", new String[]{});
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String imgurl = cursor.getString(cursor.getColumnIndex("imgurl"));
                String newsname = cursor.getString(cursor.getColumnIndex("newsname"));
                String newsurl = cursor.getString(cursor.getColumnIndex("newsurl"));
                String newsdate = cursor.getString(cursor.getColumnIndex("newsdate"));
                String newstype = cursor.getString(cursor.getColumnIndex("newstype"));
                f=new News(newsname, newsurl,newsdate,newstype,imgurl);
                fruit.add(f);
            }
        }
        return fruit;
    }

    public void delete(String name) {
        db.delete("fruit", "newsname=?", new String[]{name});
    }
}
