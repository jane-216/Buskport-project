package com.example.demo.invokers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.invokers.model.KakaoTalkToken;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class KakaotalkInvoker {
	@Value("${auth.kakaotalk.domain}")
	private String authDomain;
	
	@Value("${auth.kakaotalk.client.id}")
	private String clientId;
	
	@Value("${auth.kakaotalk.redirect.uri}")
	private String redirectUri;
	
	private final String AUTH_API = "/oauth/authorize";
	private final String GET_TOKEN_API = "/oauth/token";
	
	private final RestClient restClient;
	
	public KakaotalkInvoker(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.baseUrl(authDomain).build();
    }
	
	public String getAuthUrl() {
        return UriComponentsBuilder.fromHttpUrl(authDomain)
        		.path(AUTH_API)
        		.queryParam("client_id", clientId)
        		.queryParam("redirect_uri", redirectUri)
        		.queryParam("response_type", "code")
        		.build()
        		.toUriString();
    }
	
	public boolean handleAuthorizationCallback(String code) {
        try {
        	KakaoTalkToken kakaoToken = getToken(code);
            if (kakaoToken != null) {
                saveAccessToken(kakaoToken.getAccess_token());
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
	
	public KakaoTalkToken getToken(String code) {
		MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
		formData.add("grant_type", "authorization_code");
		formData.add("client_id", clientId);
		formData.add("redirect_uri", redirectUri);
		formData.add("code", code);
		
		ResponseEntity<KakaoTalkToken> response = call(HttpMethod.POST, authDomain + GET_TOKEN_API, formData, KakaoTalkToken.class);
		return response.getBody();
		
	}
	
	public <T> ResponseEntity<T> call(HttpMethod method, String url, Object body, Class<T> responseType) {
		RestClient.RequestBodySpec requestSpec = restClient.method(method)
                .uri(url)
                .headers(headers -> headers.setBearerAuth(getAccessToken()));

        if (body != null) {
            requestSpec.contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(body);
        }
        try {
            return ResponseEntity.ok(requestSpec.retrieve().body(responseType));
        } catch (RestClientResponseException e) {

            // 에러 메시지 (응답 바디)
            String errorBody = e.getResponseBodyAsString();
            throw e;
        }
	}
	
	private HttpSession getSession() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession();
    }

    private void saveAccessToken(String accessToken) {
        getSession().setAttribute("access_token", accessToken);
    }

    private String getAccessToken() {
        return (String) getSession().getAttribute("access_token");
    }
	

}
