package com.eventra.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eventra.model.Program;

@Repository
public interface ProgramDao extends CrudRepository<Program, Long> {

}
