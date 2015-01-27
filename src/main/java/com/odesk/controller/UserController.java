package com.odesk.controller;

import javassist.NotFoundException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.odesk.model.User;
import com.odesk.model.dto.UserDTO;
import com.odesk.service.UserService;

/**
 * User controller class
 * @author paulo.franca
 *
 */
@Controller("userController")
@RequestMapping(value = "/rest/user")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Resource	
	private UserService service;

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public final User get(@PathVariable("id") final String id) throws NotFoundException {
		LOGGER.debug("Getting user with id: " + id);
		
		User found = service.findById(Long.valueOf(id));
		LOGGER.debug("Found user with id: " + id);
		
		return found; 
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public User create(@RequestBody UserDTO user) {
		LOGGER.debug("Creating user with information: " + user);
		
		User created = service.add(user);
		LOGGER.debug("User created with success.");
		
		return created;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void update(@RequestBody User user, @PathVariable String id) {
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void remove(@PathVariable String id) {
	}
}