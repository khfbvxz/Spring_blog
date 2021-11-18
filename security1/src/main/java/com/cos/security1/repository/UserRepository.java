package com.cos.security1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.security1.model.User;

//CRUD 함수를 JpaRepository가 들고 있음
// @Repository 라는 어노테이션이 없어도 IoC 가 됨 이유는  JpaRepository 상속받았기 때문에
public interface UserRepository extends JpaRepository<User, Integer>{
	//findBy규칙 -> Username 문법
	// select * from user where username = 1? 
	
	public User findByUsername(String username); // Jpa Query method
	
//	// select * from user where email= ?
//	public User findByEmail();
}
