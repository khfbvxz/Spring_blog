package com.cos.blog3.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog3.config.auth.PrincipalDetailService;

// 빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는것 
// 3개 세트 이해안가면 그냥 걸어 
@Configuration          // 빈 등록 (IoC관리)
@EnableWebSecurity //시큐리티 필터가 등록이 된다. // 시큐리티 필터 추가 = 스프링 시큐리티가 활성화 되어있는데 어떤 설정을 해당 파일에서 하겠다.
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻. 
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}


	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean // IoC가 된다.  스프링이 관리한다. 
	public BCryptPasswordEncoder encodePWD() {
		// 암호화 해서 넣어준다. 
		//String encPassword = new BCryptPasswordEncoder().encode("1234");
		return new BCryptPasswordEncoder();
	}
	
	// 시큐리티가 대신 로그인 해주는데 password를 가로채기를 하는데 
	// 해당 password 가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
	// 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음 .
	//principalDetailService 안넣어주면 패스워드 비교를 할 수 없음 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.csrf().disable()   // csrf 토큰 비활성화 (테스트시 걸어두는게 좋음 ) 
			.authorizeRequests()
				.antMatchers("/","/auth/**","/js/**","/css/**","/image/**","dummy/**")
				.permitAll()
				.anyRequest()
				.authenticated() 
			.and()
				.formLogin()
				.loginPage("/auth/loginForm")
				.loginProcessingUrl("/auth/loginProc")
				.defaultSuccessUrl("/");
				// 스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로 채서 대신 로그인 해준다.
				//.failureUrl("/fail");실패시 
	}
}
