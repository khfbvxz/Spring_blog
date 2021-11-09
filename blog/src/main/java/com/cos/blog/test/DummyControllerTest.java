package com.cos.blog.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
	// 의존성 주입!! (DI) 
	@Autowired // DummyControllerTest 가 메모리에 뜰때 UserRepository 도 같이 띄워준다 
	private UserRepository userRepository ;
	
	// http://localhost:8000/blog/dummy/join (요청)
	//http 의 body 에 username , password, email 데이터를 가지고 (요청)
	@PostMapping("/dummy/join")
	// public String join(@RequestParam("username") String username , String password, String email) {
	public String join(User user) { // key = value (약속된 규칙)
		System.out.println("id: " + user.getId());  // id auto_increment 적용됨 
		System.out.println("username: " + user.getUsername());
		System.out.println("password: " + user.getPassword());
		System.out.println("email: " + user.getEmail());
		System.out.println("role: " + user.getRole());
		System.out.println("createDate: " + user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입 완료되었습니다.";
	}
}
