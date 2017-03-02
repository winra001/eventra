package com.eventra.dao;

import org.springframework.data.repository.CrudRepository;

import com.eventra.model.User;

public interface UserDao extends CrudRepository<User, Long> {

	public User findByUsername(String username);

}
