package com.cml.learn.cacheablesearch.sample.model;

public class User {
	private int age;
	// ....其他属性

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [age=" + age + "]";
	}

}
