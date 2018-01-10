package com.cml.learn.cacheablesearch.sample.controller;

import java.io.Serializable;

public class UserListQueryParam extends CacheableParam implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private Integer age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
}
