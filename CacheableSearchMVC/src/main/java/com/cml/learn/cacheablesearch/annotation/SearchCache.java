package com.cml.learn.cacheablesearch.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 搜索条件缓存
 * <p>
 * 如果请求key对应的参数值为空，则表示没有缓存，会将对应的对象保存到缓存中，并且将生产的key保存到request的attribute中，key为请求key，默认为"cacheToken"
 * </p>
 * <p>
 * 如果请求中缓存key对应的值不为空，则会根据参数值获取缓存中的值
 * </p>
 * 
 * @author cml
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@Documented
public @interface SearchCache {
	String cacheImplRef() default "";

	String keyGeneratorRef() default "";

	/**
	 * 请求key,默认为cacheToken
	 * 
	 * @return
	 */
	String value() default "";

	/**
	 * 请求的方式
	 * 
	 * @return
	 */
	RequestType requestType() default RequestType.Form;

	enum RequestType {
		/**
		 * 使用body的方式，同@RequestBody功能一致
		 */
		RequestBody,
		/**
		 * 普通的表单提交
		 */
		Form;
	}
}
