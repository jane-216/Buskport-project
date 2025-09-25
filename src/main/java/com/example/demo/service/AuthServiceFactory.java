package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.AuthType;

@Service
public class AuthServiceFactory {
	
	@Autowired
	public KakaoTalkAuthService kakaoTalkAuthService;
	
	@Autowired
	public NaverAuthService naverAuthService;
	
	@Autowired
	public GoogleAuthService googleAuthService;
	
	@Autowired
	public SpaceAuthService spaceAuthService;
	
	public AuthService getService(AuthType socialType) {
		return switch (socialType) {
		case KAKAO_TALK -> kakaoTalkAuthService;
		case NAVER -> naverAuthService;
		case GOOGLE -> googleAuthService;
		case SPACE -> spaceAuthService;
		default -> null;
		};
	}
}
