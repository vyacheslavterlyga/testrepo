package com.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.model.User;


@Repository
public class UserDAO implements UserDetailsService {
	@Autowired
	private SessionFactory sessionFactory;

	@Transactional(readOnly = false)
	public User addUser(User user) {
		sessionFactory.getCurrentSession().save(user);
		return user;
	}

	@Transactional(readOnly = true)
	public User getUserById(String id) {
		return (User) sessionFactory.getCurrentSession().createQuery("from com.model.User where id = '" + id + "'").uniqueResult();
	}

	@Transactional(readOnly = true)
	public List <User> getAllUsers() {
		return sessionFactory.getCurrentSession().createQuery("from com.model.User").list();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;
	}
}
