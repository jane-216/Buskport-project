package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.db.PerformanceParticipantRepository;
import com.example.demo.db.PerformanceRepository;
import com.example.demo.db.ReviewRepository;
import com.example.demo.db.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReviewService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PerformanceRepository performanceRepository;
	
	@Autowired
	private PerformanceParticipantRepository performanceParticipantRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;

}
