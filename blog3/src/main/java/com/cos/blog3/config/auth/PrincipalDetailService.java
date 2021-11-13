package com.cos.blog3.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.blog3.model.User;
import com.cos.blog3.repository.UserRepository;

@Service // 빈 등록 
public class PrincipalDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	// 스프링이 로그인 요청을 가로채서 username, password 변수 2개를 가로채는데 
	// password 부분처리는 알아서 함
	// username 이 DB에 있는지만 확인해주면 됨.
	// loadUserByUsername 함수에서 함 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepository.findByUsername(username)
				.orElseThrow(()-> {
					return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. : " + username);
				});
		return new PrincipalDetail(principal); // 시큐리티 세션에 유저 정보가 저장이됨 // 안만들어 주면 아이디: user, 패스워드 : 콘솔창
	}
}
