package com.cos.jwt.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin // 시큐리티 인증이 필요한 요청이 다 거부 즉 이건 인증이 필요하지 않은 요청만 허용 로그인 들어와서 하는 요청은 안되
@RestController
public class RestApiController {
	
	@GetMapping("home")
	public String home() {
		return "<h1>home</h1>";
	}
}
