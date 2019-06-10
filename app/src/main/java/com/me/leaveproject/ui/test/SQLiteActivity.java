package com.me.leaveproject.ui.test;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.me.leaveproject.R;
import com.me.leaveproject.base.BaseActivity;
import com.me.leaveproject.db.DatabaseHelper;

public class SQLiteActivity extends BaseActivity implements View.OnClickListener {


	private DatabaseHelper databaseHelper;
	private SQLiteDatabase sqLiteDatabase;
	private static int VERSION = 1;

	private TextView showTv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sqlite);
		init();
	}

	private void init() {
		Toolbar toolbar = findViewById(R.id.toolbar);
		setToolbar(toolbar);
		initView();
	}

	/* 初始化控件 */
	private void initView() {
		findViewById(R.id.createBtn).setOnClickListener(this);
		findViewById(R.id.insertTv).setOnClickListener(this);
		findViewById(R.id.deleteBtn).setOnClickListener(this);
		findViewById(R.id.changeBtn).setOnClickListener(this);
		findViewById(R.id.checkBtn).setOnClickListener(this);
		showTv = findViewById(R.id.showTv);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.createBtn:
				createTable();
				break;
			case R.id.insertTv:
				insertData();
				break;
			case R.id.deleteBtn:
				deleteData();
				break;
			case R.id.changeBtn:
				changeData();
				break;
			case R.id.checkBtn:
				checkData();
				break;
		}
	}


	/* 查看数据 */
	private void checkData() {

	}

	/* 修改数据 */
	private void changeData() {

	}

	/* 删除数据 */
	private void deleteData() {

	}

	/* 插入数据 */
	private void insertData() {

	}

	/* 创建数据库表 */
	private void createTable() {
		/* 该方法会调用 Helper的初始哈方法*/
//		databaseHelper.getWritableDatabase();
	}
}
