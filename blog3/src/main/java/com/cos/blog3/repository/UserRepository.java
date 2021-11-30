package com.cos.blog3.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
// import org.springframework.data.jpa.repository.Query;

import com.cos.blog3.model.User;

//DAO 
// 스프링 레거시 공부했던 사람은 오해할 수 있는데 빈으로 등록이 되나요 의심?
// 빈으로 등록된다는거 스프링 ioc 객체를 가지고 있나요 라고 물어보는 건데 
// 그렇게 해야지 필요한 것에서 인젝션 이라는 것을 통해 di 를 할 수 있다 
// 자동으로 bean으로 등록한다. 
// 생략가능 
//@Repository // 예전에는 얘들 등록 해야한다. 그래야 스프링 컴포넌트 스캔을 할 때  유저레포지터리 를 메모리 띄워주는데 
public interface UserRepository extends JpaRepository<User, Integer>{
	// select * from user where username = 1?;
	Optional<User> findByUsername(String username);
	
	
}

//JPA Naming 전략 쿼리
// select * from user where username = ?1 and password =?2 ; 이게 동작이 된다 
//User findByUsernameAndPassword(String username, String password);

//// 또는
//@Query(value="select * from user where username = ?1 and password =?2", nativeQuery = true)
//User login(String username, String password);