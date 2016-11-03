package com.training.persistence.dao.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.training.persistence.dao.UserDAO;
import com.training.persistence.model.User;

@Repository
public class UserDAOImpl extends AbstractEntityDAOImpl<User> implements UserDetailsService, UserDAO {

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return null;
  }
}
