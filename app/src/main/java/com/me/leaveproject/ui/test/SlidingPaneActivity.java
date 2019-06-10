package com.me.leaveproject.ui.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.me.leaveproject.R;
import com.me.leaveproject.base.BaseActivity;

public class SlidingPaneActivity extends BaseActivity implements SlidingPaneLayout.PanelSlideListener{


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
