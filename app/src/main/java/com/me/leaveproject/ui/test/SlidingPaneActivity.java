package com.me.leaveproject.ui.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.me.leaveproject.R;
import com.me.leaveproject.adapter.BaseAdapter;
import com.me.leaveproject.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class SlidingPaneActivity extends BaseActivity implements SlidingPaneLayout.PanelSlideListener{

	private RecyclerView recyclerView;
	private BaseAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sliding_pane);
		init();
	}

	private void init() {
		Toolbar toolbar = findViewById(R.id.toolbar);
		setToolbar(toolbar);
		SlidingPaneLayout slidingPaneLayout = findViewById(R.id.slidingPanel);
		slidingPaneLayout.setPanelSlideListener(this);
		recyclerView = findViewById(R.id.recyclerView);
		mAdapter = new BaseAdapter();
		initView();
		initData();

	}

	private void initData() {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			list.add("内容");
		}
		mAdapter.setList(list);
		mAdapter.notifyDataSetChanged();
	}

	private void initView() {
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(mAdapter);
		recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
	}

	@Override
	public void onPanelSlide(@NonNull View view, float v) {
		System.out.println("float v=> "+v);
	}

	@Override
	public void onPanelOpened(@NonNull View view) {

	}

	@Override
	public void onPanelClosed(@NonNull View view) {

	}
}
