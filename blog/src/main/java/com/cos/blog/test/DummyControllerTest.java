package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

// 리턴이 html파일이 아니라 data를 리턴해주는 controller = RestController
@RestController
public class DummyControllerTest {
	// 의존성 주입!! (DI) 
	@Autowired // DummyControllerTest 가 메모리에 뜰때 UserRepository 도 같이 띄워준다 
	private UserRepository userRepository ;
	
	// save 함수는 id를 전달하지 않으면 insert를 해주고
	//save 함수는 id를 전달하면 id에 대한 데이터가 있으면 update를 해주고 ,
	//save 함수는 id를 전달하면 id에 대한 데이터가 없으면 insert를 해요 
	//email , password
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {			
			userRepository.deleteById(id);  //그냥 하면 위험
		} catch (EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
		}
		return "삭제되었습니다.  id : " +id;
	}
	
	
	// json 데이터 로 받아서 테스트 
	@Transactional //함수 종료시 자동 commit이 됨 그니깐 함수 돌때만 트랜잭션 
	@PutMapping("/dummy/user/{id}")  // detail 과 같은데 알아서 구분함 
	public User updateUser(@PathVariable int id,  @RequestBody User requestUser) { 
		//json데이터를 요청 -> Java Object(MessageConverter의 Jackson 라이브러리가 변환해서 받아줘요. 필요 어노테이션 @RequestBody 
		System.out.println("id : " +id);
		System.out.println("password : "+ requestUser.getPassword());
		System.out.println("email : "+requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow( () -> {
			return new IllegalArgumentException("수정에 실해하였습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		
		//userRepository.save(user); 
		// save insert 할 때 데이터베이스에 있다면 업데이트 처리 그런데 다른 값들이 null 될 수 있다. 
		
		// 더티 체킹  @Transactional 
		
		return user;
	}
	
	// http://localhost:8000/blog/dummy/user
	@GetMapping("dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	// jsp나 기타 등등 로직을 다 짜야함 ...
	
	//한 페이지당 2건에 데이터를 리턴받아 볼 예정 
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUser = userRepository.findAll(pageable);
		
//		if(pagingUser.isFirst()) {  //pagingUser.isLast() 
//			
//		}
		List<User> users = pagingUser.getContent();
		return users;
	}
	
	//{id} 주소로 파라미터를 전달 받을 수 있음;
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// user/4을 찾으면 내가 데이터 베이스에서 못찾아오게되면 user가 null이 될것아냐?
		// 그럼 return null 이 리턴이 되자나.. 그럼 프로그램에 문제가 있지 않겠니?
		// Optional로 너의 User 객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return해 !!
		// 람다식
//		User user = userRepository.findById(id).orElseThrow(()-> {
//			return new IllegalArgumentException("해당 유저는 없습니다. ");
//		});
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			
			@Override
			public IllegalArgumentException get() {
	
				return new IllegalArgumentException("해당 유저는 없습니다.  ");
			}
		});
		// 요청 : 웹 브라우저
		// user 객체 = 자바 오브젝트 
		// 변환 ( 웹브라우저가 이해할 수 있는 데이터 ) -> json  (Gson 라이브러리 ) 자바오브젝트를 json으로 변경을 해서 던져줘야함
		// 스프링 부트 =  MessageConverter 라는 애가 응답시에 자동 작동
		// 만약에 자바 오브젝트를 리턴을 하게되면 MMessageConverter가 Jackson 라이브러리를 호출해서
		// user 오브젝트를 json으로 변환해서 브라우저에게 던져준다.  
		return user; 
	}
	
	// http://localhost:8000/blog/dummy/join (요청)
	//http 의 body 에 username , password, email 데이터를 가지고 (요청)
	@PostMapping("/dummy/join")
	// public String join(@RequestParam("username") String username , String password, String email) {
	public String join(User user) { // key = value (약속된 규칙)  form 태그 
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
