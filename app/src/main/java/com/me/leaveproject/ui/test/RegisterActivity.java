package com.me.leaveproject.ui.test;

import android.animation.LayoutTransition;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.me.leaveproject.R;
import com.me.leaveproject.base.BaseActivity;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {


    private View contentView;
    private TextView centerTv,leftTv;
    private ImageView leftIv;
    private LinearLayout contentLl;
    private RelativeLayout topRl;
    private View registerView,resultView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        
        init();
    }

    private void init() {
        findViewById(R.id.leftRl).setOnClickListener(this);
        findViewById(R.id.rightLl).setOnClickListener(this);
        contentLl = findViewById(R.id.containerLl);
        centerTv = findViewById(R.id.centerTv);
        leftTv = findViewById(R.id.leftBtn);
        leftIv = findViewById(R.id.leftIv);
        leftIv.setVisibility(View.VISIBLE);
        topRl = findViewById(R.id.topRl);
        leftTv.setVisibility(View.GONE);
        centerTv.setText("手机号注册");
        initView();

    }

    private void initView() {
        registerView = getLayoutInflater().inflate(R.layout.register_via_phone_layout,null);
        resultView = getLayoutInflater().inflate(R.layout.layout_register_result,null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        contentLl.addView(registerView,params);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rightLl:
                showToast("-=right=-");
                if (resultView!=null && contentLl.getChildCount()!=0 && registerView!=null) {
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    topRl.setVisibility(View.GONE);
                    contentLl.removeView(registerView);
//                    contentLl.setLayoutAnimation(setAnimation())
                    resultView.setAnimation(setViewAnimation());
                    contentLl.addView(resultView,params);
                    registerView = null;

                }
                break;
            case R.id.leftRl:
                finish();
                break;
        }
    }

    /*
    几种动画的介绍
    透明度动画
    旋转动画
    位移动画
    缩放动画 多种动画集合
    动画监听
    @link https://www.jianshu.com/p/59c1e8df8735
    */

    private LayoutAnimationController setAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0f,1f);
        alphaAnimation.setDuration(200);
        LayoutAnimationController layoutAnimationController = new LayoutAnimationController(alphaAnimation,0.5f);
        layoutAnimationController.setOrder(LayoutAnimationController.ORDER_NORMAL);
        return layoutAnimationController;
    }

    private Animation setViewAnimation(){
        TranslateAnimation translateAnimation = new TranslateAnimation(0,200,0,200);
        translateAnimation.setDuration(10000);
        return translateAnimation;
    }



}
