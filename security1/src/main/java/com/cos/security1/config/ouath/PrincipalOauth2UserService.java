package com.cos.security1.config.ouath;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.cos.security1.config.auth.PrincipalDetails;
import com.cos.security1.config.ouath.provider.FacebookUserInfo;
import com.cos.security1.config.ouath.provider.GoogleUserInfo;
import com.cos.security1.config.ouath.provider.NaverUserInfo;
import com.cos.security1.config.ouath.provider.OAuth2UserInfo;
import com.cos.security1.model.User;
import com.cos.security1.repository.UserRepository;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService{

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserRepository userRepository;
	
	// 구글로 부터 받은 userRequest 데이터에 대한 후처리되는 함수    후처리는 loadUser
	// 함수 종료시 @ AuthenticationPrincipal 어노테이션이 만들어 진다 
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		System.out.println("getClientRegistration: "+userRequest.getClientRegistration()); // registrationId 로 어떤 OAuth로 로그인 했는지 확인가능.  
//		System.out.println("getAccessToken: "+userRequest.getAccessToken());
		System.out.println("getAccessToken: "+userRequest.getAccessToken().getTokenValue());
		
		
		
		OAuth2User oauth2User = super.loadUser(userRequest);
		// 구글로그인 버튼 클릭-> 구글로그인창 -> 로그인을 완료 -> code를 리턴(OAuth-Client 라이브러리) -> AccessToken요청
				// userRequest 정보 -> loadUser함수 호출 -> 구글로부터 회원프로필 받아준다.
//		System.out.println("getAttributes: "+super.loadUser(userRequest).getAttributes());
		System.out.println("getAttributes: "+oauth2User.getAttributes());
		
		// 회원가입을 강제로 진행해볼 예정 
		OAuth2UserInfo oAuth2UserInfo = null;
		if(userRequest.getClientRegistration().getRegistrationId().equals("google")) {
			System.out.println("구글 로그인 요청");
			oAuth2UserInfo = new GoogleUserInfo(oauth2User.getAttributes());
		}else if(userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {
			System.out.println("페이스북 로그인 요청");
			oAuth2UserInfo = new FacebookUserInfo(oauth2User.getAttributes());
		}else if(userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
			System.out.println("네이버 로그인 요청");
			oAuth2UserInfo = new NaverUserInfo((Map)oauth2User.getAttributes().get("response"));
		} else {
			System.out.println("우리는 구글과 페이스북, 네이버 만 지원해요 ㅎㅎㅎ");
		}
		String provider = oAuth2UserInfo.getProvider();
		String providerId = oAuth2UserInfo.getProviderId(); // null 
		String username = provider+"_"+providerId;
		String password = bCryptPasswordEncoder.encode("겟인데어");
		String email = oAuth2UserInfo.getEmail();
		String role = "ROLE_USER";
		
		User userEntity = userRepository.findByUsername(username);
		
		if(userEntity == null) {
			System.out.println("OAuth로그인이 최초입니다.");
			userEntity = User.builder()
					.email(email)
					.password(password)
					.provider(provider)
					.providerId(providerId)
					.role(role)
					.username(username)
					.build();
			userRepository.save(userEntity);
		} else {
			System.out.println("로그인을 이미 한적이 있습니다. 당신은 자동 회원가입이 되어 있습니다. ");
		}
		return new PrincipalDetails(userEntity, oauth2User.getAttributes()); // Authentication 객체 안에 들어간다 세션정보 안으로 들어간다  
		
	}
}
