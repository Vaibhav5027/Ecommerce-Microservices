package com.cafemanagement.api_gateway.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafemanagement.api_gateway.model.User;

public interface UserRepo  extends JpaRepository<User, Integer> {

	User findByEmail(String email);

	User findByName(String string);

}
