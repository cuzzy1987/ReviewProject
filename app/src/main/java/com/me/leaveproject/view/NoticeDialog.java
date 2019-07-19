package com.me.leaveproject.view;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.me.leaveproject.R;

public class NoticeDialog extends AlertDialog {


    private ViewGroup.OnClickListener onClickListener;
    private String content;

    public NoticeDialog(Context context) {
        super(context);
    }

    protected NoticeDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_notice_dialog);
        Window window = this.getWindow();
        if (window!=null)window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        init();
    }

    private void init() {
        findViewById(R.id.cancelTv).setOnClickListener(onClickListener);
        TextView contentTv = findViewById(R.id.contentTv);
        if (!TextUtils.isEmpty(content)) contentTv.setText(content);
    }

    public NoticeDialog setOnCancelClickListener(ViewGroup.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
        return this;
    }

    public NoticeDialog setContentText(String content){
        this.content = content;
        return this;
    }
}
