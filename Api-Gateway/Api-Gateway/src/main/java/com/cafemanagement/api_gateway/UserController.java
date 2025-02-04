package com.cafemanagement.api_gateway;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafemanagement.api_gateway.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
    
	@Autowired
	private UserService userService;
	 
	@PostMapping("/register")
    public ResponseEntity<?> signUp(@RequestBody Map<String,Object> requestParam){
	   return userService.register(requestParam) ;
			
    }
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String,Object> requestParam){
		return this.userService.login(requestParam);
	}	
}
