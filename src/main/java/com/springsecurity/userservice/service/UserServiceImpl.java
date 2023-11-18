package com.springsecurity.userservice.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.springsecurity.userservice.domain.AppUser;
import com.springsecurity.userservice.domain.Role;
import com.springsecurity.userservice.repo.RoleRepo;
import com.springsecurity.userservice.repo.UserRepo;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;



@Service @RequiredArgsConstructor @Transactional
public class UserServiceImpl  implements UserService, UserDetailsService {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	 RoleRepo roleRepo;

	private final PasswordEncoder passwordEncoder;

	
	@Override
	public AppUser saveUser(AppUser appUser) {
//		logger.info("saving User to new database");
		appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
		return userRepo.save(appUser);
	}

	@Override
	public Role saveRole(Role role) {
		//logger.info("saving Role  {} to new database", role.getName());

		return roleRepo.save(role);
	}

	@Override
	public void addRoleToUser(String userName, String roleName) {
		
	//	logger.info("adding new Role  {} to user {} new database", roleName, userName);

		AppUser appUser =  userRepo.findByUserName(userName);
		Role role =  roleRepo.findByName(roleName);
		appUser.getRoles().add(role);
	}

	@Override
	public AppUser getUser(String userName) {
	//	logger.info("fetching user to new database");

		// TODO Auto-generated method stub
		return userRepo.findByUserName(userName);
	}

	@Override
	public List<AppUser> getUsers() {
		//logger.info("fetching user ");

		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser appUser =  userRepo.findByUserName(username);
		if(username == null){
			System.out.println("User not found");
			throw new UsernameNotFoundException("User not found the database");
		}

		Collection<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
		appUser.getRoles().forEach(role -> {
			simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		});
		return  new User(appUser.getUserName(), appUser.getPassword(), simpleGrantedAuthorities);

	}
}