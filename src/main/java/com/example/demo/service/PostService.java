package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.db.PostRepository;
import com.example.demo.db.UserRepository;
import com.example.demo.db.model.Post;
import com.example.demo.db.model.User;
import com.example.demo.model.PostDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public PostDto getPost(Long postId) {
		Post entity = postRepository.findById(postId).orElse(null);
		if (entity == null) {
			return null;
		}
		
		return toDto(entity);
	}
	
	public void addPost(PostDto postDto) {
		Post entity = fromDto(postDto);
		postRepository.save(entity);
	}
	
	public void deletePost(Long postId) {
		postRepository.deleteById(postId);
	}
	
	public List<PostDto> getPostsByUserId(Long userId) {
		List<Post> posts = postRepository.findByUser_UserId(userId);
		return posts.stream().map(entity -> toDto(entity)).toList();
	}
	
	private PostDto toDto(Post entity) {
		PostDto dto = new PostDto();
		dto.setPostId(entity.getPostId());
		dto.setUserId(entity.getUser().getUserId());
		dto.setTitle(entity.getTitle());
		dto.setContent(entity.getContent());
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setUpdatedAt(entity.getUpdatedAt());
		
		return dto;
	}
	
	private Post fromDto(PostDto dto) {
		User userEntity = userRepository.findById(dto.getUserId()).orElse(null);
		
		if (userEntity == null) {
			return null;
		}
		Post entity = new Post();
		entity.setPostId(dto.getPostId());
		entity.setUser(userEntity);
		entity.setTitle(dto.getTitle());
		entity.setContent(dto.getContent());
		entity.setCreatedAt(dto.getCreatedAt());
		entity.setUpdatedAt(dto.getUpdatedAt());
		return entity;
	}

}
