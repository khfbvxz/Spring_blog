package com.cos.book.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.cos.book.domain.Book;
import com.cos.book.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
// hamcrest
import org.hamcrest.Matchers; //

//단위 테스트(Controller, Filter, ControllerAdvice) 관련 로직만 띄우기 
// 
// @ExtendWith(SpringExtension.class)

@Slf4j
@WebMvcTest
public class BookControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean // IoC환경에 bean 등록됨
	private BookService bookService;
	
	//BDD mokito 패턴 given when then
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
	@Test
	public void findAll_테스트() throws Exception{
		//given
		List<Book> books = new ArrayList<>();
		books.add(new Book(1L, "스프링부트따라하기","코스"));
		books.add(new Book(2L, "리액트 따라하기","코스"));
		
		when(bookService.모두가져오기()).thenReturn(books);
		
		ResultActions resultAction = mockMvc.perform(get("/book")
				.accept(MediaType.APPLICATION_JSON_UTF8));
		
		//then 
		resultAction
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", Matchers.hasSize(2)))
			.andExpect(jsonPath("$.[0].title").value("스프링부트 따라하기" ))
			.andDo(MockMvcResultHandlers.print());
		
	}
	
	@Test
	public void findById_테스트() throws Exception{
		//given 
		Long id = 1L;
		when(bookService.한건가져오기(id)).thenReturn(new Book(1L,"자바 공부하기","쌀"));
		
		//when
		ResultActions resultAction = mockMvc.perform(get("/book/{id}",id)
				.accept(MediaType.APPLICATION_JSON_UTF8));
		
		//then 
		resultAction
		  .andExpect(status().isOk())
		  .andExpect(jsonPath("$.title").value("자바 공부하기"))
		  .andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void update_테스트() throws Exception{
		//given 
		
		Long id = 1L;
		Book book = new Book(null, "C++ 따라하기","코스");
		String content = new ObjectMapper().writeValueAsString(book);
		when(bookService.수정하기(id, book)).thenReturn(new Book(1L, "C++ 따라하기","코스"));
		
		//when
		ResultActions resultAction = mockMvc.perform(put("/book/{id}",id)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content)
				.accept(MediaType.APPLICATION_JSON_UTF8));
		
		//then 
		resultAction
		  .andExpect(status().isOk())
		  .andExpect(jsonPath("$.title").value("C++ 따라하기"))
		  .andDo(MockMvcResultHandlers.print());
	}
	@Test
	public void delete_테스트() throws Exception{
		//given 
		
		Long id = 1L;
		when(bookService.삭제하기(id)).thenReturn("ok");
		
		//when
		ResultActions resultAction = mockMvc.perform(delete("/book/{id}",id)
				.accept(MediaType.TEXT_PLAIN));
		
		//then 
		resultAction
		  .andExpect(status().isOk())
		  .andDo(MockMvcResultHandlers.print());
		
		// 문자 리턴할 때 
		MvcResult requestResult = resultAction.andReturn();
		String result = requestResult.getResponse().getContentAsString();
		
		assertEquals("ok", result);
		//assertEquals("ok1", result);
		
	}
}
