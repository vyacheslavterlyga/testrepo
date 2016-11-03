package com.training.persistence.dao;

import java.util.List;

import com.training.persistence.model.AbstractEntity;

public interface EntityDAO<T extends AbstractEntity> {

  T getById(Long id);

  T add(T user);

  List<T> getAll();

}
