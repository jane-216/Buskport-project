package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.invokers.KakaotalkInvoker;
import com.example.demo.invokers.model.KakaoTalkToken;
import com.example.demo.model.AuthType;
import com.example.demo.service.AuthService;
import com.example.demo.service.AuthServiceFactory;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {
	
	@Autowired
	public AuthServiceFactory authServiceFactory;
	
	@Autowired
	public KakaotalkInvoker kakaotalkInvoker;
	
	@GetMapping("/")
	public String test() {
		return "hello";
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
