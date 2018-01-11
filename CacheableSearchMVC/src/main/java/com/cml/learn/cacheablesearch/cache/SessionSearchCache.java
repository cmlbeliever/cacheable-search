package com.cml.learn.cacheablesearch.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
public class SessionSearchCache implements ISearchCache {

	private ConcurrentHashMap<String, Map<String, Object>> cacheContainer = new ConcurrentHashMap<>(125);

	private String getSessionId() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getSession().getId();
	}

	@Override
	public void put(String key, Object value) {
		String sessionId = getSessionId();
		Map<String, Object> cacheValue = cacheContainer.get(sessionId);
		if (null == cacheValue) {
			synchronized (sessionId) {
				cacheValue = cacheContainer.get(sessionId);
				if (null == cacheValue) {
					cacheValue = new ConcurrentHashMap<String, Object>();
					cacheContainer.put(sessionId, cacheValue);
				}
			}
		}
		cacheValue.put(key, value);
		cacheContainer.put(sessionId, cacheValue);
	}

	@Override
	public Object get(String key) {
		String sessionId = getSessionId();
		Map<String, Object> cacheValue = cacheContainer.get(sessionId);
		if (null != cacheValue) {
			return cacheValue.get(key);
		}
		return null;
	}

	protected void remove(String key) {
		cacheContainer.remove(key);
	}

	public static class SessionCacheListener implements HttpSessionListener {

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
			sessionCache.remove(se.getSession().getId());
		}

	}

}
