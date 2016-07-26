package com.example.lz.text.AppManager.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.lz.text.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lz on 2016/5/31.
 */
public class CakeView extends View {
    Paint paint;
    private RectF rectF;
    private float phonememorysize;
    private float sdmemorysize;
    private float phonespace;
    private float sdspace;
    private int phonecolor;
    private int sdcolor;
    private int basecolor;
//    Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case 0:
//                    postInvalidate();//刷新
//                    break;
//            }
//        }
//    };

    public CakeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();//实例化画笔
        phonecolor = context.getResources().getColor(R.color.colorPrimary);//手机总空间颜色
        sdcolor = context.getResources().getColor(R.color.green);//手机SD卡颜色
        basecolor = context.getResources().getColor(R.color.orange);//底色
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int viewWidth = MeasureSpec.getSize(widthMeasureSpec);
        int viewHeight = MeasureSpec.getSize(heightMeasureSpec);
        rectF = new RectF(0, 0, viewWidth, viewHeight);//饼形图
        setMeasuredDimension(viewWidth, viewHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAntiAlias(true);//设置防锯齿
        paint.setColor(basecolor);
        canvas.drawArc(rectF, -90, 360, true, paint);
        // 整个手机机，手机空间所占
        paint.setColor(phonecolor);
        canvas.drawArc(rectF, -90, phonespace, true, paint);
        // 整个手机机，ＳＤ空间所占
        paint.setColor(sdcolor);
        canvas.drawArc(rectF, -90 + phonespace, sdspace, true, paint);
    }

    //设置绘制轨迹。
    public void setDraw(float f1, float f2) {
        //这里通过线程加载实现动画效果？
        phonememorysize = f1;
        sdmemorysize = f2;
        //获得目标角度
        // 目标角度
        final float phonefinaly = 360 * phonememorysize;
        final float sdfinaly = 360 * sdmemorysize;
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(50);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                while ((phonespace != phonefinaly || sdspace == sdfinaly)) {
//                    phonespace += 4;
//                    sdspace += 4;//每次4度提升
//
//                    if (phonespace >= phonefinaly) {
//                        phonespace = phonefinaly;
//                    }
//                    if (sdspace >= sdfinaly) {
//                        sdspace = sdfinaly;
//                    }
//                    handler.sendEmptyMessage(0);//发送申请修改
//                }
//
//            }
//        }).start();
        final Timer timer = new Timer();
        final TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                phonespace += 4;
                sdspace += 4;
                postInvalidate();
                if (phonespace >= phonefinaly) {
                    phonespace = phonefinaly;
                }
                if (sdspace >= sdfinaly) {
                    sdspace = sdfinaly;
                }
                if (phonespace == phonefinaly && sdspace == sdfinaly) {
                    timer.cancel();
                }
            }
        };
        timer.schedule(timerTask, 26, 26);//定义循环执行 每次
    }
    }

