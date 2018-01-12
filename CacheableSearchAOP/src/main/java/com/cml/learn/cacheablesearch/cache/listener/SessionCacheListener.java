package com.cml.learn.cacheablesearch.cache.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.cml.learn.cacheablesearch.cache.SessionSearchCache;

public class SessionCacheListener implements HttpSessionListener {

	private SessionSearchCache sessionCache;

	public SessionCacheListener(SessionSearchCache sessionCache) {
		super();
		this.sessionCache = sessionCache;
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		sessionCache.clear(se.getSession().getId());
	}

}
