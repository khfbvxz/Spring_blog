package com.cos.blog3.test;

//import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import lombok.Getter;
//import lombok.Setter;

////@Getter 
////@Setter
@Data   // Getter  Setter
//@AllArgsConstructor  // 생성자
@NoArgsConstructor  // 빈 생성자 
//@RequiredArgsConstructor  // final 이 붙은 애들에 대한 컨스트럭터 만들어줌 

public class Member {
	// final 데이터베이스의 값을 들고 온다 변경되지 않게   불변성 
	// 변경할 일이 있으면 
	
	private int id;
	private String username;
	private String password;
	private String email;
	
	@Builder  
	public Member(int id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
}
