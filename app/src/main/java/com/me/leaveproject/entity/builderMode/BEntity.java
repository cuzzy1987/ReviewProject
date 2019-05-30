package com.me.leaveproject.entity.builderMode;

/**
 * Created by cs on 2019/5/30.
 */
public class BEntity {

	private int age;
	private String name;
	private boolean sex;

	public int getAge() {
		return age;
	}

	public String getName() {
		return name;
	}

	public boolean isSex() {
		return sex;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public static class Builder{
		private int age;
		private String name;
		private boolean sex;

		public Builder name(String name){
			this.name = name;
			return this;
		}

		public Builder age(int age){
			this.age = age;
			return this;
		}

		public Builder sex(boolean sex){
			this.sex = sex;
			return this;
		}

		public BEntity build(){
			return new BEntity(this);
		}
	}

	private BEntity(Builder builder){
		name = builder.name;
		age = builder.age;
		sex = builder.sex;
	}

}
