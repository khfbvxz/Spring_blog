package com.cos.blog3.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog3.config.auth.PrincipalDetail;
import com.cos.blog3.dto.ReplySaveRequestDto;
import com.cos.blog3.dto.ResponseDto;
import com.cos.blog3.model.Board;
import com.cos.blog3.model.Reply;
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
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id){
		boardService.글삭제하기(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);  
	}
	
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board){
		boardService.글수정하기(id, board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);  
	}
	
	// 원래 데이터를 받을때 컨트롤러에서 dto를 만들어서 받는게 좋다.
	// dto 사용하지 않는 이유 !! 작아서 ...
	
	@PostMapping("/api/board/{boardId}/reply")
	public ResponseDto<Integer> replySave( @RequestBody ReplySaveRequestDto replySaveRequestDto) {
		
//		reply.setUser(principal.getUser());
		boardService.댓글쓰기(replySaveRequestDto);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);  
	}
	@DeleteMapping("/api/board/{boardId}/reply/{replyId}")
	public ResponseDto<Integer> replyDelete(@PathVariable int replyId){
		boardService.댓글삭제(replyId);
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
