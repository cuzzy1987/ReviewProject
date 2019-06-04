package com.me.leaveproject.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by cs on 2019/6/4.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

	private static final String TAG ="DatabaseHelper";
	/* 数据库版本 */
	private static final int DATABASE_VERSION = 1;
	/* 数据库名称 */
	private static final String DATABASE_NAME = "user_db";
	/* 封装创建数据库表的SQL语句 */
	private static final String SQL_CREATE = "create table " + SQLContract.User.TABLE_NAME+" ( "+
			SQLContract.User.COLUMN_ID+" integer primary key, " +
			SQLContract.User.COLUMN_NAME +" text)";
	/* 封装删除表的语句 */
	/* 一般是用于升级数据库时 删除已有的表 防止无法更新数据库 */
	private static final String SQL_DELETE = "DROP TABLE IF EXISTS "+SQLContract.User.TABLE_NAME;

	/* 构造方法 */
	/*
	 * name 数据库名称
	 * param factory
	 * version 当前数据库版本 值为整形递增
	 * @param context 上下文
	 */
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/* 增删改查 操作 */

	@Override
	public void onCreate(SQLiteDatabase db) {
		//	创建数据库
		Log.d(TAG,"onCreate: 创建数据库 生成表");
		db.execSQL(SQL_CREATE);
	}

	/* 数据库版本升级的时候调用（即数据库版本发生变化的时候调用） */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		/* 删除存在的表 重新生成新表 */
		db.execSQL(SQL_DELETE);
		onCreate(db);
		Log.d(TAG,"onUpgrade: "+"更新数据库，当前版本号："+newVersion);
	}
}
