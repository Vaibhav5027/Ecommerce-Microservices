package com.cafemanagement.api_gateway.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cafemanagement.api_gateway.jwtutils.JwtUtils;
import com.cafemanagement.api_gateway.model.User;
import com.cafemanagement.api_gateway.repo.UserRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	AuthenticationManager authManager;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	UserDetailsServiceImpl serviceImpl;

	@Autowired
	UserDetailsImpl serviceImpl1;

	@Autowired
	PasswordEncoder passwordEncoder;

	public ResponseEntity<?> register(Map<String, Object> requestParam) {
		try {
			if (validateRequest(requestParam)) {
				User userFromRequest = UserFromRequest(requestParam);
//			     userFromRequest.setPassword();
				User save = userRepo.save(userFromRequest);
				return new ResponseEntity<>("{\"message\": \"User Registered Successfully\"}", HttpStatus.CREATED);
			} else {
//				return ";
				return new ResponseEntity<>("{\"message\": \"Invalid Data\"}", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	private User UserFromRequest(Map<String, Object> requestParam) {
		User user = new User();
		user.setEmail((String) requestParam.get("email"));
		user.setName((String) requestParam.get("name"));
		user.setPassword(passwordEncoder.encode((String) requestParam.get("password")));
		user.setRole((String) user.getRole());
		return user;
	}

	private boolean validateRequest(Map<String, Object> requestParam) {
		if (requestParam.containsKey("name") && requestParam.containsKey("email")
				&& requestParam.containsKey("password")) {
			return true;
		}

		else {
			return false;
		}
	}

	public ResponseEntity<?> login(Map<String, Object> requestParam) {
		try {

			User byEmail = userRepo.findByEmail((String) requestParam.get("email"));
			System.out.println(byEmail);
			 Authentication authenticate=null;
			if (byEmail != null) {
           try {
        	    authenticate = authManager.authenticate(new UsernamePasswordAuthenticationToken(
						(String) requestParam.get("email"), (String) requestParam.get("password")));
		     } catch (Exception e) {
			e.printStackTrace();
		          }
				
		
//			System.out.println(authenticate);
				if (authenticate.isAuthenticated()) {

					UserDetails userDetails = serviceImpl.loadUserByUsername((String) requestParam.get("email"));
					String token = jwtUtils.generateToken(userDetails);
					System.out.println(token);
					return new ResponseEntity<>("{\"token\":  \"" + token + "\"}", HttpStatus.OK);

				}
			} else {
				return new ResponseEntity<>("{\"message\":  \" invalid email  \"}", HttpStatus.BAD_REQUEST);
			}

		} 
		
		catch (Exception e) {
			return new ResponseEntity<>("{\"message\":  \"Password Incorrect  \"}", HttpStatus.BAD_REQUEST);
		}
		return null;
	

	}

}
