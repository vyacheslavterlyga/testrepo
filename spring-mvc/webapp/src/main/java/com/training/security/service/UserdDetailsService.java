package com.training.security.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.training.service.user.User;
import com.training.service.user.UserServicePortType;

@Service
public class UserdDetailsService implements UserDetailsService {

  @Autowired
  UserServicePortType userService;

  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    User userFromDB = userService.getByLogin(login);
    List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_" + userFromDB.getRole()));
    return new org.springframework.security.core.userdetails.User(login, userFromDB.getPassword(), authorities);
  }
}
