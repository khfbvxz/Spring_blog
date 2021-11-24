package com.cos.jwt.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // mysql 쓰면 오토 인크리먼트 
	private long id;
	private String username;
	private String password;
	private String roles; // USER, ADMIN // model 에 role이라는 테이블 하나 더 만들어도 됨 
	
	public List<String> gerRoleList(){  // role 이 하나면 굳이 만들 필요 x  
		if(this.roles.length() > 0) {
			return Arrays.asList(this.roles.split(","));
		}
		return new ArrayList<>(); // null이 안뜨게만 
	}
}
