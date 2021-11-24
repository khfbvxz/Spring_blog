package com.cos.jwt.controller;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.jwt.model.User;
import com.cos.jwt.repository.UserRepository;

import lombok.RequiredArgsConstructor;

//@CrossOrigin // 시큐리티 인증이 필요한 요청이 다 거부 즉 이건 인증이 필요하지 않은 요청만 허용 로그인 들어와서 하는 요청은 안되
@RestController
@RequiredArgsConstructor
public class RestApiController {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("home")
	public String home() {
		return "<h1>home</h1>";
	}
	@PostMapping("token")
	public String token() {
		return "<h1>token</h1>";
	}
	
	@PostMapping("/join")
	public String join(@RequestBody User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword())); //1234 ppp -> ABC33333
		user.setRoles("ROLE_USER");
		userRepository.save(user);
		return "회원가입완료";
		//$2a$10$S5slaOpOAqQyyrxaY2/E7.lTsWRb.J7tShiGbrC86FyyDQfaNWbou
	}
	// user,manager,admin 권한만 접근가능
	@GetMapping("/api/v1/user")
	public String user() {
		return "user";
	}
	//manager,admin 권한만 접근가능	
	@GetMapping("/api/v1/manager")
	public String manager() {
		return "manager";
	}
	//admin 권한만 접근가능
	@GetMapping("/api/v1/admin")
	public String admin() {
		return "admin";
	}
}
