package com.training.persistence.dao.impl;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.training.persistence.dao.UserDAO;
import com.training.persistence.model.User;

public class UserDAOImpl extends AbstractEntityDAOImpl<User> implements UserDAO {

  @Override
  public User getByLogin(String login) {
    return (User) getGenericCriteria().add(Restrictions.eq("login", login)).uniqueResult();
  }

  @Override
  public List<User> getByLimit(Integer firstRow, Integer countRows, String orderBy) {
    List<User> list = getGenericCriteria().addOrder(Order.asc(orderBy)).setFirstResult(firstRow).setMaxResults(countRows).list();
    return list;
  }

  @Override
  public long getCount() {
    return (long) getGenericCriteria().setProjection(Projections.rowCount()).uniqueResult();
  }
}
