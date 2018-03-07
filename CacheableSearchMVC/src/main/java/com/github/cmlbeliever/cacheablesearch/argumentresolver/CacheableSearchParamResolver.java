package com.github.cmlbeliever.cacheablesearch.argumentresolver;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.RequestParamMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;

import com.github.cmlbeliever.cacheablesearch.annotation.SearchCache;
import com.github.cmlbeliever.cacheablesearch.argumentresolver.impl.ArgumentHandleHolder;
import com.github.cmlbeliever.cacheablesearch.argumentresolver.impl.ArgumentResolverWrapper;
import com.github.cmlbeliever.cacheablesearch.argumentresolver.impl.FormArgumentResolver;
import com.github.cmlbeliever.cacheablesearch.argumentresolver.impl.RequestBodyArgumentResolver;
import com.github.cmlbeliever.cacheablesearch.cache.ISearchCache;
import com.github.cmlbeliever.cacheablesearch.configuration.EnableSearchCacheAutoConfiguration;
import com.github.cmlbeliever.cacheablesearch.configuration.EnableSearchCacheAutoConfiguration.SearchCacheProperties;
import com.github.cmlbeliever.cacheablesearch.key.KeyGenerator;

public class CacheableSearchParamResolver implements HandlerMethodArgumentResolver, BeanFactoryAware, InitializingBean {

	private ISearchCache defaultSearchCacheManager;
	private KeyGenerator defaultKeyGenerator;
	private String defaultCacheToken = "cacheToken";

	@Autowired(required = false)
	private SearchCacheProperties defaultConfig;

	private CacheableArgumentComposite argumentResolverComposite = new CacheableArgumentComposite();

	private BeanFactory beanFactory;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(SearchCache.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {

		// 获取参数的注解配置
		SearchCache cacheConfig = retrieveSearchCache(parameter);
		// 缓存的参数字段
		String cacheTokenKey = getCacheTokenKey(cacheConfig);

		// 处理请求参数和请求key
		ArgumentHandleHolder valueHolder = argumentResolverComposite.resolveRequestArgument(parameter, mavContainer, webRequest, binderFactory,
				cacheTokenKey);

		if (null == valueHolder) {
			return null;
		}

		String cacheToken = valueHolder.getCacheToken();
		ISearchCache searchCacheResolver = getSearchCache(cacheConfig);

		// 有cacheToken，从缓存中获取数据
		if (!StringUtils.isEmpty(cacheToken)) {
			Object cacheValue = searchCacheResolver.get(cacheToken);
			// 有缓存，从缓存中获取数据
			if (null != cacheValue) {
				webRequest.setAttribute(cacheTokenKey, cacheToken, NativeWebRequest.SCOPE_REQUEST);
				return cacheValue;
			}
		}

		Object value = valueHolder.getArgumentValue();

		if (null != value) {
			KeyGenerator keyGenerator = getKeyGenerate(cacheConfig);

			// 生成key
			String key = keyGenerator.generateKey();
			// 添加到缓存
			searchCacheResolver.put(key, value);
			webRequest.setAttribute(cacheTokenKey, key, NativeWebRequest.SCOPE_REQUEST);
		}

		return value;
	}

	private String getCacheTokenKey(SearchCache config) {
		return StringUtils.hasText(config.value()) ? config.value() : defaultCacheToken;
	}

	private KeyGenerator getKeyGenerate(SearchCache config) {
		if (StringUtils.hasText(config.keyGeneratorRef())) {
			return beanFactory.getBean(config.keyGeneratorRef(), KeyGenerator.class);
		}
		return defaultKeyGenerator;
	}

	private ISearchCache getSearchCache(SearchCache config) {
		if (StringUtils.hasText(config.cacheImplRef())) {
			return beanFactory.getBean(config.cacheImplRef(), ISearchCache.class);
		}
		return defaultSearchCacheManager;
	}

	private SearchCache retrieveSearchCache(MethodParameter parameter) {
		Method objMethod = parameter.getMethod();
		Annotation[][] anon = objMethod.getParameterAnnotations();

		for (int i = 0; i < anon.length; i++) {
			Annotation[] an = anon[i];
			for (Annotation ann : an) {
				if (ann.annotationType() == SearchCache.class) {
					return (SearchCache) ann;
				}
			}
		}
		return null;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
		argumentResolverComposite
				.addArgumentResolver(new FormArgumentResolver(new RequestParamMethodArgumentResolver((ConfigurableBeanFactory) beanFactory, true)));
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (StringUtils.hasText(defaultConfig.getCacheImplRef())) {
			defaultSearchCacheManager = beanFactory.getBean(defaultConfig.getCacheImplRef(), ISearchCache.class);
		} else {
			defaultSearchCacheManager = beanFactory.getBean(EnableSearchCacheAutoConfiguration.DEFAULT_SEARCH_CACHE_REF, ISearchCache.class);
		}

		Assert.notNull(defaultSearchCacheManager, "searchCacheManager must not be null!!!");

		if (StringUtils.hasText(defaultConfig.getKeyGeneratorRef())) {
			defaultKeyGenerator = beanFactory.getBean(defaultConfig.getKeyGeneratorRef(), KeyGenerator.class);
		} else {
			defaultKeyGenerator = beanFactory.getBean(EnableSearchCacheAutoConfiguration.DEFAULT_KEY_GENERATE_REF, KeyGenerator.class);
		}

		Assert.notNull(defaultKeyGenerator, "keyGenerator must not be null!!!");
		if (StringUtils.hasText(defaultConfig.getCacheToken())) {
			defaultCacheToken = defaultConfig.getCacheToken();
		}

		handleCustomArgumentResolver();
		addDefaultArgumentResolver();
	}

	/**
	 * 添加默认的参数处理
	 */
	private void addDefaultArgumentResolver() {
		argumentResolverComposite.addArgumentResolver(new RequestBodyArgumentResolver(Arrays.asList(new MappingJackson2HttpMessageConverter())));
		argumentResolverComposite.addArgumentResolver(new FormArgumentResolver(new ServletModelAttributeMethodProcessor(true)));
	}

	/**
	 * 用户自定义参数处理，会优先于默认参数处理
	 */
	private void handleCustomArgumentResolver() {
		if (defaultConfig.getArgumentResolvers() != null) {
			String[] resolverRefs = defaultConfig.getArgumentResolvers().split(",");
			for (String resolverRef : resolverRefs) {
				if (StringUtils.isEmpty(resolverRef)) {
					throw new IllegalArgumentException("custom argumentResovlerRef can not be empty!!!");
				}
				Object resolver = beanFactory.getBean(resolverRef);
				if (resolver instanceof CacheableArgumentResolver) {
					argumentResolverComposite.addArgumentResolver((CacheableArgumentResolver) resolver);
				} else if (resolver instanceof HandlerMethodArgumentResolver) {
					argumentResolverComposite.addArgumentResolver(new ArgumentResolverWrapper((HandlerMethodArgumentResolver) resolver));
				} else {
					throw new IllegalArgumentException("resolver must be type of CacheableArgumentResolver or HandlerMethodArgumentResolver");
				}

			}
		}
	}

}
