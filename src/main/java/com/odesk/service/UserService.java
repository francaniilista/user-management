package com.odesk.service;

import javassist.NotFoundException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odesk.jpa.repository.UserRepository;
import com.odesk.model.User;
import com.odesk.model.dto.UserDTO;

/**
 * User service class
 * @author paulo.franca
 *
 */
@Service
public class UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
	@Resource
	private UserRepository repository;
	
	@Transactional
	public User add(UserDTO added) {
		LOGGER.debug("Adding new user", added);
		
		User user = User.getBuilder(added.getUsername(), added.getPassword())
					.email(added.getEmail())
					.groups(added.getGroups())
					.build();

        return repository.save(user);
	}
	
	@Transactional(readOnly = true)
	public User findById(Long id) throws NotFoundException {
		LOGGER.debug("Finding user by id: ", id);
		
		User user = repository.findOne(id);
		
		if (user == null) {
			LOGGER.debug("No user found with id: ", id);
            throw new NotFoundException("No user found with id: " + id);
		}
		
		LOGGER.debug("Found user: " + user);

		return user;
	}
	
	@Transactional(rollbackFor = NotFoundException.class)
	public User update(UserDTO updated) throws NotFoundException {
		LOGGER.debug("Updating user: ", updated);
		
		User found = repository.findOne(updated.getId());
		
		if (found == null) {
            LOGGER.debug("No user found with id: ", updated.getId());
            throw new NotFoundException("No user found with id: " + updated.getId());
        }
		
		found.update(updated.getUsername(), updated.getPassword());
		found.updateEmail(updated.getEmail());
		
		return found;

	}
	
	@Transactional(rollbackFor = NotFoundException.class)
	public User deleteById(Long id) throws NotFoundException {
		LOGGER.debug("Deleting user by id: ", id);
		
		User deleted = findById(id);
		repository.delete(deleted);
		
		LOGGER.debug("User deleted: ", id);
		
		return deleted;
	}
}
