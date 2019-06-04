package com.me.leaveproject.db;

/**
 * Created by cs on 2019/6/4.
 */
public class SQLContract {

	private SQLContract(){

	}

	public static class User{
		public static final String TABLE_NAME = "user";
		public static final String COLUMN_ID = "id";
		public static final String COLUMN_NAME = "name";
	}
}
