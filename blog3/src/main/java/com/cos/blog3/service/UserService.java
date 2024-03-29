package com.cos.blog3.service;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog3.model.RoleType;
import com.cos.blog3.model.User;
import com.cos.blog3.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌  IoC를 해준다.
@Service
public class UserService {
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired  // 패스워드 암호화
	private BCryptPasswordEncoder encoder;
	
	@Transactional(readOnly = true)
	public User 회원찾기(String username) { // 옵션널
		User user = userRepository.findByUsername(username).orElseGet(()->{
			return new User();
		}); 
		return user; 
	}
	
	@Transactional
	public int 회원가입(User user) {
		String rawPassword = user.getPassword(); // 1234 원문
		String encPassword = encoder.encode(rawPassword); // 해쉬
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);	
		user.setRole(RoleType.USER);
		try {
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}
	@Transactional
	public void 회원수정(User user) {
		// 수정시에는 영속성 컨텍스트 User 오브젝트를 영속화 시키고, 영속화ㅗ딘 User 오브젝트를 수정
		// select 를 해서 User 오브젝트를 DB로부터 가져오는 이유는 영속화를 하기위해서!!
		// 영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려주거든요
		User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원 찾기 실패");
		});
		
		//Validate 체크  => oauth 필드에 값이 없으면 수정 가능  
		if(persistance.getOauth() == null || persistance.getOauth().equals("")) {
			String rawPassword = user.getPassword();
			String encPassword = encoder.encode(rawPassword);
			persistance.setPassword(encPassword);
			persistance.setEmail(user.getEmail());
		}


		// 회원수정 함수 종료시 = 서비스 종료 = 트랜잭션 종료 = commit이 자동으로 됩니다.
		// 영속화된 persistance 객체의 변화가 감지되면 더티 체킹이 되어 변화된 것을 update문을 날려줌 
		
	}
	
	// 시큐리티 사용전 
//	@Transactional(readOnly = true) // Select 할때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료 (정합성) 
//	public User 로그인(User user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
//	public int 회원가입(User user) {
//		try {
//			userRepository.save(user);
//			return 1;
//		}catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("UserService : 회원가입() : " + e.getMessage());
//		}
//		return -1;
//		//전체가 성공하면 커밋 
//		// 실패하면 롤백  많으면 하나하나 처리 해줘야함 
//	}
}
