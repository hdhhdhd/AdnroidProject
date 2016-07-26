package com.example.lz.myapplication1;

import java.io.Serializable;

/**
 * Created by lz on 2016/5/7.
 */
public class Student implements Serializable{
    private String name;
    private String age;
    private String major;
    private String teacher;
    private String degree;
    private String grade;

    public Student(String name, String age, String major, String teacher, String degree, String grade) {
        this.name = name;
        this.age = age;
        this.major = major;
        this.teacher = teacher;
        this.degree = degree;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
