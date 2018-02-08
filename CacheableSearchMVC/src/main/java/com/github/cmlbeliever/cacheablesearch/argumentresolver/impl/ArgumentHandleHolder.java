package com.github.cmlbeliever.cacheablesearch.argumentresolver.impl;

public class ArgumentHandleHolder {
	private Object argumentValue;
	private String cacheToken;

	public Object getArgumentValue() {
		return argumentValue;
	}

	public void setArgumentValue(Object argumentValue) {
		this.argumentValue = argumentValue;
	}

	public String getCacheToken() {
		return cacheToken;
	}

	public void setCacheToken(String cacheToken) {
		this.cacheToken = cacheToken;
	}

}
