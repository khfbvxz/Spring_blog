package com.cos.authjwt.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.cos.authjwt.config.AppProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

// JWT UTIl 

@Service
public class TokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

//	@Value("${bezkoder.app.jwtSecret}")
//	private String jwtSecret;
//
//	@Value("${bezkoder.app.jwtExpirationMs}")
//	private int jwtExpirationMs;
    private AppProperties appProperties;

    public TokenProvider(AppProperties appProperties) {
        this.appProperties = appProperties;
    }
// 토큰 생성. 
    public String createToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec()); //tokenExpirationMsec: 864000000

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
                .compact();
    }
// 
//    public String getUserNameFromJwtToken(String token) {
//		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
//	}
    //
    public Long getUserIdFromToken(String token) {  
        Claims claims = Jwts.parser()
                .setSigningKey(appProperties.getAuth().getTokenSecret())
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }
// 
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature", ex.getMessage());
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token" ,ex.getMessage());
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token",ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token",ex.getMessage());
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.",ex.getMessage());
        }
        return false;
    }

}

