package com.eventra.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eventra.model.Chair;

@Repository
public interface ChairDao extends CrudRepository<Chair, Long> {

	public Chair findByName(String name);

}
