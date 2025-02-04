package com.eccomerce.IdentityService.model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class AuthRequest {
private String email;
private String password;
}
