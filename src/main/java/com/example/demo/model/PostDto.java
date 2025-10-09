package com.example.demo.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PostDto {
	private Long postId;
	private Long userId;
	private String title;
	private String content;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
