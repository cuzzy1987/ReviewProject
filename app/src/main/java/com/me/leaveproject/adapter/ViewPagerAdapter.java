package com.me.leaveproject.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.me.leaveproject.callback.ViewPagerItemClickListener;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {


    private List<View> mViewList;
    private ViewPagerItemClickListener mClickListener;

    public ViewPagerAdapter(List<View> mViewList, ViewPagerItemClickListener mClickListener) {
        this.mViewList = mViewList;
        this.mClickListener = mClickListener;
    }

    @Override
    public int getCount() {
        return mViewList==null?0:mViewList.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        container.addView(mViewList.get(position));
        mViewList.get(position).setOnClickListener(v -> {
            if (mClickListener!=null) mClickListener.onItemClick(position);
        });
        return mViewList.get(position);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
