package com.springsecurity.userservice.service;

import com.springsecurity.userservice.domain.AppUser;
import com.springsecurity.userservice.domain.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
	
	AppUser saveUser(AppUser appUser);
	
	Role saveRole(Role role);
	
	void addRoleToUser(String userName, String roleName);
	
	AppUser getUser(String userName);
	
	List<AppUser> getUsers();

}