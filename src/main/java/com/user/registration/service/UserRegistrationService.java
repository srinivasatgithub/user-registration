package com.user.registration.service;

import java.util.List;

import com.user.registration.domain.User;

public interface UserRegistrationService {

	public User findById(long id);
	
	public User findByName(String name);

	public List<User> findAll();

	public long save(User user);

	public boolean update(User user);

	public boolean delete(User user);
	
	public boolean deleteAll();

	public void shutdown();
}
