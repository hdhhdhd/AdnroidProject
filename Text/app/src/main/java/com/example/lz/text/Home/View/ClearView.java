package com.example.lz.text.Home.View;

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
 * Created by lz on 2016/6/1.
 */
public class ClearView extends View{
    private Paint paint=new Paint();
    private RectF rectF;
    private final int START_RAD=-90;
    private int paintrad=0;
    private int state =0;
    private int arcColor = 0xFFFF8C00;
    private boolean isRunning =false;

    public ClearView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAngle(360);
        arcColor = context.getResources().getColor(R.color.orange);

    }
    public void setAngle(final int angle){
      paintrad =angle;
        postInvalidate();
        isRunning =false;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int viewWidth = MeasureSpec.getSize(widthMeasureSpec);
        int viewHeight = MeasureSpec.getSize(heightMeasureSpec);
        rectF =new RectF(0,0,viewWidth,viewHeight);
        setMeasuredDimension(viewWidth,viewHeight);
    }
    /**设置角度，且带动画*/
    public void setAngleWithAnim(final int angle) {
        if (isRunning) {
            return;
        }
        isRunning = true;
        state = 0; // 回退
        final Timer timer = new Timer();
        final TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                switch (state) {
                    case 0:
//                        sweepAngle += back[backIndex++];
//                        if (backIndex >= back.length) {
//                            backIndex = back.length - 1;
//                        }
                        paintrad-=4;
                        postInvalidate();
                        if (paintrad <= 0) {
                            paintrad = 0;
                            state = 1;
//                            backIndex = 0;
                        }
                        break;
                    case 1:
//                        sweepAngle += goon[goonIndex++];
//                        if (goonIndex >= goon.length) {
//                            goonIndex = goon.length - 1;
//                        }
                        paintrad+=4;
                        postInvalidate();
                        if (paintrad >= angle) {
                            paintrad = angle;
                            timer.cancel();
//                            goonIndex = 0;
                            isRunning = false;
                        }
                        break;
                }
            }
        };
        timer.schedule(timerTask, 24, 24);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(arcColor);
        paint.setAntiAlias(true);
        canvas.drawArc(rectF, START_RAD, paintrad, true, paint); // 会旋转的圆形
    }
}
