package com.cos.book.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cos.book.domain.BookRepository;

//단위테스트 (service 와 관련됨 애들만 메모리에 띄우면됨.)
// BookRepository => 가짜 객체로 만들 수 있음.
// @ExtendWith(MockitoExtension.class)  



@ExtendWith(MockitoExtension.class)
public class BookServiceUnitTest {
	
	//@Mock //Mockito 라는 메모리 가짜로 객체 띄우기
	@InjectMocks // BookService 객체가 만들어 질때 BookServiceUnitTest 파일에 @Mock로 등록된 모든 애들을 주입받는다. 
	private BookService bookService;
	
	@Mock
	private BookRepository bookRepository;
	
}
