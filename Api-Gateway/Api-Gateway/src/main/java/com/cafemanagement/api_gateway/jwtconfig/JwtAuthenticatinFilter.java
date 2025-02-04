package com.cafemanagement.api_gateway.jwtconfig;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cafemanagement.api_gateway.jwtutils.JwtUtils;
import com.cafemanagement.api_gateway.service.UserDetailsImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticatinFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	private UserDetailsImpl userDetails;
	public JwtAuthenticatinFilter() {
	}
	 

	public JwtAuthenticatinFilter(UserDetailsImpl userDetails, UserDetailsService userDetailsService) {
		this.userDetails = userDetails;
		this.userDetailsService = userDetailsService;
	};


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("{} inside jwt filter");
		System.out.println(request.getServletPath());
		if (request.getServletPath().matches("/api/user/login|/api/user/register|/api/user/forgotPassword|/api/product/get")) {
		    System.out.println(request.getServletPath());
		    filterChain.doFilter(request, response);
		}
		else {
			System.out.println("inside else");
			try {
				String jwt = jwtUtils.extractTokenFromHeader(request);
				if (jwt != null && jwtUtils.validateToken(jwt)) {
					String email = jwtUtils.getUsernameFromToken(jwt).trim();

					userDetailsService.loadUserByUsername(email);
					UserDetails userDetails = userDetailsService.loadUserByUsername(email);
				

					if (userDetails != null) {
						UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
								userDetails, null, userDetails.getAuthorities());
						authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(authentication);
						 

			}}} catch (Exception e) {
				e.printStackTrace();
			}
	
			filterChain.doFilter(request, response);
		}
		
	}}
	


