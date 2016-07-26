package com.example.lz.text;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.os.Debug;
import android.os.Environment;
import android.os.StatFs;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lz.text.SpeedPhone.data.AppProcess;
import com.example.lz.text.AppManager.data.AppInfo;
import com.example.lz.text.tongxun.data.Classlist;
import com.example.lz.text.tongxun.data.NumberInfo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by lz on 2016/5/20.
 */
public class Tools {

    public static void Copy(Context context, String path, File tofile){
        AssetManager am =context.getAssets();
        BufferedInputStream bis=null;
        BufferedOutputStream bos= null;
        byte b[]=new byte[1024];
        int len = 0;
        try {
            bis =new BufferedInputStream(am.open(path));
            bos = new BufferedOutputStream(new FileOutputStream(tofile));
            while ((len=bis.read(b))!=-1){
                bos.write(b,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bis!=null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos!=null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void Createfile(File file){
        if (!file.getParentFile().exists()){
            file.mkdirs();
        }
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static List<Classlist> getClasslist(File file){
        SQLiteDatabase db =null;
        db = SQLiteDatabase.openOrCreateDatabase(file,null);
        List<Classlist> list = new ArrayList<Classlist>();
        Cursor cursor = db.rawQuery("select * from classlist",null);
        if (cursor!=null){

            while (cursor.moveToNext()){
                int index = cursor.getInt(cursor.getColumnIndex("idx"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                Classlist cl= new Classlist(name,index);
                list.add(cl);
            }
        }
        if (cursor!=null){
            cursor.close();
        }
        if (db!=null) {
            db.close();
        }
        return list;
    }
    public static List<NumberInfo> getNumberInfo(File file, int index){
        SQLiteDatabase db =null;
        db =SQLiteDatabase.openOrCreateDatabase(file,null);
        List<NumberInfo> list =new ArrayList<NumberInfo>();
        Cursor cursor = db.rawQuery("select * from table"+index,null);
        if (cursor!=null){
            while (cursor.moveToNext()){
                String num = cursor.getString(cursor.getColumnIndex("number"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                NumberInfo ni= new NumberInfo(name,num);
                list.add(ni);
            }
        }
        if (cursor!=null){
            cursor.close();
        }
        if (db!=null) {
            db.close();
        }
        return list;
    }
    public static void setTitle(View v, String title, final Activity activity){
        TextView  tv = (TextView) v.findViewById(R.id.sontitle_title_tv);
        tv.setText(title);
        ImageView iv= (ImageView) v.findViewById(R.id.sontitle_back_iv);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }
    public static void setListView(View v, BaseAdapter adapter){
        ListView lv = (ListView) v.findViewById(R.id.clearrubbish_lv);
        lv.setAdapter(adapter);
    }
    public static List<AppInfo> getAppinfo(Context context,int index){
        PackageManager pm=context.getPackageManager();
        List<AppInfo> allapplist=new ArrayList<AppInfo>();
        List<AppInfo> systemapplist=new ArrayList<AppInfo>();
        List<AppInfo> userapplist=new ArrayList<AppInfo>();
        List<PackageInfo> list=pm.getInstalledPackages(0);
        for (int i = 0; i < list.size(); i++) {
            allapplist.add(new AppInfo(list.get(i), false));
        }
        if (index==0) {

          return allapplist;
        }else if (index==1){
            for (int i=0;i<list.size();i++){
                PackageInfo pk=allapplist.get(i).getPackageInfo();
                if((pk.applicationInfo.flags&pk.applicationInfo.FLAG_SYSTEM)>0){
                    systemapplist.add(new AppInfo(list.get(i), false));
                }
            }
            return systemapplist;
        }else {

            for (int i=0;i<list.size();i++){
                PackageInfo pk=allapplist.get(i).getPackageInfo();
                if((pk.applicationInfo.flags&pk.applicationInfo.FLAG_SYSTEM)<=0){
                    userapplist.add(new AppInfo(list.get(i), false));
                }
            }
            return userapplist;
        }
    }
    public static List<AppProcess> getAppProcess(Context context, int index){
        ActivityManager am = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
        PackageManager pm =context.getPackageManager();
        List<ActivityManager.RunningAppProcessInfo> raplist =am.getRunningAppProcesses();
        List<AppProcess> userlist =new ArrayList<AppProcess>();
        List<AppProcess> systemlist = new ArrayList<AppProcess>();
            for (int i=0;i<raplist.size();i++){
                String packageName = raplist.get(i).processName; // 正在运行程序进程名
                int pid = raplist.get(i).pid; // 正在运行程序进程ID
                int importance = raplist.get(i).importance; // 正在运行程序进程级别
                // 服务进程（包括）级别以下进程
                if (importance >= ActivityManager.RunningAppProcessInfo.IMPORTANCE_SERVICE) {
                    Drawable icon; // 所取数据：运行中程序图标
                    String lableName; // 所取数据：运行中程序名称
                    long size; // 所取数据：运行中程序所占内存
                    Debug.MemoryInfo[] memoryInfos = am.getProcessMemoryInfo(new int[] { pid });
                    size = (memoryInfos[0].getTotalPrivateDirty()) * 1024;
                    String str ="内存："+Tools.getFileSize(size);
                        try {
                            icon = pm.getApplicationIcon(packageName);
                           ApplicationInfo applicationInfo = null;
                            applicationInfo = pm.getApplicationInfo(packageName, PackageManager.GET_META_DATA | PackageManager.GET_SHARED_LIBRARY_FILES );
                            lableName = pm.getApplicationLabel(applicationInfo).toString();
                            if ((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                                String type = "系统进程";
                                AppProcess appProcess = new AppProcess(lableName,packageName, str,icon,type, false);
                                systemlist.add(appProcess);
                            }
                            // 用户进程(默认选中)
                            else {
                                String type = "用户进程";
                                AppProcess appProcess = new AppProcess(lableName,packageName,str,icon,type, true);
                                userlist.add(appProcess);
                            }
                            //实例化的时候没有被选中
                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                        }
                }
        }if (index==0){
            return userlist;
        }else {
            return systemlist;
        }

    }
    public static long getPhoneTotalRamMemory() {
        try {
            FileReader fr = new FileReader("/proc/meminfo");
            BufferedReader br = new BufferedReader(fr);
            String text = br.readLine();
            String[] array = text.split("\\s+");
            return Long.valueOf(array[1]) * 1024; // 原为kb, 转为b
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static long getPhoneFreeRamMemory(Context context) {
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        am.getMemoryInfo(info);
        return info.availMem;
    }
    public static int getPhoneCpuNumber() {
        class CpuFilter implements FileFilter {
            public boolean accept(File pathname) {
                if (Pattern.matches("cpu[0-9]", pathname.getName())) {
                    return true;
                }
                return false;
            }
        }
        try {
            File dir = new File("/sys/devices/system/cpu/");
            File[] files = dir.listFiles(new CpuFilter());
            return files.length;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
    public  static String getPhoneCpuName() {
        try {
            FileReader fr = new FileReader("/proc/cpuinfo");
            BufferedReader br = new BufferedReader(fr);
            String text = br.readLine();
            String[] array = text.split(":\\s+", 2);
            return array[1];
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String getResolution(Context context) {
        String resolution = "";
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        resolution = metrics.widthPixels + "*" + metrics.heightPixels;
        return resolution;
    }
    public static String getFileSize(long filesize) {
        DecimalFormat df = new DecimalFormat("#.00");
        StringBuffer mstrbuf = new StringBuffer();
        if (filesize < 1024) {
            mstrbuf.append(filesize);
            mstrbuf.append(" B");
        } else if (filesize < 1048576) {
            mstrbuf.append(df.format((double) filesize / 1024));
            mstrbuf.append(" K");
        } else if (filesize < 1073741824) {
            mstrbuf.append(df.format((double) filesize / 1048576));
            mstrbuf.append(" M");
        } else {
            mstrbuf.append(df.format((double) filesize / 1073741824));
            mstrbuf.append(" G");
        }
        return mstrbuf.toString();
    }
    public static String getMaxPhotoSize() {
        String maxSize = "";
        Camera camera = Camera.open();
        Camera.Parameters parameters = camera.getParameters();
        List<Size> sizes = parameters.getSupportedPictureSizes();
        Size size = null;
        for (Size s : sizes) {
            if (size == null) {
                size = s;
            } else if (size.height * s.width < s.height * s.width) {
                size = s;
            }
        }
        maxSize = size.width + "*" + size.height;
        camera.release();
        return maxSize;
    }
    /** 获取手机内置sdcard路径, 为null表示无 */
    public static String getPhoneInSDCardPath() {
        String sdcardState = Environment.getExternalStorageState();
        if (!sdcardState.equals(Environment.MEDIA_MOUNTED)) {
            return null;
        }
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    /** 获取手机外置sdcard路径, 为null表示无 */
    public static String getPhoneOutSDCardPath() {
        Map<String, String> map = System.getenv();
        if (map.containsKey("SECONDARY_STORAGE")) {
            String paths = map.get("SECONDARY_STORAGE");
            // /storage/extSdCard
            String path[] = paths.split(":");
            if (path == null || path.length <= 0) {
                return null;
            }
            return path[0];
        }
        return null;
    }
    /** 设备外置存储SDCard全部大小 单位B , 当没有外置卡时,大小为0 */
    public static long getPhoneOutSDCardSize(Context context) {
        try {
            File path = new File(getPhoneOutSDCardPath());
            if (path == null) {
                return 0;
            }
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long blockCount = stat.getBlockCount();
            return blockCount * blockSize;
        } catch (Exception e) {
//            Toast.makeText(context, "外置存储卡异常", Toast.LENGTH_SHORT).show();
            return 0;
        }
    }

    /** 设备外置存储SDCard空闲大小 单位B */
    public static long getPhoneOutSDCardFreeSize(Context context) {
        try {
            File path = new File(getPhoneOutSDCardPath());
            if (path == null) {
                return 0;
            }
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long availaBlock = stat.getAvailableBlocks();
            return availaBlock * blockSize;
        } catch (Exception e) {
//            Toast.makeText(context, "外置存储卡异常", Toast.LENGTH_SHORT).show();
            return 0;
        }
    }
    /** 设备内置存储卡全部大小(手机自带32G存储空间?) 单位B */
    public static long getPhoneSelfSDCardSize() {
        String sdcardState = Environment.getExternalStorageState();
        if (!sdcardState.equals(Environment.MEDIA_MOUNTED)) {
            return 0;
        }
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long blockCount = stat.getBlockCount();
        return blockCount * blockSize;
    }

    /** 设备内置存储卡空间大小(手机自带32G存储空间?) 单位B */
    public static long getPhoneSelfSDCardFreeSize() {
        String sdcardState = Environment.getExternalStorageState();
        if (!sdcardState.equals(Environment.MEDIA_MOUNTED)) {
            return 0;
        }
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availaBlock = stat.getAvailableBlocks();
        return availaBlock * blockSize;
    }
    /**获取手机总存储大小*/
    public static long getPhoneAllSize() {
        File path = Environment.getRootDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long blockCount = stat.getBlockCount();
        long rootFileSize = blockCount * blockSize;

        path = Environment.getDataDirectory();
        stat = new StatFs(path.getPath());
        blockSize = stat.getBlockSize();
        blockCount = stat.getBlockCount();
        long dataFileSize = blockCount * blockSize;

        path = Environment.getDownloadCacheDirectory();
        stat = new StatFs(path.getPath());
        blockSize = stat.getBlockSize();
        blockCount = stat.getBlockCount();
        long cacheFileSize = blockCount * blockSize;

        return rootFileSize + dataFileSize + cacheFileSize;
    }
    /** 清理所有正在运行的程序(级别为服务进程以上的非系统进程) */
    public static void killALLProcesses(Context context) {
        PackageManager packageManager = context.getPackageManager();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcessInfos = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcessInfo : appProcessInfos) {
            if (appProcessInfo.importance >= ActivityManager.RunningAppProcessInfo.IMPORTANCE_SERVICE) {
                String packageName = appProcessInfo.processName;
                try {
                    ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA | PackageManager.GET_SHARED_LIBRARY_FILES | PackageManager.GET_SHARED_LIBRARY_FILES | PackageManager.GET_UNINSTALLED_PACKAGES);
                    if ((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                    } else {
                        activityManager.killBackgroundProcesses(packageName);
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
    public static boolean isRoot() {
        boolean bool = false;

        try {
            if ((!new File("/system/bin/su").exists()) && (!new File("/system/xbin/su").exists())) {
                bool = false;
            } else {
                bool = true;
            }
        } catch (Exception e) {

        }
        return bool;
    }

}
