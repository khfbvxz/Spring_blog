package com.cos.blog3.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog3.config.auth.PrincipalDetail;
import com.cos.blog3.dto.ResponseDto;
import com.cos.blog3.model.Board;
import com.cos.blog3.service.BoardService;


@RestController
public class BoardApiController {
	

	@Autowired
	private BoardService boardService;
	
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {  
		
		boardService.글쓰기(board, principal.getUser());

		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);  
	}
	
	
	
	
	// 다음시간에 스프링 시큐리티 이용해서 로그인!1
//	@PostMapping("/api/user/login")
//	public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
//		System.out.println("UserApiController  : login 호출됨");
//	    User principal =  userService.로그인(user);  //principal 접근 주체 
//	    if(principal != null) {
//	    	session.setAttribute("principal", principal);
//	    	System.out.println("세션 : login 호출됨");
//	    }
//	    
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); 
//	}
}
