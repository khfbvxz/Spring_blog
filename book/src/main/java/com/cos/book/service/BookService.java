package com.cos.book.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.book.domain.Book;
import com.cos.book.domain.BookRepository;

import lombok.RequiredArgsConstructor;


// 기능을 정의할 수 있고, 트랜잭션 관리 
@RequiredArgsConstructor // DI final이 붙어있는 
@Service
public class BookService {
	
	// 함수 => 송금 () -> 레포지토리에 여러개의 함수 실행 -> commit or rollback
	private final BookRepository bookRepository;
	
	@Transactional // 서비스 함수가 종료될 때 commit or rollback
	public Book 저장하기(Book book) {
		return bookRepository.save(book);
	}
	// jpa 변경감지라는 내부기능 활성화 x update시의 정합성을 유지, insert의 유령데이터현상 못막음 
	@Transactional(readOnly = true)
	public Book 한건가져오기(Long id) {
		return bookRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("id를 확인해 주세요 !"));
	}
	@Transactional(readOnly = true)
	public List<Book> 모두가져오기() {
		return bookRepository.findAll();
	}
	@Transactional
	public Book 수정하기(Long id,Book book) {
		// 더치체킹
		Book bookEntity = bookRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("id를 확인해 주세요 !")); // 영속화 (book object)
		bookEntity.setTitle(book.getTitle());
		bookEntity.setAuthor(book.getAuthor());
		
		return bookEntity;
	}
	
	@Transactional
	public String 삭제하기(Long id) {
		bookRepository.deleteById(id);// 오류 터지면 익셉션을 타이깐 신경쓰지마 
		return "ok";
	}
}
