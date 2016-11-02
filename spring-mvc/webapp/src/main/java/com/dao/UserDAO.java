package com.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.model.User;

@Repository
public class UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional(readOnly = false)
	public User addUser(User user){
		sessionFactory.getCurrentSession().save(user);
		return user;		
	}
	
	@Transactional(readOnly = true)
	public User getUserById(String id){
		return (User)sessionFactory.getCurrentSession().createQuery("from com.model.User where id = '" + id + "'").uniqueResult();
	}
	
	@Transactional(readOnly = true)
	public User getAllUsers(){
		return (User)sessionFactory.getCurrentSession().createQuery("from com.model.User where id = ?").uniqueResult();
	}
}
