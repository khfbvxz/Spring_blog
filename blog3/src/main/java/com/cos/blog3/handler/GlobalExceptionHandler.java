package com.cos.blog3.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog3.dto.ResponseDto;

@ControllerAdvice // 어디에서든 Exception 발생하면 이쪽으로 오게 하는 
@RestController
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value=Exception.class)//IllegalArgumentException 발생하면 그 익셉션에 대한 에러는 함수에다가 전달한다 스프링이   
	public ResponseDto<String> handleArgumentException(Exception e) {
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
//		return "<h1>"+e.getMessage()+"</h1>";
	}
	
	//다른 익셉션을 받고 싶다면 IllegalArgumentException -> Exception 
	
}
