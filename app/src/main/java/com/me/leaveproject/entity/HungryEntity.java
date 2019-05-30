package com.me.leaveproject.entity;

/**
 * Created by cs on 2019/5/30.
 */
public class HungryEntity {

	private static HungryEntity instance = new HungryEntity();

	public static HungryEntity getInstance (){
		return instance;
	}

}
