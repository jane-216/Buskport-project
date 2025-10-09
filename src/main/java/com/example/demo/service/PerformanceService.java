package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.db.PerformanceParticipantRepository;
import com.example.demo.db.PerformanceRepository;
import com.example.demo.db.UserRepository;
import com.example.demo.db.model.Performance;
import com.example.demo.db.model.PerformanceParticipant;
import com.example.demo.db.model.User;
import com.example.demo.model.PerformanceDto;
import com.example.demo.model.PerformanceParticipantDto;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PerformanceService {
	@Autowired
	private PerformanceRepository performanceRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PerformanceParticipantRepository performanceParticipantRepository;
	
	public PerformanceDto getPerformance(long performanceId) {
		Performance performance = performanceRepository.findById(performanceId).orElse(null);
		if (performance == null) {
			return null;
		}
		
		PerformanceDto dto = toDto(performance);
		List<PerformanceParticipant> participiants = performanceParticipantRepository.findByPerformance_PerformanceId(performanceId);
		List<PerformanceParticipantDto> participiantDtos = participiants.stream().map(entity -> toDto(entity)).toList();
		dto.setParticipants(participiantDtos);
		return dto;
	}
	
	public List<PerformanceDto> getPerformances(LocalDate startDate, LocalDate endDate) {
		List<Performance> performances = performanceRepository.findByPerformanceDate(startDate, endDate);
		return performances.stream().map(p -> toDto(p)).toList();
	}
	
	public void addPerformance(PerformanceDto performance) {
		Performance entity = fromDto(performance);
		performanceRepository.save(entity);
	}
	
	@Transactional
	public void updatePerformance(PerformanceDto performance) {
		Performance originEntity = performanceRepository.findById(performance.getPerformanceId()).orElse(null);
		if (originEntity == null) {
			log.error("NOT EXIST PERFORMANCE");
			return;
		}
		
		originEntity.setTitle(performance.getTitle());
		originEntity.setChatUrl(performance.getChatUrl());
		originEntity.setSongList(performance.getSongList());
		originEntity.setPerformanceDatetime(performance.getPerformanceDatetime());
		originEntity.setPromoUrl(performance.getPromoUrl());
	}
	
	@Transactional
	public void deletePerformance(Long performanceId) {
		performanceParticipantRepository.deleteByPerformance_PerformanceId(performanceId);
		performanceRepository.deleteById(performanceId);
	}
	
	public void addParticipants(List<PerformanceParticipantDto> dtoes) {
		List<PerformanceParticipant> entities = dtoes.stream().map(dto -> fromDto(dto)).toList();
		performanceParticipantRepository.saveAll(entities);
	}
	
	private PerformanceDto toDto(Performance performance) {
		PerformanceDto performanceDto = new PerformanceDto();
		performanceDto.setPerformanceId(performance.getPerformanceId());
		performanceDto.setOrganizer(performance.getOrganizer().getUserId());
		performanceDto.setTitle(performance.getTitle());
		performanceDto.setSongList(performance.getSongList());
		performanceDto.setPromoUrl(performance.getPromoUrl());
		performanceDto.setPerformanceDatetime(performance.getPerformanceDatetime());
		performanceDto.setRequiredPositions(performance.getRequiredPositions());
		performanceDto.setStatus(performance.getStatus());
		performanceDto.setChatUrl(performance.getChatUrl());
		
		return performanceDto;
	}
	
	private PerformanceParticipantDto toDto(PerformanceParticipant entity) {
		PerformanceParticipantDto dto = new PerformanceParticipantDto();
		dto.setParticipantId(entity.getParticipantId());
		dto.setPerformanceId(entity.getPerformance().getPerformanceId());
		dto.setPosition(entity.getPosition());
		dto.setStatus(entity.getStatus());
		dto.setUserId(entity.getUser().getUserId());
		return dto;
	}
	
	private Performance fromDto(PerformanceDto dto) {
		User user = userRepository.findById(dto.getOrganizer()).orElse(null);
		
		Performance performance = new Performance();
		performance.setPerformanceId(dto.getPerformanceId());
		// organizer가 null인 경우는 해당 유저가 탈퇴한 경우이다.
		performance.setOrganizer(user);
		performance.setTitle(dto.getTitle());
		performance.setSongList(dto.getSongList());
		performance.setPromoUrl(dto.getPromoUrl());
		performance.setPerformanceDatetime(dto.getPerformanceDatetime());
		performance.setRequiredPositions(dto.getRequiredPositions());
		performance.setStatus(dto.getStatus());
		performance.setChatUrl(dto.getChatUrl());
		return performance;
	}
	
	private PerformanceParticipant fromDto(PerformanceParticipantDto dto) {
		Performance performanceEntity = performanceRepository.findById(dto.getParticipantId()).orElse(null);
		User userEntity = userRepository.findById(dto.getUserId()).orElse(null);
		
		if (performanceEntity == null) {
			return null;
		}
		
		PerformanceParticipant entity = new PerformanceParticipant();
		entity.setParticipantId(dto.getParticipantId());
		entity.setPerformance(performanceEntity);
		entity.setPosition(dto.getPosition());
		// user가 탈퇴한 경우 null일 수 있다.
		entity.setUser(userEntity);
		return entity;
	}
}
