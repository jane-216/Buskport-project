package com.example.demo.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.db.model.UserReward;

@Repository
public interface UserRewardRepository extends JpaRepository<UserReward, Long> {
	List<UserReward> findByUser_Id(Long userId);
}
