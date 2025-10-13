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

import com.example.demo.model.PostDto;
import com.example.demo.service.PostService;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	/**
	 * 게시글 단건 조회
	 * @param postId
	 * @return
	 */
	@GetMapping("/{postId}")
	ResponseEntity<?> getPost(@PathVariable long postId) {
		PostDto result = postService.getPost(postId);
		return ResponseEntity.ok(result);
	}
	
	/**
	 * 게시글 목록 조회
	 * @return
	 */
	@GetMapping("")
	ResponseEntity<?> getPosts() {
		List<PostDto> result = postService.getPosts();
		return ResponseEntity.ok(result);
	}
	
	/**
	 * 게시글 등록
	 * @param dto
	 * @return
	 */
	@PostMapping("/")
	ResponseEntity<?> addPost(@RequestBody PostDto dto) {
		postService.addPost(dto);
		return ResponseEntity.accepted().build();
	}
	
	/**
	 * 게시글 삭제
	 */
	@DeleteMapping("/{postId}")
	ResponseEntity<?> removePost(@PathVariable long postId, @AuthenticationPrincipal UserDetails userDetails) {
		// TODO 게시글 작성한 주인인지 확인
		postService.deletePost(postId);
		return ResponseEntity.noContent().build();
	}
}
