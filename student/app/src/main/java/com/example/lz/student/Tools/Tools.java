package com.example.lz.student.Tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lz.student.SQL.UserdbHelper;
import com.example.lz.student.entity.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lz on 2016/5/17.
 */
public class Tools {
    SQLiteDatabase db;
    List<Student> list;
    public List<Student> find(Context context,String name){
        list =new ArrayList<Student>();
        Cursor cursor =null;
        Student student=null;
        cursor = db.rawQuery("select * from student where name=?",null);
        if (cursor!=null){
            while (cursor.moveToNext()){
                String name1 =cursor.getString(cursor.getColumnIndex("name"));
                String age =cursor.getString(cursor.getColumnIndex("age"));
                String major =cursor.getString(cursor.getColumnIndex("major"));
                String taeacher=cursor.getString(cursor.getColumnIndex("teacher"));
                String degree =cursor.getString(cursor.getColumnIndex("degree"));
                String grade = cursor.getString(cursor.getColumnIndex("grade"));
                student =new Student(name1,age,major,taeacher,degree,grade);
                list.add(student);
            }
        }
        return list;
    }
    public static void goTo(Activity a,Class c){
        Intent in = new Intent(a,c);
        a.startActivity(in);
    }
}
