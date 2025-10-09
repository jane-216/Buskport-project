package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/posts")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@GetMapping("/{postId}")
	ResponseEntity<?> getPost(@PathVariable long postId) {
		PostDto result = postService.getPost(postId);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/")
	ResponseEntity<?> addPost(@RequestBody PostDto dto) {
		postService.addPost(dto);
		return ResponseEntity.accepted().build();
	}
	
	@DeleteMapping("/{postId}")
	ResponseEntity<?> removePost(@PathVariable long postId) {
		postService.deletePost(postId);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/")
	ResponseEntity<?> getPostsByUserId(@RequestParam long userId) {
		List<PostDto> result = postService.getPostsByUserId(userId);
		return ResponseEntity.ok(result);
	}
}
