package com.training.persistence.dao.impl

import com.training.persistence.dao.EntityDAO


class UserDAOSpec extends AbstractEntityDAOSpec {

  @Override
  public EntityDAO getDAO() { 
    return new UserDAOImpl(sessionFactory:sessionFactory)
  }
}
