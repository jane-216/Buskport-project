package com.example.demo.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.db.model.PerformanceParticipant;

@Repository
public interface PerformanceParticipantRepository extends JpaRepository<PerformanceParticipant, Long> {
}
