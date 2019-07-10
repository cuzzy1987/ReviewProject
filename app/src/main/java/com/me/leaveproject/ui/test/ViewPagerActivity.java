package com.me.leaveproject.ui.test;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.me.leaveproject.R;
import com.me.leaveproject.adapter.ViewPagerAdapter;
import com.me.leaveproject.base.BaseActivity;
import com.me.leaveproject.callback.ViewPagerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends BaseActivity implements ViewPagerItemClickListener {


    private Toolbar mToolBar;
    private ViewPager mViewPager;
    private TextView hintTv;
    private ViewPagerAdapter mAdapter;

    public static final String TAG = "ViewPagerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        initView();
    }

    private void initView() {
        mToolBar = findViewById(R.id.toolbar);
        mViewPager = findViewById(R.id.viewPager);
        hintTv = findViewById(R.id.hintTv);
        hintTv.setText("位置=> 1");
        setToolbar(mToolBar);
        List<View> viewList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            View view = getLayoutInflater().inflate(R.layout.item_pager_layout,null);
            viewList.add(view);
        }
        mAdapter = new ViewPagerAdapter(viewList,this);
        mViewPager.setAdapter(mAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float v, int i1) {
                Log.d(TAG, "onPageScrolled: position => "+position +" v=> "+v+" i1=> "+i1);
            }

            @Override
            public void onPageSelected(int position) {
                hintTv.setText("位置=> "+(position+1));
            }

            @Override
            public void onPageScrollStateChanged(int position) {

            }
        });
    }


    @Override
    public void onItemClick(int position) {
        showToast("位置=> "+position);
    }
}
