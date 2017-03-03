package com.eventra.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eventra.model.security.Role;

@Repository
public interface RoleDao extends CrudRepository<Role, Integer> {

}
