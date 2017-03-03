package com.eventra.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eventra.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Long> {

	public User findByUsername(String username);

}
