package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
// ORM - >  JAVA(다른언어) Object -> 테이블로 매핑해주는 기술
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌터 패턴 
@Entity // User 클래스가 MySQL에 테이블이 생성이 된다.
// @DynamicInsert // insert 시에 null 데이터를 제외시킨다   
public class User {
	@Id  // PK 
	@GeneratedValue(strategy = GenerationType.IDENTITY)// 넘버링  프로젝트에서 연결된 DB의 넘버링 전략을 따라간다. 
	private int id; // 시퀀스 , auto_increment 로 넘버링
	
	@Column(nullable = false, length = 30)
	private String username; // 아이디
	@Column(nullable = false, length = 100)// 123456 => 해쉬 ( 비밀번호 암호화 )
	private String password ; 
	@Column(nullable = false, length = 50)
	private String email ; 
	
//	@ColumnDefault("'user'")
//	private String role;
	//DB는 RoleType 이 없음 
	@Enumerated(EnumType.STRING)
	private RoleType role; //  정확하게 할려면 Enum 을 쓰는게 좋다. 도메인  // admin , user , manager 
	@CreationTimestamp // 시간이 자동 입력
	private Timestamp createDate;  //  원래 회원정보를 수정하는 업데이트데이트도 필요함 
	
	
}
