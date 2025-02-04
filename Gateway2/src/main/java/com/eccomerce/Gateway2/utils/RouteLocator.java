package com.eccomerce.Gateway2.utils;

import java.util.List;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouteLocator {

	    private static final List<String> openEndpoints = List.of("api/user/register","api/user/login","api/user/validateToken","/api/product/get");

	    public boolean isSecured(ServerHttpRequest request) {
	        return openEndpoints.stream().noneMatch(uri -> request.getURI().getPath().contains(uri));
	    
	}

}
