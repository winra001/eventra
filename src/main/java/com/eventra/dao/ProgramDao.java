package com.eventra.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eventra.model.Program;

@Repository
public interface ProgramDao extends CrudRepository<Program, Long> {

	@Query("SELECT DISTINCT(DATE(p.beginDate)) FROM Program p")
	public List<Date> findEventDates();

}
