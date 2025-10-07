package com.example.demo.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.db.model.PerformanceParticipant;

@Repository
public interface PerformanceParticipantRepository extends JpaRepository<PerformanceParticipant, Long> {
	/**
     * performanceId를 기준으로 해당 공연의 모든 참여자 목록을 조회합니다.
     */
    List<PerformanceParticipant> findByPerformance_PerformanceId(Long performanceId);
    
    /**
     * performanceId를 기준으로 해당 공연의 모든 참여자 목록을 삭제합니다.
     */
    long deleteByPerformance_PerformanceId(Long performanceId);
}
