package com.eventra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProgramController {

	@RequestMapping("/program")
	public String programGet() {
		return "program/programList";
	}

}
