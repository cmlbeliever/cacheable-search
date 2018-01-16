package com.cml.learn.cacheablesearch.argumentresolver;

import java.util.List;

import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import com.cml.learn.cacheablesearch.annotation.SearchCache;
import com.cml.learn.cacheablesearch.annotation.SearchCache.RequestType;

public class CacheableSearchRequestBodyParamResolver extends RequestResponseBodyMethodProcessor {

	public CacheableSearchRequestBodyParamResolver(List<HttpMessageConverter<?>> converters) {
		super(converters);
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		SearchCache searchCache = parameter.getParameterAnnotation(SearchCache.class);
		if (null != searchCache) {
			return searchCache.requestType() == RequestType.RequestBody;
		}
		return false;
	}

	@Override
	public boolean supportsReturnType(MethodParameter returnType) {
		return false;
	}

}
