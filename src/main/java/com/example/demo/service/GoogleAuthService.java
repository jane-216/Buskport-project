package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.SocialUserInfo;

@Service
public class GoogleAuthService implements AuthService {

	@Override
	public String getAuthorizeUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SocialUserInfo getUserInfo(String authorizationCode) {
		// TODO Auto-generated method stub
		return null;
	}

}
