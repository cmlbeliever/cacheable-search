package com.github.cmlbeliever.cacheablesearch.argumentresolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 缓存参数处理接口
 * 
 * @author cml
 *
 */
public interface CacheableArgumentResolver {
	/**
	 * 判断是否支持此类型的参数处理
	 * 
	 * @param webRequest
	 *            request
	 * @param parameter
	 *            参数
	 * @return true 支持，false 不支持
	 */
	boolean support(NativeWebRequest webRequest, MethodParameter parameter);

	/**
	 * 从请求中获取请求参数
	 * 
	 * @param parameter
	 * @param mavContainer
	 * @param webRequest
	 * @param binderFactory
	 * @return 获取的对象
	 * @throws Exception
	 */
	Object resolveRequestArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception;

	/**
	 * 获取请求参数中的cacheToken
	 * 
	 * @param webRequest
	 * @param cacheTokenKey
	 * @param argumentValue
	 * @return cacheToken
	 */
	String getCacheToken(NativeWebRequest webRequest, String cacheTokenKey);
}
