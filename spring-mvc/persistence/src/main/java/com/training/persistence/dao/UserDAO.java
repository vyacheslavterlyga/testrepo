package com.training.persistence.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.training.persistence.model.User;

@Repository
public interface UserDAO extends EntityDAO<User> {

  @Transactional(readOnly = true)
  User getByLogin(String login);

  @Transactional(readOnly = true)
  List<User> getByLimit(Integer firstRow, Integer countRows, String orderBy);

  @Transactional(readOnly = true)
  long getCount();
}
