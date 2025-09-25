package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.invokers.KakaotalkInvoker;
import com.example.demo.model.SocialUserInfo;

@Service
public class KakaoTalkAuthService implements AuthService {
	
	@Autowired
	KakaotalkInvoker kakaotalkInvoker;

	@Override
	public String getAuthorizeUrl() {
		return kakaotalkInvoker.getAuthUrl();
	}

	@Override
	public SocialUserInfo getUserInfo(String authorizationCode) {
		// TODO Auto-generated method stub
		return null;
	}
}
