package com.cml.learn.cacheablesearch.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cml.learn.cacheablesearch.cache.SessionSearchCache;
import com.cml.learn.cacheablesearch.key.UUIDKeyGenerator;

@Configuration
public class EnableSearchCacheAutoConfiguration {

	@Bean
	public UUIDKeyGenerator uuidKeyGenerate() {
		return new UUIDKeyGenerator();
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
