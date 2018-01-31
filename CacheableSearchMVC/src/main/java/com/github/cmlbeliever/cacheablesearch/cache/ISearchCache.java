package com.github.cmlbeliever.cacheablesearch.cache;

public interface ISearchCache {
	/**
	 * 添加如缓存
	 * 
	 * @param key 缓存key(not null)
	 * @param value 缓存值(not null)
	 */
	void put(String key, Object value);

	Object get(String key);

	/**
	 * 清除缓存
	 */
	void clear(String key);

}
