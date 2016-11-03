package com.training.persistence.dao.impl

import org.hibernate.Session
import org.hibernate.SessionFactory

import spock.lang.Shared
import spock.lang.Specification

import com.training.persistence.dao.EntityDAO
import com.training.persistence.model.AbstractEntity
import com.training.persistence.model.User

abstract class AbstractEntityDAOSpec extends Specification { 

  @Shared
  SessionFactory sessionFactory = Stub(SessionFactory)

  @Shared
  Session session = Stub(Session)

  def setup(){
    sessionFactory.getCurrentSession() >> session
    session.get( _ as AbstractEntity,_ as String) >> new User()
  }

  abstract EntityDAO getDAO()

  def "test getById"() {
    given:
    def dao = getDAO()

    when:
    def result =dao.getById(_ as String)
    then:
    1 * sessionFactory.getCurrentSession().get( _ as AbstractEntity,_ as String)
  }
}
