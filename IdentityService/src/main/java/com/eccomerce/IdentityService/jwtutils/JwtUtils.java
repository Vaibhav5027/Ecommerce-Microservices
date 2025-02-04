package com.eccomerce.IdentityService.jwtutils;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.eccomerce.IdentityService.config.CustomUserDetails;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtUtils {
	public  String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
	
	public String getTokenFronHeader(HttpServletRequest request) {
		  String header = request.getHeader("Authorization");
		  String token=null;
		  if(header!=null &&header.startsWith("Bearer")) {
			   token=header.substring(0,7);
			  return token;
		  }
		  return token;
	}
	 
	 public String getUserNameFromToken(String token) {
		return Jwts.parser().verifyWith(((SecretKey)key())).build().parseSignedClaims(token).getPayload().getSubject();
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
	 
	 public String generateToken(UserDetails userDetails,String email) {
			String username=userDetails.getUsername();
			String role = userDetails.getAuthorities().stream().map(authority->authority.getAuthority()).collect(Collectors.joining(","));
		return Jwts.builder().subject(username)
				.claim("roles", role)
				.claim("email", email)
				.issuedAt(new Date())
				.expiration(new Date(new Date().getTime()+17280000))
				.signWith(key())
				.compact();
		}

	private Key key() {
		
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
	}
	
}
