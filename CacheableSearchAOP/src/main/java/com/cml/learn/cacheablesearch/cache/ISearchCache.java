package com.cml.learn.cacheablesearch.cache;

public interface ISearchCache {
	/**
	 * 添加如缓存
	 * 
	 * @param key
	 * @param value
	 */
	void put(String key, Object value);

	Object get(String key);

	void clear(String key);
}
