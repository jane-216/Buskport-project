package com.example.demo.db.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "rewards")
@Data
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reward_id")
    private Integer rewardId;

    @Column(nullable = false, name="reward_name")
    private String rewardName;

    @Column(nullable = false)
    private String description;

    @Column(name="icon_image_url")
    private String iconImageUrl;
}
