package com.cml.learn.cacheablesearch.configuration;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cml.learn.cacheablesearch.annotation.SearchCache;
import com.cml.learn.cacheablesearch.cache.ISearchCache;
import com.cml.learn.cacheablesearch.key.KeyGenerator;

@Aspect()
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SearchCacheAspect implements BeanFactoryAware {

	private static Logger log = LoggerFactory.getLogger(SearchCacheAspect.class);

	private BeanFactory beanFactory;

	@Pointcut("execution(* *..*.*(.. , @com.cml.learn.cacheablesearch.annotation.SearchCache (*), ..))")
	public void cacheAspect() {
	}

	@Around("cacheAspect()")
	public Object cacheAdvice(ProceedingJoinPoint point) throws Throwable {

		// HttpServletRequest request = retrieveParam(point,
		// HttpServletRequest.class);
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

		// do nothing
		if (null == request) {
			log.warn("not found HttpServletRequest param!!!");
			return point.proceed();
		}

		Object[] args = point.getArgs();

		// 获取添加了注解的参数对象
		ParamHolder<SearchCache> searchCachePutHolder = retrieveParamConfig(point, SearchCache.class);
		if (null != searchCachePutHolder) {
			String cacheTokenKey = searchCachePutHolder.anonTarget.value();
			// 修改args参数值
			ISearchCache searchCache = beanFactory.getBean(searchCachePutHolder.anonTarget.cacheImpl());
			Assert.notNull(searchCache, "cannot found impl class !!!!");

			String cacheKey = request.getParameter(cacheTokenKey);
			// 获取缓存
			if (null != cacheKey) {
				args[searchCachePutHolder.paramIndex] = searchCache.get(cacheKey);
			} else {
				cacheKey = generateKey(searchCachePutHolder);
				// 生成缓存数据
				Object value = args[searchCachePutHolder.paramIndex];
				searchCache.put(cacheKey, value);
			}
			// 如果有model参数，将缓存的key存放到model中
			Model model = retrieveParam(point, Model.class);
			if (null != model) {
				model.addAttribute(cacheTokenKey, cacheKey);
			}
			// 将缓存的key存放到request中
			request.setAttribute(cacheTokenKey, cacheKey);
		}

		Object value = point.proceed(args);

		return value;
	}

	private String generateKey(ParamHolder<SearchCache> searchCachePutHolder) {
		KeyGenerator keyGenerator = beanFactory.getBean(searchCachePutHolder.anonTarget.keyGenerator());
		return keyGenerator.generateKey();
	}

	private <T> T retrieveParam(ProceedingJoinPoint point, Class target) {
		Object[] args = point.getArgs();
		MethodSignature signature = (MethodSignature) point.getSignature();
		Method objMethod = signature.getMethod();
		Annotation[][] anon = objMethod.getParameterAnnotations();
		Class[] paramTypes = objMethod.getParameterTypes();
		for (int i = 0; i < paramTypes.length; i++) {
			if (paramTypes[i].equals(target)) {
				return (T) args[i];
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private <T> ParamHolder<T> retrieveParamConfig(ProceedingJoinPoint point, Class<? extends Annotation> anonTarget) {
		Object[] args = point.getArgs();
		MethodSignature signature = (MethodSignature) point.getSignature();
		Method objMethod = signature.getMethod();
		Annotation[][] anon = objMethod.getParameterAnnotations();

		for (int i = 0; i < anon.length; i++) {
			Annotation[] an = anon[i];
			for (Annotation ann : an) {
				if (ann.annotationType() == anonTarget) {
					ParamHolder<T> holder = new ParamHolder<>();
					holder.anonTarget = (T) ann;
					holder.paramIndex = i;
					holder.argValue = args[i];
					holder.paramType = objMethod.getParameterTypes()[i];
					return holder;
				}
			}
		}
		return null;
	}

	static class ParamHolder<T> {
		T anonTarget;
		Object argValue;
		int paramIndex;
		Class paramType;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}
}
