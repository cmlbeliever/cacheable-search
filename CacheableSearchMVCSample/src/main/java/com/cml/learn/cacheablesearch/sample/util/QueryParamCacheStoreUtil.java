package com.cml.learn.cacheablesearch.sample.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.cml.learn.cacheablesearch.sample.model.CacheableParam;

public class QueryParamCacheStoreUtil {

	public static <T> T retrieveCacheIfNeed(HttpServletRequest req, String key, T param) {
		if (param instanceof CacheableParam) {
			CacheableParam cacheableParam = (CacheableParam) param;
			if (cacheableParam.isUseCache()) {
				// 从缓存中读取数据
				return QueryParamCacheStoreUtil.getCache(req, key);
			}
			// 将数据放入缓存中
			QueryParamCacheStoreUtil.putCache(req, key, param);
		}
		return param;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getCache(HttpServletRequest req, String key) {
		return (T) getSession(req).getAttribute(key);
	}

	public static void putCache(HttpServletRequest req, String key, Object param) {
		getSession(req).setAttribute(key, param);
	}

	public static HttpSession getSession(HttpServletRequest req) {
		return req.getSession();
	}
}
