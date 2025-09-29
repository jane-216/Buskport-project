package com.example.demo.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.db.model.Performance;
import com.example.demo.db.model.User;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Long> {
    // 특정 유저가 개설한 공연 목록 찾기 (예시)
    List<Performance> findByOrganizer(User user);
}
