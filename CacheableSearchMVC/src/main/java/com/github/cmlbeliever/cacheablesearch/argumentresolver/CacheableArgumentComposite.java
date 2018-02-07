package com.github.cmlbeliever.cacheablesearch.argumentresolver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class CacheableArgumentComposite {
	private List<HandlerMethodArgumentResolver> argumentResolvers = new ArrayList<>();

	public CacheableArgumentComposite addArgumentResolver(HandlerMethodArgumentResolver resolver) {
		argumentResolvers.add(resolver);
		return this;
	}

	/**
	 * 参数处理
	 * 
	 * @param parameter
	 * @param mavContainer
	 * @param webRequest
	 * @param binderFactory
	 * @return
	 * @throws Exception
	 */
	public Object resolveRequestArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		for (HandlerMethodArgumentResolver resolver : argumentResolvers) {
			if (resolver.supportsParameter(parameter)) {
				return resolver.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
			}
		}
		return null;
	}

}
