package com.example.demo.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReviewDto {
	private Long reviewId;
	private Long performanceId;
	private Long userId;
	private String title;
	private String content;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
