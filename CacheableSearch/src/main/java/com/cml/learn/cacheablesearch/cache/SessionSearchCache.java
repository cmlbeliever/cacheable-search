package com.cml.learn.cacheablesearch.cache;

import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.ehcache.Cache;
import org.ehcache.PersistentCacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
public class SessionSearchCache implements ISearchCache, InitializingBean, DisposableBean {

	private static final String CACHE_NAME = "sessionSearchCache";
	private PersistentCacheManager persistentCacheManager;
	@SuppressWarnings("rawtypes")
	private Cache<String, ConcurrentHashMap> sessionCache;

	private String getSessionId() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getSession().getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void put(String key, Object value) {
		String sessionId = getSessionId();
		ConcurrentHashMap<String, Object> cacheValue = sessionCache.get(sessionId);
		if (null == cacheValue) {
			synchronized (sessionId) {
				cacheValue = sessionCache.get(sessionId);
				if (null == cacheValue) {
					cacheValue = new ConcurrentHashMap<String, Object>();
				}
			}
		}
		cacheValue.put(key, value);
		sessionCache.put(sessionId, cacheValue);
	}

	@Override
	public Object get(String key) {
		String sessionId = getSessionId();
		@SuppressWarnings("unchecked")
		ConcurrentHashMap<String, Object> cacheValue = sessionCache.get(sessionId);
		if (null != cacheValue) {
			return cacheValue.get(key);
		}
		return null;
	}

	protected void remove(String key) {
		sessionCache.remove(key);
	}

	/**
	 * session创建和关闭事件监听，在session关闭后清除对应的缓存
	 * 
	 * @author cml
	 *
	 */
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

	@Override
	public void afterPropertiesSet() throws Exception {

		persistentCacheManager = CacheManagerBuilder.newCacheManagerBuilder().with(CacheManagerBuilder.persistence("/cache"))
				.withCache(CACHE_NAME,
						CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, ConcurrentHashMap.class, ResourcePoolsBuilder
								.newResourcePoolsBuilder().heap(1000, EntryUnit.ENTRIES).offheap(10, MemoryUnit.MB).disk(1024, MemoryUnit.MB, true)))
				.build(true);

		sessionCache = persistentCacheManager.getCache(CACHE_NAME, String.class, ConcurrentHashMap.class);
	}

	@Override
	public void destroy() throws Exception {
		persistentCacheManager.close();
	}

}
