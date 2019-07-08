package com.me.leaveproject.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by cs on 2019/5/28.
 */
public class RationBarView extends View {

	private Canvas mCanvas;
	private Paint mPaint;
	private int index = 0;

	public RationBarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initPaint();
	}

	public RationBarView(Context context) {
		super(context);
	}

	private void initPaint() {
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setStrokeWidth(5);
		mPaint.setColor(Color.RED);
		mPaint.setTextSize(80);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		mCanvas = canvas;
		String text = "前";
		canvas.drawText(text,10,100,mPaint);
	}


	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		if (event.getAction()==MotionEvent.ACTION_UP) {
			index++;
			mCanvas.drawText("后"+index,10,100,mPaint);
			System.out.println("index==> "+index);
			postInvalidate();
		}
		return true;
	}
}
