package com.user.registration.dao;

import java.io.Serializable;
import java.util.List;

import com.user.registration.domain.User;

public interface UserDao {

	public User findById(long id);

	public User findByName(String name);

	public List<User> findAll();

	public Serializable save(User user);

	public boolean update(User user);

	public boolean delete(User user);
	
	public boolean deleteAll();

	public void shutdown();
}
