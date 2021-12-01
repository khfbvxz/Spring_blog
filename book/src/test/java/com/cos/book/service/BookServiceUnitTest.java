package com.cos.book.service;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cos.book.domain.Book;
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
	
	@Test
	public void 저장하기_테스트() {
//		// BODMociko방식
//		//given 
//		Book book = new Book();
//		book.setTitle("첵제목 1");
//		book.setAuthor("책저자1");
//		
//		//stub - 동작 지정 
//		when(bookRepository.save(book)).thenReturn(book);
//		
//		//trst execute 
//		Book bookEntity = bookService.저장하기(book);
//		
//		//then
//		assertEquals(bookEntity, book);
	}
}
