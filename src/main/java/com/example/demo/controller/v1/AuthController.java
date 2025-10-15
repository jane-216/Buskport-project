package com.example.demo.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.invokers.KakaotalkInvoker;
import com.example.demo.invokers.model.KakaoTalkToken;
import com.example.demo.model.AuthType;
import com.example.demo.model.LoginRequest;
import com.example.demo.secure.JwtUtil;
import com.example.demo.service.AuthService;
import com.example.demo.service.AuthServiceFactory;
import com.example.demo.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthController {
	
	@Autowired
	public AuthServiceFactory authServiceFactory;
	
	@Autowired
	public KakaotalkInvoker kakaotalkInvoker;
	
	@Autowired
	public UserService userService;
	
	@GetMapping("/")
	public String test() {
		return "hello";
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
		boolean loginResult = userService.login(loginRequest);
		if (!loginResult) {
			return ResponseEntity.status(401).build();
		}
		
		String token = JwtUtil.createToken(loginRequest.getUserId());
		// 토큰을 쿠키에 담아 클라이언트에 전달
        Cookie cookie = new Cookie(JwtUtil.COOKIE_NAME, token);
        cookie.setPath("/");
        response.addCookie(cookie);
        
        return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{authType}/login")
	public ResponseEntity<String> getSocialLoginUrl(@PathVariable AuthType authType) {
		AuthService authService = authServiceFactory.getService(authType);
		
		return ResponseEntity.ok(authService.getAuthorizeUrl());
	}
	
	@GetMapping("/{authType}/callback")
	public ResponseEntity<?> socialCallback(@PathVariable AuthType authType, @RequestParam String code) {
		AuthService authService = authServiceFactory.getService(authType);
		KakaoTalkToken token = kakaotalkInvoker.getToken(code);
		
		log.info("Get AccessToken!!");
		return ResponseEntity.ok(token);
	}
}
