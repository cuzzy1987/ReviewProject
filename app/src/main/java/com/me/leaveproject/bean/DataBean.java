package com.me.leaveproject.bean;

import java.io.Serializable;
import java.util.Observable;

/**
 * Created by cs on 2019/5/28.
 */
public class DataBean extends Observable implements Serializable {

	public DataBean(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int id;
	public String name;



}
