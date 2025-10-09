package com.example.demo.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.db.model.UserReward;

@Repository
public interface UserRewardRepository extends JpaRepository<UserReward, Long> {
	List<UserReward> findByUser_User_Id(Long userId);
	int deleteByReward_Reward_Id(int rewardId);
}
