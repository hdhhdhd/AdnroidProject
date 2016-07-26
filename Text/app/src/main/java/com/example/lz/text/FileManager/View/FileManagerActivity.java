package com.example.lz.text.FileManager.View;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lz.text.BaseActivity;
import com.example.lz.text.FileManager.Tool.Filetypechange;
import com.example.lz.text.FileManager.Tool.FileManager;
import com.example.lz.text.R;
import com.example.lz.text.Tools;

/**
 * Created by lz on 2016/6/2.
 */
public class FileManagerActivity extends BaseActivity implements View.OnClickListener {
    TextView titlesize, allsize, txtsize, videosize, audiosize, imgsize, zipsize, apksize;
    ProgressBar titlepb, allpb, txtpb, videopb, audiopb, imgpb, zippb, apkpb;
    RelativeLayout allrl,txtrl,videorl,audiorl,imgrl,ziprl,apkrl;
    View view;
    FileManager fileManager;
    Thread thread;

    @Override
    public void getView() {
        setContentView(R.layout.filemanager);
        titlesize = (TextView) findViewById(R.id.filemanager_size_tv);
        allsize = (TextView) findViewById(R.id.filemanager_allfilesize_tv);
        txtsize = (TextView) findViewById(R.id.filemanager_txtfilesize_tv);
        videosize = (TextView) findViewById(R.id.filemanager_videofilesize_tv);
        audiosize = (TextView) findViewById(R.id.filemanager_audiofilesize_tv);
        imgsize = (TextView) findViewById(R.id.filemanager_imagefilesize_tv);
        zipsize = (TextView) findViewById(R.id.filemanager_zipfilesize_tv);
        apksize = (TextView) findViewById(R.id.filemanager_apkfilesize_tv);
        allrl = (RelativeLayout) findViewById(R.id.filemanager_allfile_rl);
        txtrl = (RelativeLayout) findViewById(R.id.filemanager_txtfile_rl);
        videorl = (RelativeLayout) findViewById(R.id.filemanager_videofile_rl);
        audiorl = (RelativeLayout) findViewById(R.id.filemanager_audiofile_rl);
        imgrl = (RelativeLayout) findViewById(R.id.filemanager_imagefile_rl);
        ziprl = (RelativeLayout) findViewById(R.id.filemanager_zipfile_rl);
        apkrl = (RelativeLayout) findViewById(R.id.filemanager_apkfile_rl);
        titlepb = (ProgressBar) findViewById(R.id.filemanager_titleloading_pb);
        allpb = (ProgressBar) findViewById(R.id.filemanager_allloading_pb);
        txtpb = (ProgressBar) findViewById(R.id.filemanager_txtloading_pb);
        videopb = (ProgressBar) findViewById(R.id.filemanager_videoloading_pb);
        audiopb = (ProgressBar) findViewById(R.id.filemanager_audio_loading_pb);
        imgpb = (ProgressBar) findViewById(R.id.filemanager_imageloading_pb);
        zippb = (ProgressBar) findViewById(R.id.filemanager_ziploading_pb);
        apkpb = (ProgressBar) findViewById(R.id.filemanager_apk_loading_pb);
        view = findViewById(R.id.filemanager_title);
    }

    @Override
    public void setView() {
        Tools.setTitle(view, "文件管理", this);
        titlesize.setVisibility(View.GONE);
        titlepb.setVisibility(View.VISIBLE);
        allpb.setVisibility(View.VISIBLE);
        allsize.setVisibility(View.GONE);
        txtpb.setVisibility(View.VISIBLE);
        titlesize.setVisibility(View.VISIBLE);
        videopb.setVisibility(View.VISIBLE);
        videosize.setVisibility(View.GONE);
        audiopb.setVisibility(View.VISIBLE);
        audiosize.setVisibility(View.GONE);
        imgpb.setVisibility(View.VISIBLE);
        imgsize.setVisibility(View.GONE);
        zippb.setVisibility(View.VISIBLE);
        zipsize.setVisibility(View.GONE);
        apkpb.setVisibility(View.VISIBLE);
        loadData();
    }

