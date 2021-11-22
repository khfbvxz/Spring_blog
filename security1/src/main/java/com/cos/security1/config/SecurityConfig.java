package com.cos.security1.config;

import org.springframework.beans.factory.annotation.Autowired;

//1. 코드받기(인증), 2. 엑세스 토큰(권한 사용자 정보에 접근할수 있는 ) 3. 사용자 정보 가져오로 4-1.그 정보를 토대로 회원가입을 자동으로 진행시키기도함
//4-2 이메일 전화번호 이름 아이디 쇼핑몰인 경우 -> (집주소) 백화점 몰-> (vip 등급, 일반 등급) 


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.security1.config.ouath.PrincipalOauth2UserService;



@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터 체인에 등록이됨!    //prePostEnabled perAuthorize , postAuthorize어노테이션 활성화 
@EnableGlobalMethodSecurity(securedEnabled = true , prePostEnabled = true) // secured 어노테이션 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PrincipalOauth2UserService principalOauth2UserService;
	
	// 해당 메서드의 리턴되는 오브젝트를 IoC로 등록해준다.
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/user/**").authenticated()//인증만 되면 들어갈 수 있는 주소!! 
			.antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.anyRequest().permitAll()
			.and()
			.formLogin()
			.loginPage("/loginForm")
			.loginProcessingUrl("/login") // /login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행해줍니다.
			.defaultSuccessUrl("/")
			.and()
			.oauth2Login()
			.loginPage("/loginForm")
			.userInfoEndpoint()
			.userService(principalOauth2UserService); 
		
		
		// 구글 로그인이 완료된 뒤의 후처리 필요함. Tip, 코드 x (엑세스토큰+사용자프로필정보 O)
		// userService 타입 ouath2 user 서비스여야함 
		
		
		
		
		
//		.loginPage("/loginForm")
//		.usernameParameter("username2")  // 파라미터 바꾸고 싶으면 
//		.loginProcessingUrl("/login")
	}
}