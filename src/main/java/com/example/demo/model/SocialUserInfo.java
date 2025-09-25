package com.example.demo.model;

import lombok.Data;

@Data
public class SocialUserInfo {
	private AuthType socialType;
	private String socialId;
	private String email;
	private String nickName;
	private String gender;
	private String age;
	private String kakaotalkId;
}
