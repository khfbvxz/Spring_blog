package com.cos.blog3.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog3.dto.ResponseDto;
import com.cos.blog3.model.RoleType;
import com.cos.blog3.model.User;
import com.cos.blog3.service.UserService;

@RestController
public class UserApiController {
	
	// 서비스를 디펜더시인척
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpSession session;
	//
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) { //username , password, email 
		System.out.println("UserApiController : save 호출됨");
		// 실제로 DB에 insert 를 하고 아래에서 return이 되면 되요 
		user.setRole(RoleType.USER);
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);  //resp 
	}
	
	// 다음시간에 스프링 시큐리티 이용해서 로그인!1
	@PostMapping("/api/user/login")
	public ResponseDto<Integer> login(@RequestBody User user) {
		System.out.println("UserApiController  : login 호출됨");
	    User principal =  userService.로그인(user);  //principal 접근 주체 
	    if(principal != null) {
	    	session.setAttribute("principal", principal);
	    	System.out.println("세션 : login 호출됨");
	    }
	    
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); 
	}
}
