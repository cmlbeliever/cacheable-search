package com.cml.learn.cacheablesearch.sample.controller;

public abstract class CacheableParam {
	private String cacheKey;
	private boolean useCache;

	public boolean isUseCache() {
		return useCache;
	}

	public void setUseCache(boolean useCache) {
		this.useCache = useCache;
	}

	public String getCacheKey() {
		return cacheKey;
	}

	public void setCacheKey(String cacheKey) {
		this.cacheKey = cacheKey;
	}
}
