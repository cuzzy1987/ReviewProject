package com.me.leaveproject.ui.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.me.leaveproject.R;

public class SingleTopActivity extends AppCompatActivity implements View.OnClickListener {

	private Button launchBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_top);

		init();
	}

	private void init() {
		findViewById(R.id.launchBtn).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.launchBtn:
				System.out.println("i'm singleTop mode Activity so no use there");
				startActivity(new Intent(this,SingleTopActivity.class));
				break;
			default:
				break;
		}
	}
}
