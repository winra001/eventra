package com.eventra.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eventra.model.Attendance;

@Repository
public interface AttendanceDao extends CrudRepository<Attendance, Long> {

}
