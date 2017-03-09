package com.eventra.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eventra.model.Venue;

@Repository
public interface VenueDao extends CrudRepository<Venue, Long> {

	public Venue findByName(String name);

}
