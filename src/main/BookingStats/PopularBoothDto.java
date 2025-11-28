package com.example.demo.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PopularBoothDto {
    private Long boothId;
    private String boothName;
    private long bookingCount;
}
