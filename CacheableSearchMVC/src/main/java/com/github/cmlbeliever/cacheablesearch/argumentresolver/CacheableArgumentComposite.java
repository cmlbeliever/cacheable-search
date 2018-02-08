package com.github.cmlbeliever.cacheablesearch.argumentresolver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.github.cmlbeliever.cacheablesearch.argumentresolver.impl.ArgumentHandleHolder;

public class CacheableArgumentComposite {
	private List<CacheableArgumentResolver> argumentResolvers = new ArrayList<>();

	public CacheableArgumentComposite addArgumentResolver(CacheableArgumentResolver resolver) {
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
	public ArgumentHandleHolder resolveRequestArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory, String cacheTokenKey) throws Exception {
		ArgumentHandleHolder holder = new ArgumentHandleHolder();
		for (CacheableArgumentResolver resolver : argumentResolvers) {
			if (resolver.support(webRequest, parameter)) {
				Object argumentValue = resolver.resolveRequestArgument(parameter, mavContainer, webRequest, binderFactory);
				holder.setArgumentValue(argumentValue);
				holder.setCacheToken(resolver.getCacheToken(webRequest, cacheTokenKey, argumentValue));
				return holder;
			}
		}
		return null;
	}

}
