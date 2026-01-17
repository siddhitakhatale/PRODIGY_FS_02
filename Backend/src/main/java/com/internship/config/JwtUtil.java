package com.internship.config;

import java.security.Key;
import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	private final String SECRET_KEY="thisisaverysecuresecretkeyforjwtauth1234";
	private final long EXPIRATION_TIME=10000*60*60;
	private final Key key=Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	
	public String generateToken(String username,String role) {
		return Jwts.builder()
				.setSubject(username)
				.claim("role", role)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
				.signWith(key,SignatureAlgorithm.HS256)
				.compact();
	}
	public String extractUsername(String token) {
		return Jwts.parserBuilder().setSigningKey(key)
				.build().parseClaimsJws(token)
				.getBody().getSubject();
	}
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		}
		catch (JwtException e) {
			return false;
		}
	}
	public boolean validateToken(String token,UserDetails userDetails) {
		String username=extractUsername(token);
		return (username.equals(userDetails.getUsername()) && validateToken(token));
	}
	public String extractRole(String token) {
		return Jwts.parserBuilder().setSigningKey(key)
				.build().parseClaimsJws(token).getBody().get("role",String.class);
	}
}

