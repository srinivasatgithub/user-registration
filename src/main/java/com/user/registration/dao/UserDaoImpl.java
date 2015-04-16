package com.user.registration.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.user.registration.domain.User;

@Repository("userDao")
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	public User findById(long id) {
		return (User) getHibernateTemplate().get(User.class, id);
	}

	public User findByName(String name) {
		return (User)getHibernateTemplate().find("from com.user.registration.domain.User where name=?", name).get(0);
	}

	public List<User> findAll() {
		return (List<User>) getHibernateTemplate().find("from com.user.registration.domain.User");
	}

	public Serializable save(User user) {
		return getHibernateTemplate().save(user);
	}

	public boolean update(User user) {
		getHibernateTemplate().update(user);
		return true;
	}

	public boolean delete(User user) {
		getHibernateTemplate().delete(user);
		return true;
	}

	public boolean deleteAll() {
		getHibernateTemplate().deleteAll(findAll());
		return true;
	}
	public void shutdown() {
		getHibernateTemplate().getSessionFactory().openSession().createSQLQuery("SHUTDOWN").executeUpdate();
	}

	@Autowired
	public void init(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
}
