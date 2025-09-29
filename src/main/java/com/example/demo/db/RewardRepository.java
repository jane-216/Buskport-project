package com.example.demo.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.db.model.Reward;

@Repository
public interface RewardRepository extends JpaRepository<Reward, Integer> {
}
