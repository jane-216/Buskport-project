package com.example.demo.service;

import com.example.demo.model.SocialUserInfo;

public interface AuthService {
	String getAuthorizeUrl();
	SocialUserInfo getUserInfo(String authorizationCode);
}
