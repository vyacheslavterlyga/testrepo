package com.training.persistence.dao;

import org.springframework.transaction.annotation.Transactional;

import com.training.persistence.model.User;

public interface UserDAO extends EntityDAO<User> {

  @Transactional(readOnly = true)
  User getByLogin(String login);
}
