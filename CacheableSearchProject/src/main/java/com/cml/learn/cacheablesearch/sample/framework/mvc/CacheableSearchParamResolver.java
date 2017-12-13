package com.cml.learn.cacheablesearch.sample.framework.mvc;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.cml.learn.cacheablesearch.sample.model.User;

@Component
public class CacheableSearchParamResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		System.out.println("=====>supportsParameter====>!!!" + parameter.getParameterType().getName());
//		return parameter.getParameterType() == User.class;
		return false;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		System.out.println("resolveArgument==>");
		User user = new User();
		return user;
	}

}
