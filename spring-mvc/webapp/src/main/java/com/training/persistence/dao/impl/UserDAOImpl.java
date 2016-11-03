package com.training.persistence.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.persistence.dao.UserDAO;
import com.training.persistence.model.User;

@Repository
public class UserDAOImpl implements UserDetailsService, UserDAO {

  private static final String MODEL_USER = "com.training.persistence.model.User";
@Autowired
  private SessionFactory sessionFactory;

  /* (non-Javadoc)
 * @see com.training.persistence.dao.UserDAO#addUser(com.training.persistence.model.User)
 */
@Override
@Transactional(readOnly = false)
  public User addUser(User user) {
    sessionFactory.getCurrentSession().save(user);
    return user;
  }

  /* (non-Javadoc)
 * @see com.training.persistence.dao.UserDAO#getUserById(java.lang.String)
 */
@Override
@Transactional(readOnly = true)
  public User getUserById(String id) {
    return (User) sessionFactory.getCurrentSession().get(User.class, id);
  }

  /* (non-Javadoc)
 * @see com.training.persistence.dao.UserDAO#getAllUsers()
 */
@Override
@Transactional(readOnly = true)
  public List<User> getAllUsers() {
    return sessionFactory.getCurrentSession().createQuery("from "+MODEL_USER).list();
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return null;
  }
}
