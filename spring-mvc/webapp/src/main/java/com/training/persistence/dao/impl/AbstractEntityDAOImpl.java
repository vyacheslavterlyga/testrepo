package com.training.persistence.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.training.persistence.dao.EntityDAO;
import com.training.persistence.model.AbstractEntity;

abstract public class AbstractEntityDAOImpl<T extends AbstractEntity> implements EntityDAO<T> {

  @Autowired
  protected SessionFactory sessionFactory;

  @Override
  public T add(T user) {
    sessionFactory.getCurrentSession().save(user);
    return user;
  }

  @Override
  public T getById(Long id) {
    return (T) sessionFactory.getCurrentSession().get(getGenericClass(), id);
  }

  private Class<? extends T> getGenericClass() {
    return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
  }

  @Override
  public List<T> getAll() {
    return sessionFactory.getCurrentSession().createCriteria(getGenericClass()).list();
  }

}
