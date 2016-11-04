package com.training.persistence.dao;

import org.springframework.stereotype.Repository;

import com.training.persistence.model.Person;

@Repository
public interface PersonDAO extends EntityDAO<Person> {
}
