package com.springsecurity.userservice.controller;
import com.springsecurity.userservice.UserserviceApplication;
import com.springsecurity.userservice.domain.AppUser;
import com.springsecurity.userservice.domain.Role;
import com.springsecurity.userservice.service.UserService;
import com.springsecurity.userservice.service.UserServiceImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	public UserService userService;
	
	
	@GetMapping("/users")
	public ResponseEntity<List<AppUser>> getUsers(){
		return ResponseEntity.ok().body(userService.getUsers());
	}

	@PostMapping("/user/save")
	public ResponseEntity<AppUser> saveUsers(@RequestBody AppUser appUser){
		URI uri =  URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/user/save").toUriString());
		return ResponseEntity.created(uri).body(userService.saveUser(appUser));
	}

	@PostMapping("/role/save")
	public ResponseEntity<Role> saveRoles(@RequestBody Role role){
		URI uri =  URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/role/save").toUriString());

		return ResponseEntity.created(uri).body(userService.saveRole(role));
	}

	@PostMapping("/role/addtouser")
	public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm roleToUserForm){
		URI uri =  URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/role/save").toUriString());

		userService.addRoleToUser(roleToUserForm.getUserName(), roleToUserForm.getRoleName());

		return  ResponseEntity.ok().build();
	}
}

@Data
class RoleToUserForm{
	private String userName;
	private String roleName;
}

