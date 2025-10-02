package com.example.demo.db.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    @Column(name="song_list")
    private String songList;

    @Column(name="promo_url")
    private String promoUrl;
    private LocalDateTime performanceDatetime;

    @Column(columnDefinition = "json", name="required_positions")
    private String requiredPositions; // JSON 타입

    @Column(nullable = false)
    private String status;

    @Column(name="chat_url")
    private String chatUrl;
    
    @CreationTimestamp
    @Column(name="created_at", updatable = false, insertable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name="updated_at", updatable = false, insertable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "performance")
    private List<PerformanceParticipant> participants;
}
