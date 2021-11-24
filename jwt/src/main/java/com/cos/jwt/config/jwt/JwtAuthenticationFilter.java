package com.cos.jwt.config.jwt;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cos.jwt.config.auth.PrincipalDetails;
import com.cos.jwt.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

// 스프링 시큐리티에서 UsernamePasswordAuthenticationFilter 가 있음 
// login 요청해서 username, password 전송하면 (post) 
// UsernamePasswordAuthenticationFilter 동작을 함 
// 로그인을 진행하는 필터기 때문에
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	private final AuthenticationManager authenticationManager;
	
	//authenticationManager 통해서 로그인 하게 되는데 그떄 실행되는 함수 attemptAuthentication
	// /login 요청을 하면 로그인 시도를 위해 실행되는 함수 
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		System.out.println("JwtAuthenticationFilter : 로그인 시도중 ");
		
		// 1. username, password 받아서
	
		try {
//			BufferedReader br = request.getReader();
//			String input = null;
//			while((input = br.readLine())!=null) {
//				System.out.println(input);
//			}
//			System.out.println(request.getInputStream().toString());
			ObjectMapper om = new ObjectMapper(); // json 데이터 파싱해주는 
			User user = om.readValue(request.getInputStream(), User.class);
			System.out.println(user);	
			//토큰 만듬 
			UsernamePasswordAuthenticationToken authenticationToken = 
					new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());	
			//로그인 시도
			//principalDetailsService의 loadUserByUsername() 함수가 실행된 후 정상이면 authentication이 리턴됨
			//DB 에 있는 username과 패스워드가 일치란다,
			//패스워드는 스프링이 알아서 처리해준다 DB에서 
			//authentication 내 로그인한 정보가 담김  
			Authentication authentication =
					authenticationManager.authenticate(authenticationToken);
			// => 로그인이 되었다는 뜻. 
			PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal(); // 오브젝트로 리턴 다운캐스팅
			System.out.println("로그인 완료됨 : "+principalDetails.getUser().getUsername()); // 로드인이 정상적으로 되었다는 뜻 
			//authentication 객체가 session 영역에 저장을 해야하고 그 방법이 return 해주면 됨 
			// 리턴의 권한 관리를 security가 대신 해주기 때문애 편하려고 하는 거임
			// 굳이 jwt 토큰을 사용하면서 세션을 만들 필요가 없음 , 근데 단지 권한 처리 때문에 session 넣어줍니다 

			return authentication; // 리턴되면 세션에 저장됨 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("==================================");
		return null;
		
	}
	// attemptAuthentication 실행 후 인증이 정상적으로 되었으면 successfulAuthentication 함수가 실행됨
	// JWT 토큰을 만들어서 request 요청한 사용자에게 JWT토큰을 reponse해주면 됨
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		System.out.println("successfulAuthentication 실행됨 : 인증이 완료되었다는 뜻 임 . ");
		PrincipalDetails principalDetails = (PrincipalDetails)authResult.getPrincipal();
		// JWT java-jwt
		//jwt 토큰 만들때 
		// .withSubject(principalDetails.getUsername())
		//withExpiresAt + 만료시간
		//withClaim 비공개 클레임  내가 넣고싶은 키 벨류 값을 막 넣어도 된다. 
		
		//.sign(Algorithm.HMAC512("cod")); 코스로 사인 
		
		//RSA 방식이 아닌 Hash암호 방식  HMAC512 특징 서버만 알고있는 시크릿 키 
		String jwtToken = JWT.create() 
				.withSubject("cos토큰")
				.withExpiresAt(new Date(System.currentTimeMillis()+(60000*10)))
				.withClaim("id", principalDetails.getUser().getId())
				.withClaim("username", principalDetails.getUser().getUsername())
				.sign(Algorithm.HMAC512("cos"));
		
		// Bearer 인증 
		response.addHeader("Authorization", "Bearer "+jwtToken);
//		super.successfulAuthentication(request, response, chain, authResult);
	}
//	public JwtAuthenticationFilter() {
//		
//	}
}
