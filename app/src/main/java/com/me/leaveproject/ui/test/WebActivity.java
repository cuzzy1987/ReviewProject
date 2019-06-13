package com.me.leaveproject.ui.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.ValueCallback;
import android.webkit.WebView;

import com.me.leaveproject.R;

public class WebActivity extends AppCompatActivity {


	private WebView mWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web);

		init();
	}

	private void init() {
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.evaluateJavascript("", new ValueCallback<String>() {
			@Override
			public void onReceiveValue(String value) {

			}
		});
	}
}
