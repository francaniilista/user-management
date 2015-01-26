package com.odesk.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.odesk.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	
}
