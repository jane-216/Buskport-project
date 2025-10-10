package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.db.PerformanceParticipantRepository;
import com.example.demo.db.PostRepository;
import com.example.demo.db.RewardRepository;
import com.example.demo.db.UserRepository;
import com.example.demo.db.UserRewardRepository;
import com.example.demo.db.model.Reward;
import com.example.demo.db.model.RewardType;
import com.example.demo.db.model.User;
import com.example.demo.db.model.UserReward;
import com.example.demo.model.RewardDto;
import com.example.demo.model.UserRewardDto;

import jakarta.transaction.Transactional;

@Service
public class RewardService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RewardRepository rewardRepository;
	
	@Autowired
	private UserRewardRepository userRewardRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private PerformanceParticipantRepository performanceParticipantRepository;
	
	private static int POST_REWARD_10 = 10;
	private static int POST_REWARD_20 = 20;
	private static int PERFORMANCE_REWARD_10 = 10;
	private static int PERFORMANCE_REWARD_20 = 20;
	
	public List<RewardDto> getRewards() {
		List<Reward> entities = rewardRepository.findAll();
		return entities.stream().map(entity -> toDto(entity)).toList();
	}
	
	public void addReward(RewardDto dto) {
		rewardRepository.save(fromDto(dto));
	}
	
	public List<UserRewardDto> getUserRewards(long userId) {
		List<UserReward> entities = userRewardRepository.findByUser_User_Id(userId);
		return entities.stream().map(entity -> toDto(entity)).toList();
	}
	
	public void addUserReward(UserRewardDto dto) {
		userRewardRepository.save(fromDto(dto));
	}
	
	@Transactional
	public void deleteReward(int rewardId) {
		// user가 획득한 reward부터 삭제한다.
		userRewardRepository.deleteByReward_Reward_Id(rewardId);
		rewardRepository.deleteById(rewardId);
	}
	
	public void addUserPostReward(long userId) {
		int postCount = postRepository.countByUser_UserId(userId);
		RewardType rewardType = null;
		if (RewardType.POST_10_TIMES.getCount() == postCount) {
			rewardType = RewardType.POST_10_TIMES;
		} else if (RewardType.POST_20_TIMES.getCount() == postCount) {
			rewardType = RewardType.POST_20_TIMES;
		}
		
		if (rewardType == null) {
			return;
		}
		
		UserRewardDto dto = new UserRewardDto();
		dto.setUserId(userId);
		dto.setRewardId(rewardType.getRewardId());
		dto.setEarnedAt(LocalDateTime.now());
		
		addUserReward(dto);
	}
	
	public void addUserPerformanceReward(long userId) {
		int performanceRewardCount = performanceParticipantRepository.countByUser_UserId(userId);
		RewardType rewardType = null;
		if (RewardType.PERFORMANCE_10_TIMES.getCount() == performanceRewardCount) {
			rewardType = RewardType.PERFORMANCE_10_TIMES;
		} else if (RewardType.PERFORMANCE_20_TIMES.getCount() == performanceRewardCount) {
			rewardType = RewardType.PERFORMANCE_20_TIMES;
		}
		
		if (rewardType == null) {
			return;
		}
		
		UserRewardDto dto = new UserRewardDto();
		dto.setUserId(userId);
		dto.setRewardId(rewardType.getRewardId());
		dto.setEarnedAt(LocalDateTime.now());
		
		addUserReward(dto);
	}
	
	private RewardDto toDto(Reward entity) {
		RewardDto dto = new RewardDto();
		dto.setDescription(entity.getDescription());
		dto.setIconImageUrl(entity.getIconImageUrl());
		dto.setRewardId(entity.getRewardId());
		dto.setRewardName(entity.getRewardName());
		
		return dto;
	}
	
	private Reward fromDto(RewardDto dto) {
		Reward entity = new Reward();
		entity.setDescription(dto.getDescription());
		entity.setIconImageUrl(dto.getIconImageUrl());
		entity.setRewardId(dto.getRewardId());
		entity.setRewardName(dto.getRewardName());
		return entity;
	}
	
	private UserRewardDto toDto(UserReward entity) {
		UserRewardDto dto = new UserRewardDto();
		dto.setEarnedAt(entity.getEarnedAt());
		dto.setUserRewardId(entity.getUserRewardId());
		dto.setUserId(entity.getUser().getUserId());
		dto.setRewardId(entity.getReward().getRewardId());
		return dto;
	}
	
	private UserReward fromDto(UserRewardDto dto) {
		Reward reward = rewardRepository.findById(dto.getRewardId()).orElse(null);
		User user = userRepository.findById(dto.getUserId()).orElse(null);
		
		if (reward == null || user == null) {
			return null;
		}
		
		UserReward entity = new UserReward();
		entity.setEarnedAt(dto.getEarnedAt());
		entity.setReward(reward);
		entity.setUser(user);
		entity.setUserRewardId(dto.getUserRewardId());
		return entity;
		
	}
}
