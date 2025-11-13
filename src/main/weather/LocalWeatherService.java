package com.buskport.weather.service;

import com.buskport.weather.invoker.KmaWeatherInvoker;
import com.buskport.weather.model.KmaUltraSrtNcstResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class LocalWeatherService {

    private final KmaWeatherInvoker kmaWeatherInvoker;

    private static final int DEFAULT_NX = 55;
    private static final int DEFAULT_NY = 127;

    public KmaUltraSrtNcstResponse getMyTownWeatherNow() {
        LocalDateTime now = LocalDateTime.now();

        String baseDate = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String baseTime = now.format(DateTimeFormatter.ofPattern("HHmm"));

        return kmaWeatherInvoker.getUltraShortNowcast(baseDate, baseTime, DEFAULT_NX, DEFAULT_NY);
    }
}
