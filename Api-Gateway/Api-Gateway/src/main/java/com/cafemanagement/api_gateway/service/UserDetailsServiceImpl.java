package com.cafemanagement.api_gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cafemanagement.api_gateway.model.User;
import com.cafemanagement.api_gateway.repo.UserRepo;

import jakarta.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	 @Autowired
	 private UserRepo userRepo;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		        User byName = userRepo.findByName("Vaibhav");
		     User user = userRepo.findByEmail(email);
		     System.out.println(user);
		return UserDetailsImpl.build(user);
	}
  
	
}
