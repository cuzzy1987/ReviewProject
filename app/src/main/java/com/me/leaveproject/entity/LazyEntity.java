package com.me.leaveproject.entity;

/**
 * Created by cs on 2019/5/30.
 */
public class LazyEntity {

	private static LazyEntity instance = null;

	public static synchronized LazyEntity getInstance(){
		instance = instance == null? new LazyEntity():instance;
		return instance;
	}

}
