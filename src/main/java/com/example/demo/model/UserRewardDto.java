package com.example.demo.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserRewardDto {
	private Long userRewardId;
	private Integer rewardId;
	private Long userId;
	private LocalDateTime earnedAt;
}
