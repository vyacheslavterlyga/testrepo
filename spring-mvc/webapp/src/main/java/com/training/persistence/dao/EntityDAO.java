package com.training.persistence.dao;

import java.util.List;

import com.training.persistence.model.AbstractEntity;

public interface EntityDAO<T extends AbstractEntity> {

  T add(T user);

  T getById(String id);

  List<T> getAll();

}
