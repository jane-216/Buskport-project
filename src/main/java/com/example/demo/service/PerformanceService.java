package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.db.PerformanceParticipantRepository;
import com.example.demo.db.PerformanceRepository;

@Service
public class PerformanceService {
	@Autowired
	private PerformanceRepository performanceRepository;
	
	@Autowired
	private PerformanceParticipantRepository performanceParticipantRepository;
	
	
	

}
