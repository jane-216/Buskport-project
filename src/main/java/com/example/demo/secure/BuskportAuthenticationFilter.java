package com.example.demo.secure;

import java.io.IOException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BuskportAuthenticationFilter extends OncePerRequestFilter{
	
	private UserDetailsService userDetailsService;

    private boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(JwtUtil.SECRET_KEY).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            // 토큰이 유효하지 않은 경우 (만료, 서명 불일치 등)
            return false;
        }
    }
    
    // JWT에서 사용자 정보(Claims) 추출
    private Claims getUserInfoFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(JwtUtil.SECRET_KEY).build().parseClaimsJws(token).getBody();
    }

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		
		String token = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (JwtUtil.COOKIE_NAME.equals(cookie.getName())) {
                   token = cookie.getValue();
                }
            }
        }
        
        if (token != null && validateToken(token)) {
        	String username = getUserInfoFromToken(token).getSubject();
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        
        filterChain.doFilter(request, response);
	}

}
