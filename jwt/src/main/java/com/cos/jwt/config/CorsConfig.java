package com.cos.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
	
	@Bean
	public CorsFilter corsFilter() {
		System.out.println("corsFilter");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		// config 설정
		config.setAllowCredentials(true); // 내 서버가 응답을 할 때 json을 자바스크립트에서 처리할 수 있게 할지를 설정하는 것
		config.addAllowedOrigin("*"); // 모든 ip에 응답을 허용 하겠다.  // origin을 어디에서든
		config.addAllowedHeader("*"); // 모든 headerdp응답을 허용하겠다.     //모든 헤더도
		config.addAllowedMethod("*");  // 모든 post,get,put,delete,patch 요청을 허용하겠다.  // 모든 호스트 무슨요청이든 다 허용 
		source.registerCorsConfiguration("/api/**", config); // 소스에다 등록 /api/** config 설정을 따라라 해주고 리턴
		return new CorsFilter(source);
		
		// 이렇게 걸기만 하면 의미 없음  이 메서드를 필터에 등록 해야함 securityconfig 로 이동 
	}
}
