package com.training.persistence.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.training.persistence.dao.EntityDAO;
import com.training.persistence.model.AbstractEntity;

abstract public class AbstractEntityDAOImpl<T extends AbstractEntity> implements EntityDAO<T> {

  @Autowired
  private SessionFactory sessionFactory;

  protected Session getSession() {
    return sessionFactory.getCurrentSession();
  }

  @Override
  public T add(T user) {
    getSession().save(user);
    return user;
  }

  @Override
  public T getById(Long id) {
    return getSession().get(getGenericClass(), id);
  }

  private Class<? extends T> getGenericClass() {
    return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
  }

  @Override
  public List<T> getAll() {
    return getSession().createCriteria(getGenericClass()).list();
  }

}
