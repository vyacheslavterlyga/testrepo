package com.training.persistence.dao.impl;

import java.util.ArrayList;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.training.persistence.dao.UserDAO;

public class UserDAOImpl extends AbstractEntityDAOImpl<com.training.persistence.model.User> implements UserDetailsService, UserDAO {

  @Override
  public com.training.persistence.model.User getByLogin(String Login) {
    return (com.training.persistence.model.User) sessionFactory
      .getCurrentSession()
      .createQuery("FROM AUSER WHERE login = :login")
      .setString("login", Login)
      .uniqueResult();
  }

  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    com.training.persistence.model.User userFromDB = getByLogin(login);
    String role = userFromDB.getRole();
    String password = userFromDB.getPassword();
    ArrayList<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(role));
    UserDetails userDetails = new org.springframework.security.core.userdetails.User(login, password, authorities);
    return userDetails;
  }
}
