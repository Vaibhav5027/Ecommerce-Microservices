package com.eccomerce.Gateway2.utils;

import java.security.Key;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
	public  String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

	public String extractUsernameFromToken(String token) {
		return Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(token).getPayload().getSubject();
		
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

     private Key key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
	}
	
}
