package com.training.persistence.dao;

import java.util.List;

import com.training.persistence.model.AbstractEntity;
<<<<<<< HEAD


public interface EntityDAO<T extends AbstractEntity> {

<<<<<<< HEAD
	T getById(Long id);
=======
  T add(T user);
>>>>>>> origin/master

  T getById(String id);

  List<T> getAll();

}
