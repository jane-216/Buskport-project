package com.example.demo.model;

import lombok.Data;

@Data
public class PerformanceParticipantDto {
	 private Long participantId;
	 private Long performanceId;
	 private Long userId;
	 private String position;
	 private String status;
}
