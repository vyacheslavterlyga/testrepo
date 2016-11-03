package com.training.persistence.dao;

import java.util.List;

import com.training.persistence.model.AbstractEntity;

public interface EntityDAO<T extends AbstractEntity> {

	T add(T user);

	T getById(Long id);

	List<T> getAll();

}