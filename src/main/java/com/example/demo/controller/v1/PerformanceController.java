package com.example.demo.controller.v1;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.PerformanceDto;
import com.example.demo.model.PerformanceParticipantDto;
import com.example.demo.service.PerformanceService;

@RestController
@RequestMapping("/api/v1/performances")
public class PerformanceController {
	@Autowired
	private PerformanceService performanceService;
	
	/**
	 * 공연 목록 조회
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@GetMapping("")
	ResponseEntity<?> getPerformances(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
		List<PerformanceDto> performances = performanceService.getPerformances(startDate, endDate);
		return ResponseEntity.ok(performances);
	}
	
	/**
	 * 공연 단건 조회
	 * @param performanceId
	 * @return
	 */
	@GetMapping("/{performanceId}")
	ResponseEntity<?> getPerformance(@PathVariable long performanceId) {
		PerformanceDto result = performanceService.getPerformance(performanceId);
		return ResponseEntity.ok(result);
	}
	
	/**
	 * 공연 등록
	 * @param performance
	 * @return
	 */
	@PostMapping("/")
	ResponseEntity<?> addPerformance(@RequestBody PerformanceDto performance) {
		performanceService.addPerformance(performance);
		
		if (!CollectionUtils.isEmpty(performance.getParticipants())) {
			performanceService.addParticipants(performance.getParticipants());
		}
		return ResponseEntity.ok(performance);
	}
	
	/**
	 * 공연 정보 수정
	 * @param performance
	 * @return
	 */
	@PutMapping("/")
	ResponseEntity<?> updatePerpomance(@RequestBody PerformanceDto performance, @AuthenticationPrincipal UserDetails userDetails) {
		// TODO 공연주인지 확인
		performanceService.addPerformance(performance);
		
		if (!CollectionUtils.isEmpty(performance.getParticipants())) {
			performanceService.addParticipants(performance.getParticipants());
		}
		return ResponseEntity.ok(performance);
	}
	
	/**
	 * 공연 삭제
	 * @param performanceId
	 * @return
	 */
	@DeleteMapping("/{performanceId}")
	ResponseEntity<?> deletePerformance(@PathVariable long performanceId, @AuthenticationPrincipal UserDetails userDetails) {
		// TODO 공연주인지 확인
		performanceService.deletePerformance(performanceId);
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * 공연 참가자 등록
	 * @param participants
	 * @return
	 */
	@PostMapping("/{performanceId}/participants")
	ResponseEntity<?> addParticipants(@RequestBody List<PerformanceParticipantDto> participants) {
		if (!CollectionUtils.isEmpty(participants)) {
			performanceService.addParticipants(participants);
		}
		return ResponseEntity.ok(null);
	}

}
