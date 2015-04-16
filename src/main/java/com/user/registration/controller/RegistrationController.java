package com.user.registration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.user.registration.domain.User;
import com.user.registration.service.UserRegistrationService;

@Controller
@RequestMapping("/users")
public class RegistrationController {

	@Autowired
	private UserRegistrationService userRegistrationService;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public List<User> getAllUsers() {
		List<User> users = userRegistrationService.findAll();
		return users;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public User getUser(@PathVariable Long id) {
		User user = userRegistrationService.findById(id);
		return user;
	}


	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Long createUser(@RequestBody User user) {
		long id = userRegistrationService.save(user);
		return id;
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public boolean updateUser(@RequestBody User user) {
		User user4update = userRegistrationService.findByName(user.getName());
		user4update.setEmail(user.getEmail());
		return userRegistrationService.update(user4update);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public boolean deleteUser(@PathVariable Long id) {
		User user = userRegistrationService.findById(id);
		return userRegistrationService.delete(user);
	}
	

}
