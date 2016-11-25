package com.training.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.training.persistence.dao.UserDAO;
import com.training.translator.Translator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserServicePortType {

  @Autowired
  UserDAO userDAO;

  @Autowired
  Translator<com.training.service.user.User, com.training.persistence.model.User> translator;

  @Override
  public User add(User userBOM) {
    com.training.persistence.model.User userAdded = userDAO.add(translator.toBEO(userBOM));
    return translator.toBOM(userAdded);
  }

  @Cacheable(cacheNames = "user", key = "#id")
  @Override
  public User getById(Long id) {
    log.debug("get user by id:{}", id);
    return translator.toBOM((userDAO.getById(id)));
  }

  @CacheEvict(cacheNames = "user", key = "#userBOM.id")
  @Override
  public User update(User userBOM) {
    com.training.persistence.model.User userMaped = translator.toBEO(userBOM);
    Long userId = userMaped.getId();
    if (userId != null) {
      userMaped.getPerson().setId(userDAO.getById(userId).getPerson().getId());
    }
    com.training.persistence.model.User userUpdated = userDAO.update(userMaped);
    return translator.toBOM(userUpdated);
  }

  @Override
  public List<User> getByLimit(Integer firstRow, Integer countRows, String orderBy, Boolean asc) {
    List<com.training.persistence.model.User> byLimit = userDAO.getByLimit(firstRow, countRows, orderBy, asc);
    List<User> bomList = translator.toBOMList(byLimit);
    return bomList;
  }

  @CacheEvict(cacheNames = "user", key = "#userBOM.id")
  @Override
  public void delete(User userBOM) {
    userDAO.delete(translator.toBEO(userBOM));
  }

  @Override
  @Cacheable("user")
  public User getByLogin(String login) {
    return translator.toBOM(userDAO.getByLogin(login));
  }

  @Override
  public Long getCount() {
    return userDAO.getCount();
  }
}
