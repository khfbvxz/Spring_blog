package com.cos.security1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.security1.model.User;
import com.cos.security1.repository.UserRepository;

@Controller // View 를 리턴 하겠다
public class IndexController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	//localhost : 8082/
	//localhost : 8082
	@GetMapping({"","/"})
	public String index() {
		//머스테치 기본적으로 스프링이 권장한다. 
		// 머스테치 기본 폴더 src/main/resources /
		// 뷰 리졸버를 설정 : templates (perfix), .mustache (suffix)
		return "index"; // src/main/resources/templates/index.mustache
	}
	
	@GetMapping("/user")
	public @ResponseBody String user() {
		return "user";
	}
	@GetMapping("/admin")
	public @ResponseBody String admin() {
		return "admin";
	}
	@GetMapping("/manager")
	public @ResponseBody String manager() {
		return "manager";
	}
	// 스프링 시큐리티가 해당주소 낚아챔 
	@GetMapping("/loginForm")
	public String loginForm() {
		return "loginForm";
	}
	@GetMapping("/joinForm")
	public String joinForm() {
		return "joinForm";
	}
	
	@PostMapping("/join")
	public  String join(User user) {
		System.out.println(user);
		user.setRole("ROLE_USER");
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		userRepository.save(user); // 회원가입 잘됨 but  1234=> 시큐리티로 로그인 할수 없음 , 이유는 패스워드가 암호화가 안되있음
		return "redirect:/loginForm";
	}
	
	//특정 메소드 에만 걸고 싶을때 
	@Secured("ROLE_ADMIN") // 특정 메소드에 간단하게 걸수 있음 하나만 걸때 
	@GetMapping("/info")
	public @ResponseBody String info() {
		return "개인정보";
	}
	
	//@PostAuthorize  // 
	// 데이터라는 메소드 실행하기전 실행  // 여러개 hasRole 쓰면 된다 
	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")// "USER_ROLE" 도 가능 USER_ROLE도
	@GetMapping("/data")
	public @ResponseBody String data() {
		return "데이터정보";
	}
	
}