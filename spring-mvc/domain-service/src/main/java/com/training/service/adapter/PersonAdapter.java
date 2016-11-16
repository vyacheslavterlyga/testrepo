package com.training.service.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.training.service.user.Person;

public class PersonAdapter extends XmlAdapter<Person, Person> {

  @Override
  public Person marshal(Person person) throws Exception {
    person.setUser(null);
    return person;
  }

  @Override
  public Person unmarshal(Person adaptedUser) throws Exception {
    return adaptedUser;
  }
}
