package com.cos.blog3.controller;

// import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.cos.blog3.config.auth.PrincipalDetail;
import com.cos.blog3.model.KakaoProfile;
import com.cos.blog3.model.OAuthToken;
import com.cos.blog3.model.User;
import com.cos.blog3.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


//인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 허용
// 그냥 주소가 / 이면 index.jsp 허용
//static 이하에 있는 /js/** , /css/** , /image/** 허용


@Controller
public class UserController {
	
	

	@Value("${cos.key}")
	private String  cosKey;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	
	@GetMapping("auth/kakao/callback")
	public String kakaoCallback(String code) { // Data 를 리턴해주는 컨트롤러 함수
		
		// POST방식으로 key=value 데이터를 요청 (카카오쪽으로) 
		// 예전에는 HttpURLConnection 사용 
		//Retrofit2,   안드로이드에서 많이 쓰임 
		//OkHttp    RestTemplate   라이브러리 들   
		
		RestTemplate rt = new RestTemplate(); // http요청을 편하게 해줌 
		
		//HttpHeaders 오브젝트 생성 
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		// body data 받을 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code"); // value 값들은 변수로 만들어놓고 사용해라 
		params.add("client_id", "68a71cd25348a14a6a5da12dd5b301f8");
		params.add("redirect_uri", "http://localhost:8081/auth/kakao/callback");
		params.add("code", code);
		// HttpHeaders 와 httpBody를 하나의 오브젝트에 담기 
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = 
				new HttpEntity<>(params, headers);
		//Http 요청하기 -Post 방식으로 - 그리고 response 변수의 응답 받음. 
		ResponseEntity<String> response = rt.exchange(
					"https://kauth.kakao.com/oauth/token",
					HttpMethod.POST,
					kakaoTokenRequest,
					String.class
				) ;
		
		//Gson, Json Simple ObjectMapper 
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken=null;
		try {
			oauthToken = objectMapper .readValue(response.getBody(), OAuthToken.class);
		} catch(JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println("엑세스 토큰 : " + oauthToken.getAccess_token() );
		
		RestTemplate rt2 = new RestTemplate(); // http요청을 편하게 해줌 
		
		//HttpHeaders 오브젝트 생성 
	 	HttpHeaders headers2 = new HttpHeaders();
	 	headers2.add("Authorization", "Bearer "+ oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		// body data 받을 오브젝트 생성 
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = 
				new HttpEntity<>(headers2);
		//Http 요청하기 -Post 방식으로 - 그리고 response 변수의 응답 받음. 
		ResponseEntity<String> response2 = rt2.exchange(
					"https://kapi.kakao.com/v2/user/me",
					HttpMethod.POST,
					kakaoProfileRequest2,
					String.class
				) ;
		System.out.println(response2.getBody());
		
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile=null;
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch(JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		//user 오브젝트 : username password email 
		System.out.println("카캌오 아이디 (번호) : " + kakaoProfile.getId());
		System.out.println("카캌오 이메일 : " + kakaoProfile.getKakao_account().getEmail());
		
		System.out.println("블로그서버 유저네임 : "+ kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId());
		System.out.println("블로그서버 이메일 : " + kakaoProfile.getKakao_account().getEmail() );
		//UUID garbagePassword = UUID.randomUUID(); // 임시 패스워드 
		// UUID 란 중복되지않는 어떤 특정 값을 만들어내는 알고리즘 
		System.out.println("블로그서버 패스워드 : " + cosKey);
		
		//인코딩 회원가입 메소드에서 
		User kakaoUser = User.builder()
				.username( kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId())
				.password(cosKey)
				.email(kakaoProfile.getKakao_account().getEmail())
				.oauth("kakao")
				.build();
		
		// 가입자 혹은 비가입자 체크해서 처리 해야되 ,,, 
		User originUser =  userService.회원찾기(kakaoUser.getUsername());
		if(originUser.getUsername() == null ) {
			System.out.println("기존회원이 아니기에 자동 회원가입을 진행합니다. ");
			userService.회원가입(kakaoUser);
		}
		System.out.println("자동 로그인을 진행합니다.");
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		//로그인 처리 
		
		// response 응답이 스트링 데이터 응답으로 
		return "redirect:/";
	}
	
	@GetMapping("/user/updateForm")  //@AuthenticationPrincipal PrincipalDetail principal  Authentication 객체 들고옴 
	public String updateForm(@AuthenticationPrincipal PrincipalDetail principal) {
		return "user/updateForm";
	}
	
	
}
