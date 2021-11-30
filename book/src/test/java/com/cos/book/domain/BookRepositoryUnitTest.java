package com.cos.book.domain;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

// 단위 테스트 (DB 에 빈이 IOC에 등록되면됨 

@Transactional
@AutoConfigureTestDatabase(replace = Replace.ANY) // 가짜 디비로 테스트 
@DataJpaTest // Repository들을 다 IoC 등록해줌 
public class BookRepositoryUnitTest {

	@Autowired
	private BookRepository bookRepository;
}
