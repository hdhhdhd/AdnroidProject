package com.example.lz.student.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lz.student.entity.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lz on 2016/5/17.
 */
public class Studentdb {
    Context context;
    SQLiteDatabase db;
    public Studentdb(Context context) {
        this.context = context;
        UserdbHelper helper = new UserdbHelper(context);
        db =helper.getReadableDatabase();
    }
    public void add(Student student){
        ContentValues cv =new ContentValues();
        cv.put("name",student.getName());
        cv.put("age",student.getAge());
        cv.put("major",student.getMajor());
        cv.put("teacher",student.getTeacher());
        cv.put("degree",student.getDegree());
        cv.put("grade",student.getGrade());
        db.insert("student",null,cv);
    }
    public void delete(String name){
        db.delete("student", "name=?", new String[]{name});
    }
    public void change(Student student,Student stu1){
        ContentValues cv =new ContentValues();
        cv.put("name",student.getName());
        cv.put("age",student.getAge());
        cv.put("major",student.getMajor());
        cv.put("teacher",student.getTeacher());
        cv.put("degree",student.getDegree());
        cv.put("grade",student.getGrade());
        db.update("student",cv,"name=?",new String[]{stu1.getName()});
    }
    public Student find(String name){
        Cursor cursor =null;
        Student student=null;
        cursor = db.rawQuery("select * from student where name=?",new String[]{name});
        if (cursor!=null){
            while (cursor.moveToNext()){
                String name1 =cursor.getString(cursor.getColumnIndex("name"));
                String age =cursor.getString(cursor.getColumnIndex("age"));
                String major =cursor.getString(cursor.getColumnIndex("major"));
                String taeacher=cursor.getString(cursor.getColumnIndex("teacher"));
                String degree =cursor.getString(cursor.getColumnIndex("degree"));
                String grade = cursor.getString(cursor.getColumnIndex("grade"));
                student =new Student(name1,age,major,taeacher,degree,grade);
            }
        }
        return student;
    }

    public List<Student> find(){
        List<Student> list=null;
        Cursor cursor =null;
        Student student=null;
        cursor = db.rawQuery("select * from student ",null);
        list =new ArrayList<Student>();
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
}
