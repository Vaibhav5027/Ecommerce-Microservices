package com.eccomerce.IdentityService.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.eccomerce.IdentityService.model.UserData;
import com.eccomerce.IdentityService.repository.UserRepo;

@Component
public class CustomUserDetailsService  implements UserDetailsService{
	
	@Autowired 
	UserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserData data = repo.findByEmail(username);
		if(data!=null) {
			 UserDetails convertUserToUserDetails = CustomUserDetails.convertUserToUserDetails(data);
			 return convertUserToUserDetails;
		}
		else {
			throw new RuntimeException( "User not Found");
		}
	}

}
