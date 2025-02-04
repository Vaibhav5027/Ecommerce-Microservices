package com.cafemanagement.api_gateway.jwtutils;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.cafemanagement.api_gateway.service.UserDetailsImpl;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtUtils {
	
	private String secreKey="8591144c7acbdff1c80b697ec0a00b5780cc783e7924206bd39de946c1d1e3540db7446381fdb164a72bb34c79375d7b3f152a461a050b2cf9f3daef60b1b58f";
	public String extractTokenFromHeader(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");

		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			String substring = bearerToken.substring(7);
			return substring;
		} else
			return null;
	}
	
	
	public String generateToken(UserDetails userDetails) {
		String username=userDetails.getUsername();
		String role = userDetails.getAuthorities().stream().map(authority->authority.getAuthority()).collect(Collectors.joining(","));
	return Jwts.builder().subject(username)
			.claim("roles", role)
			.issuedAt(new Date())
			.expiration(new Date(new Date().getTime()+17280000))
			.signWith(key())
			.compact();
	}

  public String getUsernameFromToken(String token) {
	  return Jwts.parser().verifyWith((SecretKey) key()).build().
			  parseSignedClaims(token).getPayload().getSubject();
  }
	private Key key() {
		
		
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secreKey));
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().verifyWith((SecretKey) key()).build().
			  parseSignedClaims(token);
			return true;
		}catch (JwtException e) {
			throw new JwtException(e.getMessage());
		}
	}
}
