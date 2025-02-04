package com.eccomerce.Gateway2.utils;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

	private JwtUtils jwtUtils;
	private RouteLocator routeLocator;

	public AuthenticationFilter(JwtUtils jwtUtils, RouteLocator routeLocator) {
		super(Config.class);
		this.jwtUtils = jwtUtils;
		this.routeLocator = routeLocator;
	}

	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain) -> {
			ServerHttpRequest request = exchange.getRequest();
			if (routeLocator.isSecured(request)) {
				if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					throw new RuntimeException("Missing Authorization Header");
				}

				String authHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				if (authHeader != null && authHeader.startsWith("Bearer ")) {
					authHeader = authHeader.substring(7);
				}
				try {
					if (jwtUtils.validateToken(authHeader)) {
						String usernameFromToken = jwtUtils.extractUsernameFromToken(authHeader);
						request.mutate().header("LoggedInUser", usernameFromToken);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
			return chain.filter(exchange);
		});

	}

	public static class Config {

	}
}
