package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.db.UserRepository;
import com.example.demo.db.model.User;
import com.example.demo.model.UserCreateRequestDto;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public User getUser(long userId) {
		return userRepository.findById(userId).orElse(null);
	}
	
	public void addUser(UserCreateRequestDto requestDto) {
	    User user = new User();
	    user.setSocialProvider(requestDto.getSocialProvider());
	    user.setSocialId(requestDto.getSocialId());
	    user.setNickname(requestDto.getNickname());
	    user.setAge(requestDto.getAge());
	    user.setGender(requestDto.getGender());
	    user.setPhoneNumber(requestDto.getPhoneNumber());
	    user.setActivityRegion(requestDto.getActivityRegion());
	    user.setPreferredGenres(requestDto.getPreferredGenres());
	    user.setPosition(requestDto.getPosition());
	    user.setIntroduction(requestDto.getIntroduction());
	    user.setPortfolioUrl(requestDto.getPortfolioUrl());
	    user.setKakaotalkId(requestDto.getKakaotalkId());
	    
		userRepository.save(user);
	}
	
	public void deleteUser(long userId) {
		userRepository.deleteById(userId);
	}
}
