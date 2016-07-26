package com.example.lz.text.FileManager.FileManagerAdapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.lz.text.FileManager.data.FileInfo;
import com.example.lz.text.R;
import com.example.lz.text.Tools;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by lz on 2016/6/2.
 */
public class FileManagerAdapter extends BaseAdapter{
    List<FileInfo> list;
    Context context;
    int normalid;
    boolean isFing;

    public FileManagerAdapter(List<FileInfo> list, Context context) {
        this.list = list;
        this.context = context;
        normalid= R.mipmap.ic_launcher;
    }

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

    public boolean isFing() {
        return isFing;
    }

    public void setFing(boolean fing) {
        isFing = fing;
    }

    public List<FileInfo> getList() {

        return list;
    }

    public void setList(List<FileInfo> list) {
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh =null;
        if (convertView==null){
            vh=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.secfilemanageritem,null);
            vh.icon = (ImageView) convertView.findViewById(R.id.secfilemanageritem_icon_iv);
            vh.packagename = (TextView) convertView.findViewById(R.id.secfilemanageritem_packagename_tv);
            vh.size = (TextView) convertView.findViewById(R.id.secfilemanageritem_size_tv);
            vh.time = (TextView) convertView.findViewById(R.id.secfilemanageritem_time_tv);
            vh.tb = (ToggleButton) convertView.findViewById(R.id.secfilemanageritem_select_tb);
            convertView.setTag(vh);
        }else {
            vh= (ViewHolder) convertView.getTag();
        }
         FileInfo fi =list.get(position);
        File file =fi.getFile();
        vh.packagename.setText(file.getName());
        SimpleDateFormat sd =new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        vh.size.setText(Tools.getFileSize(file.length()));
        vh.time.setText(sd.format(file.lastModified()));
        vh.tb.setTag(position);
       if (isFing){
           vh.icon.setImageResource(normalid);
       }else {
           Resources resources =context.getResources();
           int icon = resources.getIdentifier(fi.getIcon(),"drawable",context.getPackageName());
           if (icon<=0){
               icon =R.drawable.icon_file;
           }
           Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),icon);
           vh.icon.setImageBitmap(bitmap);
       }
            vh.tb.setOnCheckedChangeListener(onCheckedChangeListener);
        return convertView;
    }class ViewHolder{
        ImageView icon;
        TextView packagename,time,size;
        ToggleButton tb;
    }CompoundButton.OnCheckedChangeListener onCheckedChangeListener= new CompoundButton.OnCheckedChangeListener(){

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            int position= (int) buttonView.getTag();
            list.get(position).setIscheck(isChecked);
        }
    };
}
