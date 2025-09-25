package com.example.demo.model;

import java.util.List;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class UserInfo {
	private String userId;
	private String password;
	private String name;
	private List<String> janr;
	
}
