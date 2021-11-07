package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 사용자가 요청 -> 응답 (HTML 파일)
// @Controller 

// 사용자가 요청 -> 응답 ( Data ) 
@RestController
public class HttpControllerTest {
	// 인터넷 브라우저 요청은 무조건 get요청밖에 할 수 없다. 
	// http://localhost:8081/http/get
	@GetMapping("/http/get")  
	public String getTest(Member m) { //id=1&username=sar&password=1234&email=ssar@nate.com
		return "get 요청: "+m.getId() +",  _   " + m.getUsername()+",  _   " +m.getPassword()+",  _   " +m.getEmail();
	}
	
	// http://localhost:8081/http/post
	@PostMapping("/http/post")   // text/plain  application/json
	public String postTest(@RequestBody Member m) { // MessageConvertor ( 스프링부트)
		return "post 요청: "+m.getId() +",  _   " + m.getUsername()+",  _   " +m.getPassword()+",  _   " +m.getEmail();
	}
	
	// http://localhost:8081/http/put
	@PutMapping("/http/put")
	public String putTest() {
		return "put 요청";
	}
	
	// http://localhost:8081/http/delete
	@DeleteMapping("/http/delete")
	public String deletetTest() {
		return "delete 요청";
	}
	
}
