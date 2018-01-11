package com.cml.learn.cacheablesearch.sample.framework;

import org.joda.time.DateTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.cml.learn.cacheablesearch.sample.framework.deserializer.DateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.setSerializationInclusion(Include.NON_NULL);

		SimpleModule serModule = new SimpleModule();
		serModule.addSerializer(DateTime.class, new DateTimeDeserializer(FrameWorkConfig.DEFAULT_DATE_TIME_FORMAT));
		objectMapper.registerModule(serModule);

		jsonConverter.setObjectMapper(objectMapper);

		return jsonConverter;
	}
}
