package com.cos.authjwt.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cos.authjwt.exception.BadRequestException;
import com.cos.authjwt.model.AuthProvider;
import com.cos.authjwt.model.User;
import com.cos.authjwt.payload.ApiResponse;
import com.cos.authjwt.payload.AuthResponse;
import com.cos.authjwt.payload.LoginRequest;
import com.cos.authjwt.payload.SignUpRequest;
import com.cos.authjwt.repository.UserRepository;
import com.cos.authjwt.security.TokenProvider;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProvider tokenProvider; // jwt utill 

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    	System.out.println("login "+loginRequest.getEmail());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );//new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
// userdetails 
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("loging 부분");
        String token = tokenProvider.createToken(authentication); // 토큰 생성하여 저장 
        System.out.println("token"+ token);
        System.out.println("login AuthResponse "+new AuthResponse(token));
        
//        return ResponseEntity.ok(new AuthResponse(token)); // 리스폰스 토큰 생성한 거랑 id name 이메일 같이 넘겨야함
        return ResponseEntity.ok().body(new AuthResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address already in use.");  // 이메일 중복 // 닉네임도 중복검사 해야될듯 
        }
        if (userRepository.existsByName(signUpRequest.getName())) {
			return ResponseEntity
					.badRequest()
					.body(new BadRequestException("Error: 이미있어 Username is already taken!"));
		}

        // Creating user's account
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setProvider(AuthProvider.local);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getId()).toUri();
        System.out.println("singup 부분");
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "User registered successfully@"));
    }

}
