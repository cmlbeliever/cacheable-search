package com.cml.learn.cacheablesearch.configuration;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.cml.learn.cacheablesearch.argumentresolver.CacheableSearchParamResolver;
import com.cml.learn.cacheablesearch.cache.SessionSearchCache;
import com.cml.learn.cacheablesearch.key.UUIDKeyGenerator;

@Configuration
public class EnableSearchCacheAutoConfiguration extends WebMvcConfigurerAdapter {

	public static final String DEFAULT_KEY_GENERATE_REF = "defaultUUIDKeyGenerator";
	public static final String DEFAULT_SEARCH_CACHE_REF = "defaultISearchCache";

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(argumentResolver());
		super.addArgumentResolvers(argumentResolvers);
	}

	@Bean
	public CacheableSearchParamResolver argumentResolver() {
		return new CacheableSearchParamResolver();
	}

	@Bean(name = DEFAULT_KEY_GENERATE_REF)
	public UUIDKeyGenerator uuidKeyGenerate() {
		return new UUIDKeyGenerator();
	}

	@Bean(name = DEFAULT_SEARCH_CACHE_REF)
	public SessionSearchCache sessionSearchCache() {
		return new SessionSearchCache();
	}

	@Component
	@ConfigurationProperties(prefix = "search-cache")
	public static class SearchCacheProperties {
		private String cacheImplRef;
		private String keyGeneratorRef;
		private String cacheToken;
		private String argumentResolvers;

		public String getArgumentResolvers() {
			return argumentResolvers;
		}

		public void setArgumentResolvers(String argumentResolvers) {
			this.argumentResolvers = argumentResolvers;
		}

		public String getCacheImplRef() {
			return cacheImplRef;
		}

		public void setCacheImplRef(String cacheImplRef) {
			this.cacheImplRef = cacheImplRef;
		}

		public String getKeyGeneratorRef() {
			return keyGeneratorRef;
		}

		public void setKeyGeneratorRef(String keyGeneratorRef) {
			this.keyGeneratorRef = keyGeneratorRef;
		}

		public String getCacheToken() {
			return cacheToken;
		}

		public void setCacheToken(String cacheToken) {
			this.cacheToken = cacheToken;
		}

	}
}
