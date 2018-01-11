package com.cml.learn.cacheablesearch.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.cml.learn.cacheablesearch.cache.ISearchCache;
import com.cml.learn.cacheablesearch.cache.SessionSearchCache;
import com.cml.learn.cacheablesearch.key.KeyGenerator;
import com.cml.learn.cacheablesearch.key.UUIDKeyGenerator;

/**
 * 搜索条件缓存
 * <p>
 * 如果请求key对应的参数值为空，则表示没有缓存，会将对应的对象保存到缓存中，并且将生产的key保存到Model对象中（如果有此参数），key为请求key，默认为"cacheToken"
 * </p>
 * <p>
 * 如果请求中缓存key对应的值不为空，则会根据参数值获取缓存中的值，并赋值到添加了此注解的对象上
 * </p>
 * 
 * @author cml
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@Documented
public @interface SearchCache {
	Class<? extends ISearchCache> cacheImpl() default SessionSearchCache.class;

	Class<? extends KeyGenerator> keyGenerator() default UUIDKeyGenerator.class;

	/**
	 * 请求key
	 * 
	 * @return
	 */
	String value() default "cacheToken";
}
