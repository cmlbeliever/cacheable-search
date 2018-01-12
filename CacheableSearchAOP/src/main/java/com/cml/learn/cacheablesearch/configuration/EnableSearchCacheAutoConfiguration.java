package com.cml.learn.cacheablesearch.configuration;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cml.learn.cacheablesearch.cache.SessionSearchCache;
import com.cml.learn.cacheablesearch.cache.listener.SessionCacheListener;
import com.cml.learn.cacheablesearch.key.UUIDKeyGenerator;

@Configuration
public class EnableSearchCacheAutoConfiguration {

	@Bean
	public UUIDKeyGenerator uuidKeyGenerate() {
		return new UUIDKeyGenerator();
	}

	/**
	 * 注册session开关监听
	 * 
	 * @param cache
	 * @return
	 */
	@Bean
	public ServletListenerRegistrationBean<SessionCacheListener> sessionListener(SessionSearchCache cache) {
		ServletListenerRegistrationBean<SessionCacheListener> listenerRegistration = new ServletListenerRegistrationBean<>();
		listenerRegistration.setListener(new SessionCacheListener(cache));
		listenerRegistration.setEnabled(true);
		return listenerRegistration;
	}

	@Bean
	public SearchCacheAspect searchCacheAspect() {
		return new SearchCacheAspect();
	}

	@Bean
	public SessionSearchCache sessionSearchCache() {
		return new SessionSearchCache();
	}

}
