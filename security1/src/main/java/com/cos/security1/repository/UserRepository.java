package com.cos.security1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.security1.model.User;

//CRUD 함수를 JpaRepository가 들고 있음
// @Repository 라는 어노테이션이 없어도 IoC 가 됨 이유는  JpaRepository 상속받았기 때문에
public interface UserRepository extends JpaRepository<User, Integer>{

}
