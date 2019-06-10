package com.me.leaveproject.ui.test;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.me.leaveproject.R;
import com.me.leaveproject.base.BaseActivity;

public class FormActivity extends BaseActivity implements View.OnClickListener  {

	private EditText param1Et,param2Et,param3Et,param4Et;
	private Button submitBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_form);

		init();
		initData();
	}

	private void init() {
		Toolbar toolbar = findViewById(R.id.toolbar);
		setToolbar(toolbar);
		findViewById(R.id.submitBtn).setOnClickListener(this);
		param1Et = findViewById(R.id.param1Et);
		param2Et = findViewById(R.id.param2Et);
		param3Et = findViewById(R.id.param3Et);
		param4Et = findViewById(R.id.param4Et);
		submitBtn = findViewById(R.id.submitBtn);
	}

	private void initData() {

	}


	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.submitBtn:
				checkData();
				break;
		}
	}

	private void checkData() {
//		Observable.combineLatest(param1Et,param2Et,param3Et,param4Et,new Func)
	}
}
