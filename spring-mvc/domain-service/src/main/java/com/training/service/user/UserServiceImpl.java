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
  public User add(User user) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public User getById(Long id) {
    return translate(userDAO.getById(id));
  }

  @Override
  public User update(AbstractEntity user) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object getAll() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void delete(User user) {
    // TODO Auto-generated method stub
  }

  @Override
  public User getByLogin(String login) {
    return translate(userDAO.getByLogin(login));
  }

  private User translate(com.training.persistence.model.User userBEO) {
    if (userBEO == null)
      return null;
    return dozerBeanMapper.map(userBEO, User.class);
  }

}