    void loadData() {
        fileManager = FileManager.getFileManager();
        fileManager.setSearchFileListener(searchFileListener);
        //启动线程进行文件搜索
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                fileManager.searchSDCardFile();
            }
        });
        thread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fileManager.setStopSearch(true);
        //终端线程释放资源
        thread.interrupt();
        thread = null;
    }


        private Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        allsize.setText(Tools.getFileSize(fileManager.getAnyFileSize()));
                        titlesize.setText(Tools.getFileSize(fileManager.getAnyFileSize()));
                        String typeName = (String) msg.obj;
                        if (typeName.equals(Filetypechange.TYPE_APK)) {
                            apksize.setText(Tools.getFileSize(fileManager.getApkFileSize()));
                        } else if (typeName.equals(Filetypechange.TYPE_AUDIO)) {
                            videosize.setText(Tools.getFileSize(fileManager.getAudioFileSize()));
                        } else if (typeName.equals(Filetypechange.TYPE_IMAGE)) {
                            imgsize.setText(Tools.getFileSize(fileManager.getImageFileSize()));
                        } else if (typeName.equals(Filetypechange.TYPE_TXT)) {
                            txtsize.setText(Tools.getFileSize(fileManager.getTxtFileSize()));
                        } else if (typeName.equals(Filetypechange.TYPE_VIDEO)) {
                            audiosize.setText(Tools.getFileSize(fileManager.getVideoFileSize()));
                        } else if (typeName.equals(Filetypechange.TYPE_ZIP)) {
                            zipsize.setText(Tools.getFileSize(fileManager.getZipFileSize()));
                        }
                        break;
                    //检测结束的更新
                    case 2:
                        //释放点击
                        allsize.setText(Tools.getFileSize(fileManager.getAnyFileSize()));
                        titlesize.setText(Tools.getFileSize(fileManager.getAnyFileSize()));
                        apksize.setText(Tools.getFileSize(fileManager.getApkFileSize()));
                        audiosize.setText(Tools.getFileSize(fileManager.getAudioFileSize()));
                        imgsize.setText(Tools.getFileSize(fileManager.getImageFileSize()));
                        txtsize.setText(Tools.getFileSize(fileManager.getTxtFileSize()));
                        videosize.setText(Tools.getFileSize(fileManager.getVideoFileSize()));
                        zipsize.setText(Tools.getFileSize(fileManager.getZipFileSize()));
                        titlesize.setVisibility(View.VISIBLE);
                        titlepb.setVisibility(View.GONE);
                        allsize.setVisibility(View.VISIBLE);
                        allpb.setVisibility(View.GONE);
                        txtsize.setVisibility(View.VISIBLE);
                        txtpb.setVisibility(View.GONE);
                        videosize.setVisibility(View.VISIBLE);
                        videopb.setVisibility(View.GONE);
                        audiopb.setVisibility(View.GONE);
                        audiosize.setVisibility(View.VISIBLE);
                        imgpb.setVisibility(View.GONE);
                        imgsize.setVisibility(View.VISIBLE);
                        zippb.setVisibility(View.GONE);
                        zipsize.setVisibility(View.VISIBLE);
                        apkpb.setVisibility(View.GONE);
                        apksize.setVisibility(View.VISIBLE);
                        allrl.setOnClickListener(FileManagerActivity.this);
                        txtrl.setOnClickListener(FileManagerActivity.this);
                        videorl.setOnClickListener(FileManagerActivity.this);
                        audiorl.setOnClickListener(FileManagerActivity.this);
                        imgrl.setOnClickListener(FileManagerActivity.this);
                        ziprl.setOnClickListener(FileManagerActivity.this);
                        apkrl.setOnClickListener(FileManagerActivity.this);
                        break;
                }
            }
        };
        private FileManager.SearchFileListener searchFileListener = new FileManager.SearchFileListener() {
            @Override
            public void searching(String typeName) {
                Message msg = handler.obtainMessage();
                msg.what = 1;
                msg.obj = typeName;
                handler.sendMessage(msg);
            }

            @Override
            public void end(boolean isExceptionEnd) {
                if(isExceptionEnd){
                handler.sendEmptyMessage(2);//结束
               }

            }
        };

            @Override
            public void onClick(View v) {
                int viewID = v.getId();
                switch (viewID) {
                    case R.id.filemanager_allfile_rl:

                    case R.id.filemanager_txtfile_rl:

                    case R.id.filemanager_videofile_rl:

                    case R.id.filemanager_audiofile_rl:

                    case R.id.filemanager_imagefile_rl:

                    case R.id.filemanager_zipfile_rl:

                    case R.id.filemanager_apkfile_rl:
                        Intent intent = new Intent(FileManagerActivity.this, SecFileManagerActivity.class);
                        intent.putExtra("id", viewID);
                        startActivityForResult(intent, 1);//回调监听改变
                        break;
                }
            }

            @Override
            public void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
                if (requestCode == 1) {
                    titlesize.setText(Tools.getFileSize(fileManager.getAnyFileSize()));
                    allsize.setText(Tools.getFileSize(fileManager.getAnyFileSize()));
                    apksize.setText(Tools.getFileSize(fileManager.getApkFileSize()));
                    audiosize.setText(Tools.getFileSize(fileManager.getAudioFileSize()));
                    imgsize.setText(Tools.getFileSize(fileManager.getImageFileSize()));
                    txtsize.setText(Tools.getFileSize(fileManager.getTxtFileSize()));
                    videosize.setText(Tools.getFileSize(fileManager.getVideoFileSize()));
                    zipsize.setText(Tools.getFileSize(fileManager.getZipFileSize()));
                }
            }
    }


