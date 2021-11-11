package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TempControllerTest {
	
	//http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("temphome()");
		//파일리턴 기본경로 : src/main/resources/static
		//리턴명 home.html
		// 풀 경로 : src/main/resources/static/html
		return "/home.html";
	}
	@RequestMapping("/temp/jsp")
	public String tempJsp() {
//		prefix: /WEB-INF/views/
		//풀 네임 : /WEB-INF/views/test.jsp
		return "test";
	}
}
