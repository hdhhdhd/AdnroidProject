package com.example.lz.text.FileManager.View;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lz.text.BaseActivity;
import com.example.lz.text.FileManager.FileManagerAdapter.FileManagerAdapter;
import com.example.lz.text.FileManager.Tool.Filetypechange;
import com.example.lz.text.FileManager.Tool.FileManager;
import com.example.lz.text.FileManager.data.FileInfo;
import com.example.lz.text.R;
import com.example.lz.text.Tools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lz on 2016/6/2.
 */
public class SecFileManagerActivity extends BaseActivity{
    ListView lv;
    TextView delete,filenum,filespace;
    FileManagerAdapter fma;
    long fileSize = 0;
    long fileNumber = 0;
    ArrayList<FileInfo> fileInfos;
    View view;
    String  title;
    int id =-1;
    @Override
    public void getView() {
         setContentView(R.layout.secfilemanager);
        lv = (ListView) findViewById(R.id.secfilemanager_lv);
        delete = (TextView) findViewById(R.id.secfilemanager_delete_tv);
        filenum = (TextView) findViewById(R.id.secfilemanager_showfilenum_tv);
        filespace = (TextView) findViewById(R.id.secfilemanager_showfilespace_tv);
        view =findViewById(R.id.secfilemanager_title);
    }
    public void getData() {
        id = this.getIntent().getIntExtra("id", -1);
        switch (id) {
            case R.id.filemanager_allfile_rl:
                fileInfos= FileManager.getFileManager().getAnyFileList();
                fileSize=FileManager.getFileManager().getAnyFileSize();
                fileNumber=fileInfos.size();
                title="全部文件";
                break;
            case R.id.filemanager_apkfile_rl:
                fileInfos= FileManager.getFileManager().getApkFileList();
                fileSize=FileManager.getFileManager().getApkFileSize();
                fileNumber=fileInfos.size();
                title="程序包";
                break;
            case R.id.filemanager_audiofile_rl:
                fileInfos= FileManager.getFileManager().getAudioFileList();
                fileSize=FileManager.getFileManager().getAudioFileSize();
                fileNumber=fileInfos.size();
                title="音频文件";
                break;
            case R.id.filemanager_imagefile_rl:
                title="图像";
                fileInfos= FileManager.getFileManager().getImageFileList();
                fileSize=FileManager.getFileManager().getImageFileSize();
                fileNumber=fileInfos.size();
                break;
            case R.id.filemanager_txtfile_rl:
                title="文档";
                fileInfos= FileManager.getFileManager().getTxtFileList();
                fileSize=FileManager.getFileManager().getTxtFileSize();
                fileNumber=fileInfos.size();
                break;
            case R.id.filemanager_zipfile_rl:
                title="压缩包";
                fileInfos= FileManager.getFileManager().getZipFileList();
                fileSize=FileManager.getFileManager().getZipFileSize();
                fileNumber=fileInfos.size();
                break;
            case R.id.filemanager_videofile_rl:
                title="视频";
                fileInfos= FileManager.getFileManager().getVideoFileList();
                fileSize=FileManager.getFileManager().getVideoFileSize();
                fileNumber=fileInfos.size();
                break;
        }
        fma=new FileManagerAdapter(fileInfos,this);

    }
    @Override
    public void setView() {
        getData();
        Tools.setTitle(view,title,this);
        filenum.setText(fileNumber+"个");
        filespace.setText(Tools.getFileSize(fileSize));
        lv.setAdapter(fma);
        setListener();
     }
    public void setListener(){
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FileInfo fileInfo = fma.getList().get(position);
                File file = fileInfo.getFile();
                // 取出此文件的后缀名　－> MIMEType
                String type = Filetypechange.getMIMEType(file);
                // 打开这个文件
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(file), type);
                startActivity(intent);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delFile();
            }
        });
        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState){
                    case 0:
                        fma.setFing(false);
                        fma.notifyDataSetChanged();
                        break;
                    case 1:
                        fma.setFing(false);
                        break;
                    case 2:
                        fma.setFing(true);
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }
    public void delFile() {
        // 用来保存所有选中的删除文件
        List<FileInfo> delFileInfos = new ArrayList<FileInfo>();
        for (int i = 0; i < fma.getList().size(); i++) {
            FileInfo fileInfo = fileInfos.get(i);
            // 已选中的
            if (fileInfo.ischeck()) {
                delFileInfos.add(fileInfo);
            }
        }
        // 删除所选中的文件
        for (int i = 0; i < delFileInfos.size(); i++) {
            FileInfo fileInfo = delFileInfos.get(i);
            File file = fileInfo.getFile();
            long size = file.length();
            if (file.delete()) {
                FileManager.getFileManager().deleteFile(file);
                fma.getList().remove(fileInfo);
                FileManager.getFileManager().getAnyFileList().remove(fileInfo);
                FileManager.getFileManager().setAnyFileSize(FileManager.getFileManager().getAnyFileSize() - size);
                fileSize=FileManager.getFileManager().getAnyFileSize();
                switch (id) {
                    case R.id.filemanager_txtfile_rl:
                        FileManager.getFileManager().getTxtFileList().remove(fileInfo);
                        FileManager.getFileManager().setTxtFileSize(FileManager.getFileManager().getTxtFileSize() - size);
                        fileSize=FileManager.getFileManager().getTxtFileSize();
                        break;
                    case R.id.filemanager_videofile_rl:
                        FileManager.getFileManager().getVideoFileList().remove(fileInfo);
                        FileManager.getFileManager().setVideoFileSize(FileManager.getFileManager().getVideoFileSize() - size);
                        fileSize=FileManager.getFileManager().getVideoFileSize();
                        break;
                    case R.id.filemanager_audiofile_rl:
                        FileManager.getFileManager().getAudioFileList().remove(fileInfo);
                        FileManager.getFileManager().setAudioFileSize(FileManager.getFileManager().getAudioFileSize() - size);
                        fileSize=FileManager.getFileManager().getAudioFileSize();
                        break;
                    case R.id.filemanager_imagefile_rl:
                        FileManager.getFileManager().getImageFileList().remove(fileInfo);
                        FileManager.getFileManager().setImageFileSize(FileManager.getFileManager().getImageFileSize() - size);
                        fileSize=FileManager.getFileManager().getImageFileSize();
                        break;
                    case R.id.filemanager_apkfile_rl:
                        FileManager.getFileManager().getApkFileList().remove(fileInfo);
                        FileManager.getFileManager().setApkFileSize(FileManager.getFileManager().getApkFileSize() - size);
                        fileSize=FileManager.getFileManager().getApkFileSize();
                        break;
                    case R.id.filemanager_zipfile_rl:
                        FileManager.getFileManager().getZipFileList().remove(fileInfo);
                        FileManager.getFileManager().setZipFileSize(FileManager.getFileManager().getZipFileSize() - size);
                        fileSize=FileManager.getFileManager().getZipFileSize();
                        break;
                }
            }
        }
        //更新列表
        fma.notifyDataSetChanged();
        //获取文件数量
        fileNumber = fma.getList().size();
        //显示
        filenum.setText(fileNumber + "个");

        filespace.setText(Tools.getFileSize(fileSize));
        System.gc();
        //放弃线程当前执行权
        Thread.yield();
    }
}
