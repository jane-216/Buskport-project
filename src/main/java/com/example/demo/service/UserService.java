package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.db.PostRepository;
import com.example.demo.db.UserRepository;
import com.example.demo.db.UserRewardRepository;
import com.example.demo.db.model.User;
import com.example.demo.model.UserCreateRequestDto;

import jakarta.transaction.Transactional;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRewardRepository userRewardRepository;
	
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
	
	@Transactional
	public void deleteUser(long userId) {
		// 사용자가 작성한 모든 게시글을 삭제한다.
		postRepository.deleteByUser_UserId(userId);
		// 사용자가 획득한 모든 reward 삭제한다.
		userRewardRepository.deleteByUser_User_Id(userId);
		
		userRepository.deleteById(userId);
	}
}
