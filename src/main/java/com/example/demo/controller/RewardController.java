package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.RewardDto;
import com.example.demo.model.UserRewardDto;
import com.example.demo.service.RewardService;

@RestController
@RequestMapping("/rewards")
public class RewardController {

	@Autowired
	private RewardService rewardService;
	
	@GetMapping("")
	ResponseEntity<?> getRewards() {
		List<RewardDto> result = rewardService.getRewards();
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/")
	ResponseEntity<?> addReward(@RequestBody RewardDto dto) {
		rewardService.addReward(dto);
		return ResponseEntity.ok(null);
	}
	
	@GetMapping("/users/{userId}")
	ResponseEntity<?> getUserRewards(@PathVariable long userId) {
		List<UserRewardDto> result = rewardService.getUserRewards(userId);
		return ResponseEntity.ok(result);
	}
}
