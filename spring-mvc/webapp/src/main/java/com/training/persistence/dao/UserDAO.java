package com.training.persistence.dao;

import com.training.persistence.model.User;

public interface UserDAO extends EntityDAO<User> {
	User getByLogin(String Login);
}