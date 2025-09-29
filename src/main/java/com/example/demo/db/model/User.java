package com.example.demo.db.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false)
    private String socialProvider;

    @Column(nullable = false)
    private String socialId;

    @Column(nullable = false, unique = true)
    private String nickname;

    private Integer age;
    private String gender;
    private String phoneNumber;
    private String activityRegion;
    private String preferredGenres;
    private String position;

    @Lob
    private String introduction;

    private String portfolioUrl;
    private String kakaotalkId;

    @Column(precision = 3, scale = 1)
    private BigDecimal mannerScore;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "organizer")
    private List<Performance> organizedPerformances;

    // Getter, Setter 생략...
}
