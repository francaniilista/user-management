package com.odesk.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.odesk.jpa.repository.UserRepository;
import com.odesk.model.User;
import com.odesk.service.UserService;

/**
 * User controller class
 * @author paulo.franca
 *
 */
@Controller("userController")
@RequestMapping(value = "/rest/user")
public class UserController {
	@Resource	
	private UserService service;

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public final User get(@PathVariable("id") final String id) {
		User user = User.getBuilder("Paulo", "q1w2e3").build();
		return service.add(user);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public User create(@RequestBody User user) {
		return null;
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