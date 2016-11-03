package com.training.persistence.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.training.persistence.model.User;

@Repository
public interface UserDAO extends EntityDAO<User> {

  @Transactional(readOnly = false)
  User getByLogin(String Login);
}
