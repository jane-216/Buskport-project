package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.PerformanceDto;
import com.example.demo.model.PerformanceParticipantDto;
import com.example.demo.service.PerformanceService;

@RestController
@RequestMapping("/performances")
public class PerformanceController {
	@Autowired
	private PerformanceService performanceService;
	
	@GetMapping("/{performanceId}")
	ResponseEntity<?> getPerformance(@PathVariable long performanceId) {
		PerformanceDto result = performanceService.getPerformance(performanceId);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/")
	ResponseEntity<?> addPerformance(@RequestBody PerformanceDto performance) {
		performanceService.addPerformance(performance);
		
		if (!CollectionUtils.isEmpty(performance.getParticipants())) {
			performanceService.addParticipants(performance.getParticipants());
		}
		return ResponseEntity.ok(performance);
	}
	
	@PostMapping("/{performanceId}/")
	ResponseEntity<?> addParticipants(@RequestBody List<PerformanceParticipantDto> participants) {
		if (!CollectionUtils.isEmpty(participants)) {
			performanceService.addParticipants(participants);
		}
		return ResponseEntity.ok(null);
	}

}
