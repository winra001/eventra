package com.eventra.dao;

import org.springframework.data.repository.CrudRepository;

import com.eventra.model.security.Role;

public interface RoleDao extends CrudRepository<Role, Long> {

}
