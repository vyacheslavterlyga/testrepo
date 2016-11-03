package com.training.persistence.dao;

import java.util.List;

public interface EntityDAO<T extends Object> {

	T add(T user);

	T getById(String id);

	List<T> getAll();

}