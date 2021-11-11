package com.cos.blog3.controller.api;

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
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) { //username , password, email 
		System.out.println("UserApiController 호출됨");
		// 실제로 DB에 insert 를 하고 아래에서 return이 되면 되요 
		user.setRole(RoleType.USER);
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);  //resp 
	}
}
