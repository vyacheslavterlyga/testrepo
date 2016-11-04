package com.training.persistence.dao.impl;

import java.util.List;

import com.training.persistence.dao.UserDAO;
import com.training.persistence.model.User;

public class UserDAOImpl extends AbstractEntityDAOImpl<User> implements UserDAO {

  @Override
  public User getByLogin(String login) {
    return (User) getSession().createQuery("FROM User WHERE login = :login").setString("login", login).uniqueResult();
  }

  public List<User> getAllUsers() {
    return getSession().createQuery("FROM User").list();
  }
}