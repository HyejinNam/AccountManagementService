package com.ams.jpa.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ams.jpa.entity.UserInfo;


public interface UserRepository extends JpaRepository<UserInfo, Long> {
	  Optional<UserInfo> findByEmail(String email);
}