package com.cml.learn.cacheablesearch.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.cml.learn.cacheablesearch.configuration.EnableSearchCacheAutoConfiguration;

@Import(EnableSearchCacheAutoConfiguration.class)
@SpringBootApplication()
public class SearchCacheApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(SearchCacheApplication.class, args);
	}

}
