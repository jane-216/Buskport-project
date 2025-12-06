package com.example.demo.secure;


import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {
	static public final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	static private final long expirationTime = 3600 * 1000L; // 1시간
    static public final String COOKIE_NAME = "Auth-Token"; 	 // 쿠키 이름
	
	static public String createToken(String username) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expirationTime))
                .signWith(SECRET_KEY)
                .compact();
    }
}
