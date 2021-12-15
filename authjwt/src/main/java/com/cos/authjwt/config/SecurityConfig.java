package com.cos.authjwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cos.authjwt.security.CustomUserDetailsService;
import com.cos.authjwt.security.RestAuthenticationEntryPoint;
import com.cos.authjwt.security.TokenAuthenticationFilter;
import com.cos.authjwt.security.user.CustomOAuth2UserService;
import com.cos.authjwt.security.user.HttpCookieOAuth2AuthorizationRequestRepository;
import com.cos.authjwt.security.user.OAuth2AuthenticationFailureHandler;
import com.cos.authjwt.security.user.OAuth2AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private CustomOAuth2UserService customOAuth2UserService;

	@Autowired
	private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

	@Autowired
	private OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

	@Autowired
	private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

	// athenticationJWTtokenFilter 생성자 만들고 
	@Bean
	public TokenAuthenticationFilter tokenAuthenticationFilter() {
		return new TokenAuthenticationFilter();
	}

	@Bean
	public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
		return new HttpCookieOAuth2AuthorizationRequestRepository();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}
// 비밀번호 암호화 
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors() // cors 허용
					.and()
				.sessionManagement() // session Creation Policy를 STATELESS로 정의해 session을 사용하지 않겠다 선언
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
					.and()
				.csrf()
					.disable()
				.formLogin()
                	.disable()
            	.httpBasic()
                	.disable()
            	.exceptionHandling()
                	.authenticationEntryPoint(new RestAuthenticationEntryPoint())
                	.and()
        		.authorizeRequests()
		            .antMatchers("/",
		                "/error",
		                "/favicon.ico",
		                "/**/*.png",
		                "/**/*.gif",
		                "/**/*.svg",
		                "/**/*.jpg",
		                "/**/*.html",
		                "/**/*.css",
		                "/**/*.js")
		                .permitAll()
	                .antMatchers("/auth/**", "/oauth2/**")
		                .permitAll()
	                .anyRequest()
	                	.authenticated()
                	.and()
            	.oauth2Login()
	                .authorizationEndpoint()
	                    .baseUri("/oauth2/authorize")
	                    .authorizationRequestRepository(cookieAuthorizationRequestRepository())
	                    .and()
	                .redirectionEndpoint()
	                    .baseUri("/oauth2/callback/*")
	                    .and()
	                .userInfoEndpoint()
	                    .userService(customOAuth2UserService)
	                    .and()
	                .successHandler(oAuth2AuthenticationSuccessHandler)
	                .failureHandler(oAuth2AuthenticationFailureHandler);

		// Add our custom Token based authentication filter
		http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
