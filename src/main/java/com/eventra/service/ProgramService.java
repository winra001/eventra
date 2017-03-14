package com.eventra.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eventra.dao.ChairDao;
import com.eventra.dao.ProgramDao;
import com.eventra.dao.VenueDao;
import com.eventra.model.Chair;
import com.eventra.model.Program;
import com.eventra.model.ProgramChair;
import com.eventra.model.ProgramVenue;
import com.eventra.model.Venue;

@Service
@Transactional(readOnly = true)
public class ProgramService {

	private static final Logger LOG = LoggerFactory.getLogger(ProgramService.class);

	@Autowired
	private ChairDao chairDao;

	@Autowired
	private VenueDao venueDao;

	@Autowired
	private ProgramDao programDao;

	public Program findById(Long id) {
		return programDao.findOne(id);
	}
	
	public List<Program> findAll() {
		return (List<Program>) programDao.findAll();
	}
	
	public List<Date> findEventDates() {
		return programDao.findEventDates();
	}

	@Transactional
	public Program createProgram(Program program, Set<ProgramChair> programChairs, Set<ProgramVenue> programVenues) {
		Program createdProgram = null;
		Chair existedChair = null;
		Venue existedVenue = null;

		// Creates Chair
		if (programChairs != null && !programChairs.isEmpty()) {
			for (ProgramChair programChair : programChairs) {
				existedChair = chairDao.findByName(programChair.getChair().getName());

				if (existedChair != null) {
					programChair.setChair(existedChair);
				}

				chairDao.save(programChair.getChair());
			}

			program.getProgramChairs().addAll(programChairs);
		}

		// Creates Venue
		if (programVenues != null && !programVenues.isEmpty()) {
			for (ProgramVenue programVenue : programVenues) {
				existedVenue = venueDao.findByName(programVenue.getVenue().getName());

				if (existedVenue != null) {
					programVenue.setVenue(existedVenue);
				}

				venueDao.save(programVenue.getVenue());
			}
			program.getProgramVenues().addAll(programVenues);
		}

		// Creates Program
		createdProgram = programDao.save(program);

		return createdProgram;
	}

}
