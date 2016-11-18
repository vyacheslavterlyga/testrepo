package com.training.service.user;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.training.persistence.dao.UserDAO;

public class UserServiceImpl implements UserServicePortType {

  @Autowired
  UserDAO userDAO;

  @Autowired
  DozerBeanMapper dozerBeanMapper;

  @Override
  public User add(User user) {
    userDAO.add(BOMtoBEO(user));
    return user;
  }

  @Override
  public User getById(Long id) {
    return BEOToBOM(userDAO.getById(id));
  }

  @Override
  public User update(User user) {
    userDAO.update(BOMtoBEO(user));
    return user;
  }

  @Override
  public Object getAll() {
    return BOMListToBEOList(userDAO.getAll());
  }

  @Override
  public void delete(User user) {
    userDAO.delete(BOMtoBEO(user));
  }

  @Override
  public User getByLogin(String login) {
    return BEOToBOM(userDAO.getByLogin(login));
  }

  private User BEOToBOM(com.training.persistence.model.User userBEO) {
    if (userBEO == null)
      return null;
    return dozerBeanMapper.map(userBEO, User.class);
  }

  private com.training.persistence.model.User BOMtoBEO(User userBOM) {
    if (userBOM == null)
      return null;
    return dozerBeanMapper.map(userBOM, com.training.persistence.model.User.class);
  }

  private Object BOMListToBEOList(List<com.training.persistence.model.User> userBEOList) {
    if (userBEOList == null)
      return null;
    return dozerBeanMapper.map(userBEOList, List.class);
  }

}
