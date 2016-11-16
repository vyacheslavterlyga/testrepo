package com.training.service.user;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.training.persistence.dao.UserDAO;

@javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter(value = com.training.service.adapter.PersonAdapter.class, type = com.training.service.user.Person.class)
public class UserServiceImpl implements UserServicePortType {

  @Autowired
  UserDAO userDAO;

  @Autowired
  DozerBeanMapper dozerBeanMapper;

  @Override
  public User add(User arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public User getById(Long id) {
    com.training.persistence.model.User userBEO = userDAO.getById(id);
    User userBOM = new User();
    userBOM.setLogin(userBEO.getLogin());
    return userBOM;
  }

  @Override
  public User update(AbstractEntity arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object getAll() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void delete(User arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public User getByLogin(String login) {
    com.training.persistence.model.User userBEO = userDAO.getByLogin(login);
    return dozerBeanMapper.map(userBEO, User.class);
  }

}
