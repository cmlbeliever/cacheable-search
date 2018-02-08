package com.cml.learn.cacheablesearch.sample.model;

public class User {
	private int age;
	private String name;
	private String cacheToken;

	public String getCacheToken() {
		return cacheToken;
	}

	public void setCacheToken(String cacheToken) {
		this.cacheToken = cacheToken;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [age=" + age + ", name=" + name + "]";
	}

}
