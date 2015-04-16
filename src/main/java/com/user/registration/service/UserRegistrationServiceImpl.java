package com.user.registration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.registration.dao.UserDao;
import com.user.registration.domain.User;

@Service("userRegistrationService")
public class UserRegistrationServiceImpl implements UserRegistrationService {

	@Autowired
	private UserDao userDao;

	public User findById(long id) {
		return userDao.findById(id);
	}

	public User findByName(String name) {
		return userDao.findByName(name);
	}

	public List<User> findAll() {
		return userDao.findAll();
	}

	public long save(User user) {
		return (Long)userDao.save(user);
	}

	public boolean update(User user) {
		return userDao.update(user);
	}

	public boolean delete(User user) {
		return userDao.delete(user);
	}

	public boolean deleteAll() {
		return userDao.deleteAll();
	}
	public void shutdown() {
		userDao.shutdown();
	}

}
