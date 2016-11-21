package com.training.translator.impl;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.training.persistence.dao.UserDAO;
import com.training.translator.Translator;

public class UserTranslator implements Translator {

  @Autowired
  UserDAO userDAO;

  @Autowired
  DozerBeanMapper dozerBeanMapper;

  @Override
  public com.training.service.user.User toBOM(Object userBEO) {
    if (userBEO == null)
      return null;
    return dozerBeanMapper.map(userBEO, com.training.service.user.User.class);
  }

  @Override
  public com.training.persistence.model.User toBEO(Object userBOM) {
    if (userBOM == null)
      return null;
    com.training.persistence.model.User userMaped = dozerBeanMapper.map(userBOM, com.training.persistence.model.User.class);
    userMaped.getPerson().setUser(userMaped);
    Long userId = userMaped.getId();
    if (userId != null) {
      userMaped.getPerson().setId(userDAO.getById(userId).getPerson().getId());
    }
    return userMaped;
  }

  @Override
  public List<com.training.service.user.User> toBOMList(List userBEOList) {
    if (userBEOList == null)
      return null;
    return dozerBeanMapper.map(userBEOList, List.class);
  }

  @Override
  public List<com.training.persistence.model.User> toBEOList(List userBOMList) {
    if (userBOMList == null)
      return null;
    return dozerBeanMapper.map(userBOMList, List.class);
  }

}
