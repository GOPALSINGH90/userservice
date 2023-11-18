package com.springsecurity.userservice.repo;

import com.springsecurity.userservice.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<AppUser, Long> {

	AppUser findByUserName(String userName);


}
