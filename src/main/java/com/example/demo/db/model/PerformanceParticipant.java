package com.example.demo.db.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "performance_participants")
@Data
public class PerformanceParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participant_id")
    private Long participantId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance_id", nullable = false)
    private Performance performance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private String status;
}
