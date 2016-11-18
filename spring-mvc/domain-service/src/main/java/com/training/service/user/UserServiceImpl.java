package com.training.service.user;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.training.persistence.dao.UserDAO;

// @javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter(value = com.training.service.adapter.PersonAdapter.class, type
// = com.training.service.user.Person.class)
public class UserServiceImpl implements UserServicePortType {

  @Autowired
  UserDAO userDAO;

  @Autowired
  DozerBeanMapper dozerBeanMapper;

  @Override
  public User add(User user) {
    com.training.persistence.model.User userBEO = dozerBeanMapper.map(user, com.training.persistence.model.User.class);
    userDAO.add(userBEO);
    return user;
  }

  @Override
  public User getById(Long id) {
    com.training.persistence.model.User userBEO = userDAO.getById(id);
    User userBOM = dozerBeanMapper.map(userBEO, User.class);
    return userBOM;
  }

  @Override
  public User update(User user) {
    userDAO.update(dozerBeanMapper.map(user, com.training.persistence.model.User.class));
    return user;
  }

  @Override
  public Object getAll() {
    List<com.training.persistence.model.User> userListBEO = userDAO.getAll();
    return dozerBeanMapper.map(userListBEO, List.class);
  }

  @Override
  public void delete(User user) {
    userDAO.delete(dozerBeanMapper.map(user, com.training.persistence.model.User.class));
  }

  @Override
  public User getByLogin(String login) {
    com.training.persistence.model.User userBEO = userDAO.getByLogin(login);
    User userBOM = dozerBeanMapper.map(userBEO, User.class);
    return userBOM;
  }

}
