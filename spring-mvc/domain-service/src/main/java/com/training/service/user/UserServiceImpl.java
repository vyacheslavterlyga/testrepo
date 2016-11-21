package com.training.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.persistence.dao.UserDAO;
import com.training.translator.impl.UserTranslator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserServicePortType {

  @Autowired
  UserDAO userDAO;

  @Autowired
  UserTranslator translator;

  @Override
  public User add(User userBOM) {
    com.training.persistence.model.User userAdded = userDAO.add(translator.toBEO(userBOM));
    return translator.toBOM(userAdded);
  }

  @Override
  public User getById(Long id) {
    log.debug("get user by id:{}", id);
    return translator.toBOM((userDAO.getById(id)));
  }

  @Override
  public User update(User userBOM) {
    com.training.persistence.model.User userMaped = translator.toBEO(userBOM);
    com.training.persistence.model.User userUpdated = userDAO.update(userMaped);
    return translator.toBOM(userUpdated);
  }

  @Override
  public Object getAll() {
    return translator.toBOMList((userDAO.getAll()));
  }

  @Override
  public void delete(User userBOM) {
    userDAO.delete(translator.toBEO(userBOM));
  }

  @Override
  public User getByLogin(String login) {
    return translator.toBOM(userDAO.getByLogin(login));
  }

}
