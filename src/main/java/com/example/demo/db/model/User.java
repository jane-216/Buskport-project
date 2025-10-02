package com.example.demo.db.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@NoArgsConstructor // JPA는 기본 생성자가 필요합니다.
@ToString(exclude = "organizedPerformances") // 무한 루프 방지를 위해 연관관계 필드는 제외
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false, name="social_provider")
    private String socialProvider;

    @Column(nullable = false, name="social_id")
    private String socialId;

    @Column(nullable = false, unique = true)
    private String nickname;

    private Integer age;
    private String gender;
    
    @Column(name="phone_number")
    private String phoneNumber;
    
    @Column(name="activity_region")
    private String activityRegion;
    
    @Column(name="preferred_genres")
    private String preferredGenres;
    private String position;

    @Lob
    private String introduction;

    @Column(name="portfolio_url")
    private String portfolioUrl;
    
    @Column(name="kakaotalk_id")
    private String kakaotalkId;

    @Column(precision = 3, scale = 1, name="manner_score")
    private BigDecimal mannerScore;

    @CreationTimestamp
    @Column(name="created_at", updatable = false, insertable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name="updated_at", updatable = false, insertable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "organizer")
    private List<Performance> organizedPerformances;
}
