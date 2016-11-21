package com.training.service.user;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.training.persistence.dao.UserDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserServiceImpl implements UserServicePortType {

  @Autowired
  UserDAO userDAO;

  @Autowired
  DozerBeanMapper dozerBeanMapper;

  @Override
  public User add(User user) {
    userDAO.add(bomToBeo(user));
    return user;
  }

  @Override
  public User getById(Long id) {
    log.debug("get user by id:{}", id);
    return beoToBom(userDAO.getById(id));
  }

  @Override
  public User update(User user) {
    userDAO.update(bomToBeo(user));
    return user;
  }

  @Override
  public Object getAll() {
    return bomListToBeoList(userDAO.getAll());
  }

  @Override
  public void delete(User user) {
    userDAO.delete(bomToBeo(user));
  }

  @Override
  public User getByLogin(String login) {
    return beoToBom(userDAO.getByLogin(login));
  }

  private User beoToBom(com.training.persistence.model.User userBEO) {
    if (userBEO == null)
      return null;
    return dozerBeanMapper.map(userBEO, User.class);
  }

  private com.training.persistence.model.User bomToBeo(User userBOM) {
    if (userBOM == null)
      return null;
    return dozerBeanMapper.map(userBOM, com.training.persistence.model.User.class);
  }

  private List<User> bomListToBeoList(List<com.training.persistence.model.User> userBEOList) {
    if (userBEOList == null)
      return null;
    return dozerBeanMapper.map(userBEOList, List.class);
  }

}
