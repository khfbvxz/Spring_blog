package com.cos.authjwt.security.user;

import java.util.Map;

public class KakaoOAuth2UserInfo extends OAuth2UserInfo {
    public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
    	System.out.println("kakao getid");
        return String.valueOf(attributes.get("id"));
    }

    @Override
    public String getName() {
        Map<String, Object> propertiesObj = (Map<String, Object>) attributes.get("properties");
        //이름 가져올때는 다르게 
//        System.out.println("kakao getName"+ (String) propertiesObj.get("nickname"));
        return (String) propertiesObj.get("nickname");
    }

    @Override
    public String getEmail() {
    	Map<String, Object> kakaoacountObj = (Map<String, Object>) attributes.get("kakao_account");
    	System.out.println("KakaoOAuth2UserInfo getEmail "+(String) attributes.get("kaccount_email"));
        return (String) kakaoacountObj.get("email");
    }

    @Override
    public String getImageUrl() {
        Map<String, Object> propertiesObj = (Map<String, Object>) attributes.get("properties");
        return (String) propertiesObj.get("profile_image");
    }
}
