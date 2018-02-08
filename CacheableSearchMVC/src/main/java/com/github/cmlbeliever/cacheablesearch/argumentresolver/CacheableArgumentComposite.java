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
				String cacheToken = resolver.getCacheToken(webRequest, cacheTokenKey);
				holder.setCacheToken(cacheToken);
				//有cacheToken表明使用的是缓存数据，不需要解析参数
				if (null != cacheToken) {
					return holder;
				}else {
					Object argumentValue = resolver.resolveRequestArgument(parameter, mavContainer, webRequest, binderFactory);
					holder.setArgumentValue(argumentValue);
				}
				return holder;
			}
		}
		return null;
	}

}
