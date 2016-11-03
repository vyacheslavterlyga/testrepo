package com.training.persistence.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.training.persistence.model.User;

public interface UserDAO {

	User addUser(User user);

	User getUserById(String id);

	List<User> getAllUsers();

}