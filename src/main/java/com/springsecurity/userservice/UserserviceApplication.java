package com.springsecurity.userservice;

import com.springsecurity.userservice.domain.AppUser;
import com.springsecurity.userservice.domain.Role;
import com.springsecurity.userservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class UserserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {

			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			userService.saveUser(new AppUser(null, "Gopal Singh", "gopal", "1234", new ArrayList<>()));
			userService.saveUser(new AppUser(null, "Prisha Singh", "prisha", "1234", new ArrayList<>()));
			userService.saveUser(new AppUser(null, "Hari Singh", "hari", "1234", new ArrayList<>()));
			userService.saveUser(new AppUser(null, "Devendra Singh", "devendra", "1234", new ArrayList<>()));

			userService.addRoleToUser("gopal" , "ROLE_USER");
			userService.addRoleToUser("prisha" , "ROLE_MANAGER");
			userService.addRoleToUser("hari" , "ROLE_ADMIN");
			userService.addRoleToUser("devendra" , "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("devendra" , "ROLE_ADMIN");
			userService.addRoleToUser("devendra" , "ROLE_USER");


		};
	}

	@Bean
	BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

}
