package com.example.demo.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateRequestDto {
 private String socialProvider;
 private String socialId;
 private String nickname;
 private Integer age;
 private String gender;
 private String phoneNumber;
 private String activityRegion;
 private String preferredGenres;
 private String position;
 private String introduction;
 private String portfolioUrl;
 private String kakaotalkId;
}
