package com.cos.security1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // View 를 리턴 하겠다
public class IndexController {
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
	// 스프링 시큐리티가 해당주소 낚아챔 - SecurityConfig 파일 생성후 작동 안함 
	@GetMapping("/login")
	public @ResponseBody String login() {
		return "login";
	}
	@GetMapping("/join")
	public @ResponseBody String join() {
		return "join";
	}
	@GetMapping("/joinProc")
	public @ResponseBody String joinProc() {
		return "회원가입 완료됨!";
	}
	
}
