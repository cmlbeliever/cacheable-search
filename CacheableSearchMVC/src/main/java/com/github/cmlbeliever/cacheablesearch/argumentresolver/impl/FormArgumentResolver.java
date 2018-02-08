package com.github.cmlbeliever.cacheablesearch.argumentresolver.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.github.cmlbeliever.cacheablesearch.argumentresolver.CacheableArgumentResolver;

/**
 * 支持 application/x-www-form-urlencoded 的数据提交
 * 
 * @author cml
 *
 */
public class FormArgumentResolver implements CacheableArgumentResolver {

	private HandlerMethodArgumentResolver defaultResolver;

	public FormArgumentResolver(HandlerMethodArgumentResolver defaultResolver) {
		super();
		this.defaultResolver = defaultResolver;
	}

	@Override
	public boolean support(NativeWebRequest webRequest, MethodParameter parameter) {
		HttpServletRequest req = (HttpServletRequest) webRequest.getNativeRequest();
		String mediaType = req.getHeader("Content-Type");
		return (StringUtils.isEmpty(mediaType) || MediaType.APPLICATION_FORM_URLENCODED_VALUE.equals(mediaType))
				&& defaultResolver.supportsParameter(parameter);
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
