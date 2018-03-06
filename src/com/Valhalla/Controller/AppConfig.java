package com.Valhalla.Controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Configuration
public class AppConfig {
	//This MultipartResolver comes with a series of set method to define properties such as the maximum size for uploads:
		@Bean(name = "multipartResolver")
		public CommonsMultipartResolver multipartResolver() {
		    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		    multipartResolver.setMaxUploadSize(100000);
		    return multipartResolver;
		}
		
		@Bean
		public StandardServletMultipartResolver multipartResolver1() {
		    return new StandardServletMultipartResolver();
		}
		
}
