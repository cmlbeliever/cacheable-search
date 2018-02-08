package com.github.cmlbeliever.cacheablesearch.argumentresolver.impl;

import java.lang.reflect.Field;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.Conventions;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodProcessor;

import com.github.cmlbeliever.cacheablesearch.argumentresolver.CacheableArgumentResolver;

/**
 * application/json方式实现
 * 
 * @author cml
 *
 */
public class RequestBodyArgumentResolver extends AbstractMessageConverterMethodProcessor implements CacheableArgumentResolver {

	public RequestBodyArgumentResolver(List<HttpMessageConverter<?>> converters) {
		super(converters);
	}

	@Deprecated
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return false;
	}

	@Deprecated
	@Override
	public boolean supportsReturnType(MethodParameter returnType) {
		return false;
	}

	@Deprecated
	@Override
	public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest)
			throws Exception {

	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		parameter = parameter.nestedIfOptional();
		Object arg = readWithMessageConverters(webRequest, parameter, parameter.getNestedGenericParameterType());
		String name = Conventions.getVariableNameForParameter(parameter);

		WebDataBinder binder = binderFactory.createBinder(webRequest, arg, name);
		if (arg != null) {
			validateIfApplicable(binder, parameter);
			if (binder.getBindingResult().hasErrors() && isBindExceptionRequired(binder, parameter)) {
				throw new MethodArgumentNotValidException(parameter, binder.getBindingResult());
			}
		}
		mavContainer.addAttribute(BindingResult.MODEL_KEY_PREFIX + name, binder.getBindingResult());

		return adaptArgumentIfNecessary(arg, parameter);
	}

	@Override
	public boolean support(NativeWebRequest webRequest, MethodParameter parameter) {
		HttpServletRequest req = (HttpServletRequest) webRequest.getNativeRequest();
		String mediaType = req.getHeader("Content-Type");
		if (!StringUtils.isEmpty(mediaType)) {
			return MediaType.APPLICATION_JSON_VALUE.equals(mediaType) || MediaType.APPLICATION_JSON_UTF8_VALUE.equals(mediaType);
		}
		return false;
	}

	@Override
	public Object resolveRequestArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		return resolveArgument(parameter, mavContainer, webRequest, binderFactory);
	}

	@Override
	public String getCacheToken(NativeWebRequest webRequest, String cacheTokenKey, Object argumentValue) {
		Field cacheTokenProperty = ReflectionUtils.findField(argumentValue.getClass(), cacheTokenKey, String.class);
		if (null != cacheTokenProperty) {
			try {
				return (String) cacheTokenProperty.get(argumentValue);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
