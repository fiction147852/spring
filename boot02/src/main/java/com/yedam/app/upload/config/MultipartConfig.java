package com.yedam.app.upload.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;


// Springboot 3.0 부터 사용
@Configuration
public class MultipartConfig {
	
	@Bean
	MultipartResolver ultipartReslover() {
		return new StandardServletMultipartResolver();
	}
	
	@Bean
	MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setFileSizeThreshold(DataSize.ofMegabytes(0));	//서버가 필요한 데이터 크기를 자동계산하게한다
		factory.setLocation("C:/Temp");
		factory.setMaxFileSize(DataSize.ofMegabytes(10));		//10MB => 10485760B로 변환해서 사용
		factory.setMaxRequestSize(DataSize.ofMegabytes(100));

		return factory.createMultipartConfig();
	}
}
