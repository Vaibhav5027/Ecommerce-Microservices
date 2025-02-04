package com.eccomerce.IdentityService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eccomerce.IdentityService.model.UserData;

public interface UserRepo extends JpaRepository<UserData,Integer>{

	UserData findByName(String username);

	UserData findByEmail(String username);

}
