package com.example.demo.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class BookingStatsResponse {
    private long todayCount;
    private long monthCount;
    private List<PopularBoothDto> popularBooths;
}
