package com.mystery.funclothes.Bean;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Vindicated-Rt
 * 2019/5/18 10:59 PM
 * 贝塞尔曲线视图类
 */
public class BezierView extends View {

    private PointF PointRedStart, PointRedControl, PointRedEnd;
    private PointF PointGrayStart, PointGrayControl, PointGrayEnd;

    public BezierView(Context context) {
        super(context);
    }

    public BezierView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        PointRedStart = new PointF(0, 0);
        PointRedControl = new PointF(w / 2, h / 2);
        PointRedEnd = new PointF(0, h);

        PointGrayStart = new PointF(w,0);
        PointGrayControl = new PointF(w / 2, h / 2);
        PointGrayEnd = new PointF(w,h);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 红色虚线画笔
        Paint paintRed = new Paint();
        paintRed.setAntiAlias(true);
        paintRed.setPathEffect(new DashPathEffect(new float[]{10, 10}, 0));
        paintRed.setStyle(Paint.Style.STROKE);
        paintRed.setColor(Color.RED);
        paintRed.setStrokeWidth(10);

        // 绘制 LIKE 二阶贝塞尔曲线
        Path pathLike = new Path();
        pathLike.moveTo(PointRedStart.x, PointRedStart.y);
        pathLike.quadTo(PointRedControl.x, PointRedControl.y, PointRedEnd.x, PointRedEnd.y);
        canvas.drawPath(pathLike, paintRed);

        // 灰色虚线画笔
        Paint paintGray = new Paint();
        paintGray.setAntiAlias(true);
        paintGray.setPathEffect(new DashPathEffect(new float[]{10, 10}, 0));
        paintGray.setStyle(Paint.Style.STROKE);
        paintGray.setColor(Color.GRAY);
        paintGray.setStrokeWidth(10);

        // 绘制 DISLIKE 二阶贝塞尔曲线
        Path pathDislike = new Path();
        pathDislike.moveTo(PointGrayStart.x, PointGrayStart.y);
        pathDislike.quadTo(PointGrayControl.x, PointGrayControl.y, PointGrayEnd.x, PointGrayEnd.y);
        canvas.drawPath(pathDislike, paintGray);
    }
}
