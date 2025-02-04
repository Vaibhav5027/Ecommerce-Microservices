package com.eccomerce.IdentityService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.eccomerce.IdentityService.config.CustomUserDetailsService;
import com.eccomerce.IdentityService.jwtutils.JwtUtils;
import com.eccomerce.IdentityService.model.AuthRequest;
import com.eccomerce.IdentityService.model.UserData;
import com.eccomerce.IdentityService.repository.UserRepo;



@Component
public class AuthService {
	@Autowired
	private UserRepo repo;
	@Autowired
	private PasswordEncoder passEncoder;
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	AuthenticationManager authManager;
	public ResponseEntity<?> register(UserData userData) {
		 
		UserData byEmail = repo.findByEmail(userData.getEmail());
		if(byEmail==null ) {
			String encodePassword = passEncoder.encode(userData.getPassword());
			userData.setPassword(encodePassword);
			UserData save = repo.save(userData);
			if(save!=null) {
				return new ResponseEntity<>("{\"message\": \"User Registered Successfully\"}", HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>("{\"message\": \"Invalid Data\"}", HttpStatus.BAD_REQUEST);
			}
		}
		else {
			return new ResponseEntity<>("{\"message\": \"User Already Registered\"}", HttpStatus.BAD_REQUEST);
		}
		
	
	
	}
	public ResponseEntity<?> login(AuthRequest requestParam) {
		try {
			Authentication authenticate = authManager.authenticate(new UsernamePasswordAuthenticationToken(requestParam.getEmail(), requestParam.getPassword()));
		    if(authenticate.isAuthenticated()) {
		    	UserDetails userByUsername = customUserDetailsService.loadUserByUsername(requestParam.getEmail());
		    	String token = jwtUtils.generateToken(userByUsername,requestParam.getEmail());
		    	System.out.println(token);
		    	return new ResponseEntity<>("{\"token\":  \"" + token + "\"}", HttpStatus.OK);
		    }
			
		} catch (Exception e) {
		   e.printStackTrace();

		}
		 return new ResponseEntity<>("{\"message\":  \"Invalid Data \"}", HttpStatus.BAD_REQUEST); 
		
	}
	public ResponseEntity<?> validateToken(String token) {
		try {
			 boolean validateToken = jwtUtils.validateToken(token);
			    if(validateToken) {
			    	 return new ResponseEntity<>("{\"message\":  \"valid token \"}", HttpStatus.OK);
			    }
		} catch (Exception e) {
			 return new ResponseEntity<>("{\"message\":  \"Invalid token \"}", HttpStatus.OK);
			
		}
		  return null; 
		
		
	}
	
	
}
