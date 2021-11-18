package com.cos.security1.config;

import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration // IoC 등록
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Override  // 머스테치 재설정 
	public void configureViewResolvers(ViewResolverRegistry registry) {
		MustacheViewResolver resolver = new MustacheViewResolver();
		  resolver.setCharset("UTF-8");
		  resolver.setContentType("text/html; charset=UTF-8");
		  resolver.setPrefix("classpath:/templates/");//classpath: 우리들 프로젝트
		  resolver.setSuffix(".html");
		  
		  registry.viewResolver(resolver);
	}
}