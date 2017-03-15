package com.eventra.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.eventra.dto.ChairDto;
import com.eventra.dto.ProgramDto;
import com.eventra.dto.VenueDto;
import com.eventra.model.Chair;
import com.eventra.model.Program;
import com.eventra.model.ProgramChair;
import com.eventra.model.ProgramVenue;
import com.eventra.model.Venue;
import com.eventra.service.PoiService;
import com.eventra.service.ProgramService;
import com.eventra.utils.ProgramUtils;

@Controller
public class ProgramController {

	private static final Logger LOG = LoggerFactory.getLogger(ProgramController.class);

	private static final String IMPORT_PROGRAM_URL_MAPPING = "/importProgram";

	private static final String IMPORT_PROGRAM_VIEW_NAME = "program/importProgram";

	public static final String IMPORT_PROGRAM_MESSAGE_KEY = "imported";

	@Autowired
	private PoiService poiService;

	@Autowired
	private ProgramService programService;

	/**
	 * Fetches Program list
	 */
	@RequestMapping("/programs")
	public String programGet(ModelMap model) {
		Map<Date, List<Program>> programList = new HashMap<Date, List<Program>>();

		List<Date> eventDates = programService.findEventDates();
		for (Date eventDate : eventDates) {
			List<Program> programs = programService.findProgramsByBeginDate(eventDate);
			programList.put(eventDate, programs);
		}

		model.addAttribute("eventDates", eventDates);
		model.addAttribute("programList", programList);
		return "program/programs";
	}

	@RequestMapping(value = IMPORT_PROGRAM_URL_MAPPING, method = RequestMethod.GET)
	public String importProgramGet(ModelMap model) {
		return IMPORT_PROGRAM_VIEW_NAME;
	}

	/**
	 * Imports Program from Excel
	 */
	@RequestMapping(value = IMPORT_PROGRAM_URL_MAPPING, method = RequestMethod.POST)
	public String importProgramPost(ModelMap model,
									@RequestParam(name = "file", required = true) MultipartFile file) throws IOException {
		if (file != null && !file.isEmpty()) {
			List<ChairDto> chairDtoList = null;
			List<VenueDto> venueDtoList = null;
			Chair chair = null;
			Venue venue = null;
			Set<ProgramChair> chairs = null;
			Set<ProgramVenue> venues = null;

			List<ProgramDto> programDtoList = poiService.extractProgram(file);

			for (ProgramDto programDto : programDtoList) {
				Program program = ProgramUtils.fromProgramDtoToDomainProgram(programDto);

				chairDtoList = programDto.getChairs();
				venueDtoList = programDto.getVenues();

				if (chairDtoList != null && !chairDtoList.isEmpty()) {
					chairs = new HashSet<ProgramChair>();
					for (ChairDto chairDto : chairDtoList) {
						chair = ProgramUtils.fromProgramDtoToDomainChair(chairDto);
						chairs.add(new ProgramChair(program, chair));
					}
				}

				if (venueDtoList != null && !venueDtoList.isEmpty()) {
					venues = new HashSet<ProgramVenue>();
					for (VenueDto venueDto : venueDtoList) {
						venue = ProgramUtils.fromProgramDtoToDomainVenue(venueDto);
						venues.add(new ProgramVenue(program, venue));
					}
				}

				programService.createProgram(program, chairs, venues);
			}
		}

		LOG.info("Program has been imported successfully");

		model.addAttribute(IMPORT_PROGRAM_MESSAGE_KEY, "true");

		return IMPORT_PROGRAM_VIEW_NAME;
	}

}
