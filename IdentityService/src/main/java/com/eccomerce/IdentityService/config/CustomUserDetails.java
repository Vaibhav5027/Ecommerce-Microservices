package com.eccomerce.IdentityService.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.eccomerce.IdentityService.model.UserData;

public class CustomUserDetails implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	
	public CustomUserDetails(String name, String password, Collection<? extends GrantedAuthority> role) {
		this.username=name;
		this.password=password;
		this.authorities=role;
	}



	public static UserDetails  convertUserToUserDetails(UserData userData) {
		GrantedAuthority authority=new SimpleGrantedAuthority(userData.getRole());
		
		return new CustomUserDetails(userData.getName(),userData.getPassword(),Collections.singleton(authority));
	}
			
	
     
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

}
