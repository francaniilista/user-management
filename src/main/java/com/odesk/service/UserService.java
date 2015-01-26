package com.odesk.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odesk.jpa.repository.UserRepository;
import com.odesk.model.User;

/**
 * User service class
 * @author paulo.franca
 *
 */
@Service
public class UserService {
	@Resource
	private UserRepository repository;
	
	@Transactional
	public User add(User user) {
		return repository.save(user);
	}
}
