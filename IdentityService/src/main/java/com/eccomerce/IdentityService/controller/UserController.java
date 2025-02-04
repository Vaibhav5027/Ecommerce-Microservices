package com.eccomerce.IdentityService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eccomerce.IdentityService.Service.AuthService;
import com.eccomerce.IdentityService.model.AuthRequest;
import com.eccomerce.IdentityService.model.UserData;



@RestController
@RequestMapping("/api/user")
public class UserController {
    
	@Autowired
  private AuthService authService;
	 
	@PostMapping("/register")
    public ResponseEntity<?> signUp(@RequestBody UserData data){
	   return authService.register(data) ;
			
    }
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthRequest request){
		return this.authService.login(request);
	}	
	
	@PostMapping("/validateToken")
	public ResponseEntity<?> validateToken(@RequestParam("token") String token){
		return this.authService.validateToken(token);
	}	
}
