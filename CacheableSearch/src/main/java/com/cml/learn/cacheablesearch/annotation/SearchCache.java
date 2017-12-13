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

	String value() default "cacheToken";
}
