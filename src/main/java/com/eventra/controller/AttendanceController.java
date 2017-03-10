package com.eventra.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.eventra.dto.AttendanceDto;
import com.eventra.model.Attendance;
import com.eventra.service.AttendanceService;
import com.eventra.service.PoiService;
import com.eventra.utils.AttendanceUtils;

@Controller
public class AttendanceController {

	private static final Logger LOG = LoggerFactory.getLogger(AttendanceController.class);

	private static final String IMPORT_ATTENDANCE_URL_MAPPING = "/importAttendance";

	private static final String IMPORT_ATTENDANCE_VIEW_NAME = "attendance/importAttendance";

	public static final String IMPORT_ATTENDANCE_MESSAGE_KEY = "imported";

	@Autowired
	private PoiService poiService;

	@Autowired
	private AttendanceService attendanceService; 

	@RequestMapping(value = IMPORT_ATTENDANCE_URL_MAPPING, method = RequestMethod.GET)
	public String importAttendanceGet(ModelMap model) {
		return IMPORT_ATTENDANCE_VIEW_NAME;
	}

	@RequestMapping(value = IMPORT_ATTENDANCE_URL_MAPPING, method = RequestMethod.POST)
	public String importAttendancePost(ModelMap model,
									   @RequestParam(name = "file", required = true) MultipartFile file) throws IOException {
		if (file != null && !file.isEmpty()) {
			Attendance attendance = null;
			List<AttendanceDto> attendanceDtoList = poiService.extractAttendance(file);

			for (AttendanceDto attendanceDto : attendanceDtoList) {
				attendance = AttendanceUtils.fromAttendanceDtoToDomainAttendance(attendanceDto);
				attendanceService.createAttendance(attendance);
			}
		}

		LOG.info("Attendance has been imported successfully");

		model.addAttribute(IMPORT_ATTENDANCE_MESSAGE_KEY, "true");

		return IMPORT_ATTENDANCE_VIEW_NAME;
	}

}
