package com.turman.fb.example.definedview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by dqf on 2016/4/6.
 */
public class MyView extends View {
    private Paint mPaint;

    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);          //抗锯齿
        mPaint.setColor(getResources().getColor(android.R.color.holo_orange_light));//画笔颜色
        mPaint.setStyle(Paint.Style.STROKE);  //画笔风格
        mPaint.setTextSize(36);             //绘制文字大小，单位px
        mPaint.setStrokeWidth(5);           //画笔粗细
    }

    //重写该方法，在这里绘图
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(getResources().getColor(android.R.color.holo_green_dark));
        canvas.drawCircle(200, 200, 100, mPaint);
        canvas.drawRect(320,320,460,460,mPaint);
        super.onDraw(canvas);
    }
}
