package com.github.cmlbeliever.cacheablesearch.argumentresolver.impl;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.github.cmlbeliever.cacheablesearch.argumentresolver.CacheableArgumentResolver;

/**
 * 兼容1.0,1.1 中使用的使用HandlerArgumentResolver类型的参数
 * 
 * @author cml
 *
 */
public class ArgumentResolverWrapper implements CacheableArgumentResolver {

	private HandlerMethodArgumentResolver defaultResolver;

	public ArgumentResolverWrapper(HandlerMethodArgumentResolver defaultResolver) {
		super();
		this.defaultResolver = defaultResolver;
	}

	@Override
	public boolean support(NativeWebRequest webRequest, MethodParameter parameter) {
		return defaultResolver.supportsParameter(parameter);
	}

	@Override
	public Object resolveRequestArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		return defaultResolver.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
	}

	@Override
	public String getCacheToken(NativeWebRequest webRequest, String cacheTokenKey) {
		return webRequest.getParameter(cacheTokenKey);
	}

}
