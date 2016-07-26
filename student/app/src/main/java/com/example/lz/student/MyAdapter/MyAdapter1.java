package com.example.lz.student.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lz.student.R;
import com.example.lz.student.entity.Student;

import java.util.List;

/**
 * Created by lz on 2016/5/17.
 */
public class MyAdapter1 extends BaseAdapter{
    Context context;
    List<Student> list;
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh= null;
        if (convertView==null){
            vh =new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.allinfo,null);
            vh.name= (TextView) convertView.findViewById(R.id.allinfo_name_tv);
            vh.age = (TextView) convertView.findViewById(R.id.allinfo_age_tv);
            vh.major = (TextView) convertView.findViewById(R.id.allinfo_major_tv);
            vh.teacher= (TextView) convertView.findViewById(R.id.allinfo_teacher_tv);
            vh.degree = (TextView) convertView.findViewById(R.id.allinfo_degree_tv);
            vh.grade = (TextView) convertView.findViewById(R.id.allinfo_grade_tv);
            convertView.setTag(vh);
        }else {
            vh= (ViewHolder) convertView.getTag();
        }
        Student student =list.get(position);
        vh.name.setText(student.getName());
        vh.age.setText(student.getAge());
        vh.teacher.setText(student.getTeacher());
        vh.major.setText(student.getMajor());
        vh.grade.setText(student.getGrade());
        vh.degree.setText(student.getDegree());
        return convertView;
    }class ViewHolder{
        TextView name;
        TextView major;
        TextView age;
        TextView teacher;
        TextView degree;
        TextView grade;
    }
}
