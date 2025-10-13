package com.example.demo.controller.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.db.model.User;
import com.example.demo.model.PostDto;
import com.example.demo.model.UserCreateRequestDto;
import com.example.demo.model.UserRewardDto;
import com.example.demo.service.PostService;
import com.example.demo.service.RewardService;
import com.example.demo.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RewardService rewardService;
	
	@Autowired
	private PostService postService;
	
	/**
	 * 내 정보 조회
	 * @param userDetails
	 * @return
	 */
	@GetMapping("/me")
	public ResponseEntity<?> getMyUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
		long myId = Long.getLong(userDetails.getUsername());
		User result = userService.getUser(myId);
		return ResponseEntity.ok(result);
	}

	/**
	 * 타회원 조회
	 * @param userId
	 * @return
	 */
	@GetMapping("/{userId}")
	public ResponseEntity<?> getUsers(@PathVariable long userId) {
		User result = userService.getUser(userId);
		return ResponseEntity.ok(result);
	}
	
	/**
	 * 회원 탈퇴
	 * @param userDetails
	 * @return
	 */
	@DeleteMapping("/me")
	public ResponseEntity<?> deleteUser(@AuthenticationPrincipal UserDetails userDetails) {
		long myId = Long.getLong(userDetails.getUsername());
		userService.deleteUser(myId);
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * 회원 가입
	 * @param user
	 * @return
	 */
	@PostMapping()
	public ResponseEntity<?> postUsers(@RequestBody UserCreateRequestDto user) {
		userService.addUser(user);
		return ResponseEntity.accepted().build();
	}
	
	/**
	 * 나의 업적 조회
	 * @param userDetails
	 * @return
	 */
	@GetMapping("/me/rewards")
	public ResponseEntity<?> getRewards(@AuthenticationPrincipal UserDetails userDetails) {
		long myId = Long.getLong(userDetails.getUsername());
		List<UserRewardDto> result = rewardService.getUserRewards(myId);
		return ResponseEntity.ok(result);
	}
	
	/**
	 * 타유저 업적 조회
	 * @param userDetails
	 * @return
	 */
	@GetMapping("/{userId}/rewards")
	public ResponseEntity<?> getRewards(@PathVariable long userId) {
		List<UserRewardDto> result = rewardService.getUserRewards(userId);
		return ResponseEntity.ok(result);
	}
	
	/**
	 * 내가 작성한 게시글 조회
	 * @param userId
	 * @return
	 */
	@GetMapping("/me/posts")
	ResponseEntity<?> getPostsByUserId(@AuthenticationPrincipal UserDetails userDetails) {
		// TODO 페이징
		long myId = Long.getLong(userDetails.getUsername());
		List<PostDto> result = postService.getPostsByUserId(myId);
		return ResponseEntity.ok(result);
	}
}
