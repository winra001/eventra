package com.eventra.service;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eventra.dao.ChairDao;
import com.eventra.dao.ProgramDao;
import com.eventra.dao.VenueDao;
import com.eventra.model.Program;
import com.eventra.model.ProgramChair;
import com.eventra.model.ProgramVenue;

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

	@Transactional
	public Program createProgram(Program program, Set<ProgramChair> programChairs, Set<ProgramVenue> programVenues) {
		Program createdProgram = null;

		if (programChairs != null && !programChairs.isEmpty()) {
			for (ProgramChair chair : programChairs) {
				chairDao.save(chair.getChair());
			}
			program.getProgramChairs().addAll(programChairs);
		}

		if (programVenues != null && !programVenues.isEmpty()) {
			for (ProgramVenue venue : programVenues) {
				venueDao.save(venue.getVenue());
			}
			program.getProgramVenues().addAll(programVenues);
		}

		createdProgram = programDao.save(program);

		return createdProgram;
	}

	public Program findById(Long id) {
		return programDao.findOne(id);
	}

}
