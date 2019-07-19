package com.me.leaveproject.view;

import android.content.Context;
import android.graphics.Canvas;
import android.nfc.Tag;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

import java.util.logging.Logger;

public class CustomTestTextView extends AppCompatTextView {
    
    public static final String TAG = "CustomTestTextView";
    
    public CustomTestTextView(Context context) {
        super(context);
        init();
    }
    
    public CustomTestTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    
    
    private void init() {
        Log.d(TAG,"-=init=-");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, "onMeasure: ");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw: ");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d(TAG, "onLayout: ");
    }

    @Override
    public void requestLayout() {
        super.requestLayout();
        Log.d(TAG, "requestLayout: ");
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        Log.d(TAG, "dispatchDraw: ");
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
    }
}
