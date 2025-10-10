package com.example.demo.db;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.db.model.PerformanceParticipant;

@Repository
public interface PerformanceParticipantRepository extends JpaRepository<PerformanceParticipant, Long> {
	/**
     * performanceId를 기준으로 해당 공연의 모든 참여자 목록을 조회한다.
     */
    List<PerformanceParticipant> findByPerformance_PerformanceId(Long performanceId);
    
    /**
     * performanceId를 기준으로 해당 공연의 모든 참여자 목록을 삭제한다.
     */
    long deleteByPerformance_PerformanceId(Long performanceId);
    
    /**
     * 해당 사용자의 공연 참여 횟수를 조회한다.
     * @param userId
     * @return
     */
    int countByUser_UserId(long userId);
}
