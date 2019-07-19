package com.me.leaveproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.me.leaveproject.base.BaseActivity;
import com.me.leaveproject.utils.ScreenUtil;
import com.me.leaveproject.view.BotSheetDialog;
import com.me.leaveproject.view.NoticeDialog;

import org.w3c.dom.Text;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText phoneEt,pwEt;
    private NoticeDialog noticeDialog;
    private BotSheetDialog botSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ScreenUtil.setFullScreen(this);
        ScreenUtil.setTranslucent(this,true);
        setContentView(R.layout.activity_login);

        init();
    }

    private void init() {

        initView();
        initData();
    }

    private void initData() {
//        noticeDialog = new NoticeDialog(this);
    }

    private void initView() {
        findViewById(R.id.registerTv).setOnClickListener(this);
        findViewById(R.id.forgotTv).setOnClickListener(this);
        findViewById(R.id.loginBtn).setOnClickListener(this);
        findViewById(R.id.wxTv).setOnClickListener(this);
        findViewById(R.id.msgTv).setOnClickListener(this);
        findViewById(R.id.moreTv).setOnClickListener(this);
        findViewById(R.id.leftRl).setOnClickListener(this);
        phoneEt = findViewById(R.id.phoneEt);
        pwEt = findViewById(R.id.pwEt);
        findViewById(R.id.leftIv).setVisibility(View.GONE);
        findViewById(R.id.rightIv).setVisibility(View.GONE);
        TextView leftTv = findViewById(R.id.leftBtn),
                centerTv = findViewById(R.id.centerTv);
        leftTv.setVisibility(View.VISIBLE);
        leftTv.setText("取消");
        centerTv.setText("登录注册");


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registerTv:
                showToast("显示状态栏");
                ScreenUtil.cleanFullScreen(this);
                break;
            case R.id.forgotTv:
                break;
            case R.id.loginBtn:
                showDialog();
                break;
            case R.id.wxTv:
                break;
            case R.id.msgTv:
                break;
            case R.id.moreTv:
                showBotSheetDialog();
                break;
            case R.id.leftRl:
                finish();
                break;

        }
    }

    private void showBotSheetDialog() {
        if (botSheetDialog == null)botSheetDialog = new BotSheetDialog(this);
        botSheetDialog
                .setOnFaceVerClickListerer(v -> {
                    showToast("脸部识别");
                })
                .setOnFingerVerListener(v -> {
                    showToast("指纹识别");
                })
                .setOnGestureListener(v -> {
                    showToast("手势识别");
                })
                .setOnBotCancelListener(v -> {
                    botSheetDialog.dismiss();
                    showToast("取消");
                })
                .show();
    }

    private void showDialog() {
        if (noticeDialog==null)noticeDialog = new NoticeDialog(this);
        noticeDialog.setContentText("").setOnCancelClickListener(view->{
            noticeDialog.dismiss();
        }).show();
    }
}
