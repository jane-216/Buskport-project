package com.example.demo.db.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "performances")
@Data
public class Performance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "performance_id")
    private Long performanceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id", nullable = false)
    private User organizer;

    @Column(nullable = false)
    private String title;

    @Lob
    private String songList;

    private String promoUrl;
    private LocalDateTime performanceDatetime;

    @Column(columnDefinition = "json")
    private String requiredPositions; // JSON 타입

    @Column(nullable = false)
    private String status;

    private String chatUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "performance")
    private List<PerformanceParticipant> participants;

    // Getter, Setter 생략...
}
