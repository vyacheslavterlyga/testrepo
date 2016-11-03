package com.training.persistence.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.training.persistence.dao.UserDAO;
import com.training.persistence.model.User;

public class UserDAOImpl extends AbstractEntityDAOImpl<com.training.persistence.model.User> implements UserDetailsService, UserDAO {

  @Override
  public User getByLogin(String Login) {
    return (User) sessionFactory.getCurrentSession().createQuery("FROM User WHERE login = :login").setString("login", Login).uniqueResult();
  }

  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    User userFromDB = getByLogin(login);
    List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(userFromDB.getRole()));
    return new org.springframework.security.core.userdetails.User(login, userFromDB.getPassword(), authorities);
  }
}
