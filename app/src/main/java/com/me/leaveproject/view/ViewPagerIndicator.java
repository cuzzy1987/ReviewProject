package com.me.leaveproject.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.me.leaveproject.utils.Utils;

public class ViewPagerIndicator extends LinearLayout {

    private Paint mPaint;
    private Paint indexPaint;
    private int margin;
    private int indexNum = 5;
    private int centerX = 0;
    private int mTranslationX;
    private int horOffset;

    private int radius;
    private int centerMargin;
    private int indexWidth;
    private int width;
    private int maxTranslationX;



    public ViewPagerIndicator(Context context) {
        super(context);
        init(context);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();
        centerX = indexWidth / 2 +horOffset;
        for (int i = 0; i < indexNum; i++) {
            canvas.drawCircle(centerX,radius,radius,mPaint);
            centerX += centerMargin;
        }
        canvas.drawLine(mTranslationX,radius,mTranslationX + indexWidth,radius,indexPaint);
        canvas.restore();
        super.dispatchDraw(canvas);
    }

    public void scroll(int position, float offset){
        int translationX = (int)(centerMargin * (offset + position)) + horOffset;
        if (translationX > maxTranslationX){
            return;
        }else {
            mTranslationX = maxTranslationX;
        }
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(width,indexWidth);
    }

    /* 初始化 */
    private void init(Context context) {
        horOffset = Utils.dip2px(context,6);
        indexWidth = Utils.dip2px(context,8);
        radius = Utils.dip2px(context,3);
        margin = Utils.dip2px(context,8);
        width = 2 * horOffset + (indexNum - 1 ) * margin + indexNum * 2 * radius;
        centerMargin = 2 * radius + margin;
        maxTranslationX = centerMargin * 3 + horOffset;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#CFCFFB"));
        mPaint.setStyle(Paint.Style.FILL);
        indexPaint = new Paint();
        indexPaint.setAntiAlias(true);
        indexPaint.setColor(Color.parseColor("#5835FF"));
        indexPaint.setStyle(Paint.Style.FILL);
        indexPaint.setStrokeCap(Paint.Cap.ROUND);
        indexPaint.setStrokeWidth(radius*2);

    }
}