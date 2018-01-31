package com.github.cmlbeliever.cacheablesearch.cache;

public interface ISearchCache {
	/**
	 * 添加如缓存
	 * 
	 * @param key
	 * @param value
	 */
	void put(String key, Object value);

	Object get(String key);

	/**
	 * 清除缓存
	 */
	void clear(String key);

}
