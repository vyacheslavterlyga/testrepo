package com.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.model.User;

@Component
public class UserDAO {
	
	@Autowired
	private SessionFactory m_SessionFactory;
	
	@Transactional(readOnly = false)
	public User addUser(User user){
		m_SessionFactory.getCurrentSession().save(user);
		return user;		
	}
	
	@Transactional(readOnly = true)
	public User getUserById(String id){
		return (User)m_SessionFactory.getCurrentSession().createQuery("FROM AUSER WHERE id = " + id).uniqueResult();
	}
}
