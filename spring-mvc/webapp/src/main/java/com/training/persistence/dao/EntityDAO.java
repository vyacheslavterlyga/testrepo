package com.training.persistence.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.training.persistence.model.AbstractEntity;

public interface EntityDAO<T extends AbstractEntity> {

  @Transactional(readOnly = true)
  T getById(Long id);

  @Transactional(readOnly = false)
  T add(T user);

  @Transactional(readOnly = true)
  List<T> getAll();
}
