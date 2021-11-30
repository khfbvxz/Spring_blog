package com.cos.book.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import com.cos.book.domain.Book;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

// 통합 테스트 (모든 Bean들을 똑같이 IoC 올리고 테스트 

//모든 애들이 메모리에 뜸  
// MOCK 실제 톰켓을 올리는게아니라 다름 톰켓으로 테스트
//RANDOM_PORT = 실제 톰캣으로 테스트 

// @AutoConfigureMockMvc MockMvc를 IoC에 등록해줌
//@Transactional 각각의 테스트 함수가 종료될 때마다 트랜잭션을 rollback 해주는 어노테이션 

@Slf4j
@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)  
public class BookControllerIntegreTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void save_테스트() throws Exception {
//		log.info("save_테스트()시작==============");
//		Book book = bookService.저장하기(new Book(null,"제목","코스"));
//		System.out.println("book? "+ book);
		Book book = new Book(null, "스프링 따라하기","코스");
		// given ( 테스트를 위한 준비)
		String content = new ObjectMapper().writeValueAsString(book);
//		log.info(content);
		// 행동 지정 
		when(bookService.저장하기(book)).thenReturn(new Book(1L, "스프링 따라하기","코스"));
		
		//when (테스트 실행)
		ResultActions resultAction = mockMvc.perform(post("/book")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content)
				.accept(MediaType.APPLICATION_JSON_UTF8));
		
		//then (검증)
		resultAction
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.title").value("스프링 따라하기"))
			.andDo(MockMvcResultHandlers.print());
	}
}
