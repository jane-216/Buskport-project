package com.example.demo.invokers;

import com.example.invokers.model.KmaUltraSrtNcstResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class KmaWeatherInvoker {

    private final RestTemplate restTemplate;

    @Value("${kma.ultra-srt-ncst-url}")
    private String ultraSrtNcstUrl;

    @Value("${kma.auth-key}")
    private String authKey;

    /**
     * @param baseDate yyyyMMdd (ex: 20251113)
     * @param baseTime HHmm   (ex: 0700)
     * @param nx       
     * @param ny       
     */
    public KmaUltraSrtNcstResponse getUltraShortNowcast(String baseDate,
                                                        String baseTime,
                                                        int nx,
                                                        int ny) {

        String uri = UriComponentsBuilder.fromHttpUrl(ultraSrtNcstUrl)
                .queryParam("pageNo", 1)
                .queryParam("numOfRows", 1000)
                .queryParam("dataType", "JSON")
                .queryParam("base_date", baseDate)
                .queryParam("base_time", baseTime)
                .queryParam("nx", nx)
                .queryParam("ny", ny)
                .queryParam("authKey", authKey)
                .toUriString();

        ResponseEntity<KmaUltraSrtNcstResponse> responseEntity =
                restTemplate.exchange(uri, HttpMethod.GET, null, KmaUltraSrtNcstResponse.class);

        return responseEntity.getBody();
    }
}
