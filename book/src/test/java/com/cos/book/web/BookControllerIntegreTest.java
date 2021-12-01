package com.cos.book.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import com.cos.book.domain.Book;
import com.cos.book.domain.BookRepository;
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
	private BookRepository bookRepository;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private EntityManager entityManager;
	
	// 모든 테스트함수가 실행되기전 각각 실행됨 
	@BeforeEach 
	public void init() {
//		entityManager.persist(new Book());  //persist 영속
//		List<Book> books = new ArrayList<>();
//		books.add(new Book(null, "스프링부트따라하기","코스"));
//		books.add(new Book(null, "리액트 따라하기","코스"));
//		books.add(new Book(null, "JUNIT 따라하기","코스"));
//		bookRepository.saveAll(books);
		entityManager.createNativeQuery("ALTER TABLE book ALTER COLUMN id RESTART WITH 1").executeUpdate();
	}
//	@AfterEach
//	public void end() {
//		bookRepository.deleteAll();
//	}
	
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
		//when(bookService.저장하기(book)).thenReturn(new Book(1L, "스프링 따라하기","코스"));
		
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
		books.add(new Book(null, "스프링부트따라하기","코스"));
		books.add(new Book(null, "리액트 따라하기","코스"));
		books.add(new Book(null, "JUNIT 따라하기","코스"));
		bookRepository.saveAll(books);
//		when
		
		ResultActions resultAction = mockMvc.perform(get("/book")
				.accept(MediaType.APPLICATION_JSON_UTF8));
		
		//then 
		resultAction
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", Matchers.hasSize(3)))
			.andExpect(jsonPath("$.[2].title").value("JUNIT 따라하기" ))
			.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void findById_테스트() throws Exception{
		//given 
		
		Long id = 2L;
		List<Book> books = new ArrayList<>();
		books.add(new Book(null, "스프링부트따라하기","코스"));
		books.add(new Book(null, "리액트 따라하기","코스"));
		books.add(new Book(null, "JUNIT 따라하기","코스"));
		bookRepository.saveAll(books);

		//when
		ResultActions resultAction = mockMvc.perform(get("/book/{id}",id)
				.accept(MediaType.APPLICATION_JSON_UTF8));
		
		//then 
		resultAction
		  .andExpect(status().isOk())
		  .andExpect(jsonPath("$.title").value("리액트 따라하기"))
		  .andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void update_테스트() throws Exception{
		//given 
		
		Long id = 2L;
		List<Book> books = new ArrayList<>();
		books.add(new Book(null, "스프링부트따라하기","코스"));
		books.add(new Book(null, "리액트 따라하기","코스"));
		books.add(new Book(null, "JUNIT 따라하기","코스"));
		bookRepository.saveAll(books);
		
		Book book = new Book(null, "C++ 따라하기","코스");
		String content = new ObjectMapper().writeValueAsString(book);
		
		//when
		ResultActions resultAction = mockMvc.perform(put("/book/{id}",id)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content)
				.accept(MediaType.APPLICATION_JSON_UTF8));
		
		//then 
		resultAction
		  .andExpect(status().isOk())
		  .andExpect(jsonPath("$.id").value(2L))
		  .andExpect(jsonPath("$.title").value("C++ 따라하기"))
		  .andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void delete_테스트() throws Exception{
		//given 
		
		Long id = 1L;
		List<Book> books = new ArrayList<>();
		books.add(new Book(null, "스프링부트따라하기","코스"));
		books.add(new Book(null, "리액트 따라하기","코스"));
		books.add(new Book(null, "JUNIT 따라하기","코스"));
		bookRepository.saveAll(books);
		
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
