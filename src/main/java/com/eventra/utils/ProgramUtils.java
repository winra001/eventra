package com.eventra.utils;

import com.eventra.dto.ChairDto;
import com.eventra.dto.ProgramDto;
import com.eventra.dto.VenueDto;
import com.eventra.model.Chair;
import com.eventra.model.Program;
import com.eventra.model.Venue;

public class ProgramUtils {

	public static <T extends ProgramDto> Program fromProgramDtoToDomainProgram(T programDto) {
		Program program = new Program();
		program.setBeginDate(programDto.getBeginDate());
		program.setEndDate(programDto.getEndDate());
		program.setTitle(programDto.getTitle());
		program.setDescription(programDto.getDescription());
		program.setLevel(programDto.getLevel());
		return program;
	}

	public static <T extends ChairDto> Chair fromProgramDtoToDomainChair(T chairDto) {
		Chair chair = new Chair();
		chair.setName(chairDto.getName());
		return chair;
	}

	public static <T extends VenueDto> Venue fromProgramDtoToDomainVenue(T venueDto) {
		Venue venue = new Venue();
		venue.setName(venueDto.getName());
		return venue;
	}

}
