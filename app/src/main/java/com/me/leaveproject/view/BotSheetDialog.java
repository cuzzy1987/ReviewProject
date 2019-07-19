package com.me.leaveproject.view;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.me.leaveproject.R;


public class BotSheetDialog extends BottomSheetDialog {

    private View.OnClickListener faceVerClickListener,
            fingerPrinterClickListener,gestureClickListener,
            cancelClickListener
                    ;

    public BotSheetDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bot_sheet_dialog);
        init();

    }

    private void init() {
        Window window = getWindow();
        if (window!=null){
            /* 取消默认背景设置为透明 | 取消默认左右边距 */
            window.findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        }
        initView();
    }

    private void initView() {
        /* 黄色警告是因为fvb可能存在找不到控件的可能性 可忽略*/
        /* void setOnClickListener(@Nullable OnClickListener l)  参数可空  */
        findViewById(R.id.faceTv).setOnClickListener(faceVerClickListener);
        findViewById(R.id.fingerTv).setOnClickListener(fingerPrinterClickListener);
        findViewById(R.id.gestureTv).setOnClickListener(gestureClickListener);
        findViewById(R.id.cancelTv).setOnClickListener(cancelClickListener);
    }

    public BotSheetDialog setOnFaceVerClickListerer(View.OnClickListener clickListener){
        this.faceVerClickListener = clickListener;
        return this;
    }

    public BotSheetDialog setOnFingerVerListener(View.OnClickListener clickListener){
        this.fingerPrinterClickListener = clickListener;
        return this;
    }

    public BotSheetDialog setOnGestureListener(View.OnClickListener clickListener){
        this.gestureClickListener = clickListener;
        return this;
    }

    public BotSheetDialog setOnBotCancelListener(View.OnClickListener clickListener){
        this.cancelClickListener = clickListener;
        return this;
    }


}
