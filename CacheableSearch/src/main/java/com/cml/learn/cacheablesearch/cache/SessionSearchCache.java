package com.cml.learn.cacheablesearch.cache;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SessionSearchCache implements ISearchCache {

	HttpSession getSession() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getSession();
	}

	@Override
	public void put(String key, Object value) {
	}

	@Override
	public Object get(String key) {
		return null;
	}

}
