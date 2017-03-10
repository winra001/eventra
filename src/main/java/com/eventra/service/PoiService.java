package com.eventra.service;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eventra.dto.AttendanceDto;
import com.eventra.dto.ChairDto;
import com.eventra.dto.ProgramDto;
import com.eventra.dto.VenueDto;

@Service
public class PoiService {

	/**
	 * Extract Attendance from Excel
	 */
	public List<AttendanceDto> extractAttendance(MultipartFile file) {
		InputStream is = null;
		Workbook workbook = null;
		AttendanceDto attendanceDto = null;
		List<AttendanceDto> attendanceDtoList = new ArrayList<>();

		try {
			is = new ByteArrayInputStream(file.getBytes());

			if (file.getOriginalFilename().endsWith(".xls")) {
				workbook = new HSSFWorkbook(is);
			} else if (file.getOriginalFilename().endsWith(".xlsx")) {
				workbook = new XSSFWorkbook(is);
			} else {
				throw new IllegalArgumentException("Received file does not have a standard excel extension.");
			}

			int rowCount = 0;

			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIter = sheet.rowIterator();

			while (rowIter.hasNext()) {
				rowCount += 1;
				Row row = rowIter.next();

				if (rowCount == 1) {
					// TODO: Do something
					continue;
				}

				attendanceDto = new AttendanceDto();
				attendanceDto.setFullName(row.getCell(0) == null ? null : row.getCell(0).toString());
				attendanceDto.setEmail(row.getCell(1) == null ? null : row.getCell(1).toString());
				attendanceDto.setReference(row.getCell(2) == null ? null : row.getCell(2).toString());
				attendanceDtoList.add(attendanceDto);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return attendanceDtoList;
	}

	/**
	 * Extracts Program data from Excel
	 * 0: Date
	 * 1: Begin time
	 * 2: End time
	 * 3: Title
	 * 4: Description
	 * 5: Chair
	 * 6: Venue
	 * 7: Level
	 */
	public List<ProgramDto> extractProgram(MultipartFile file) {
		InputStream is = null;
		Workbook workbook = null;
		ProgramDto programDto = null;
		VenueDto venueDto = null;
		ChairDto chairDto = null;
		List<ProgramDto> programDtoList = new ArrayList<ProgramDto>();
		List<VenueDto> venueDtoList = null;
		List<ChairDto> chairDtoList = null;

		DateFormat timeFormat = new SimpleDateFormat("HH:mm");
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		try {
			is = new ByteArrayInputStream(file.getBytes());

			if (file.getOriginalFilename().endsWith(".xls")) {
				workbook = new HSSFWorkbook(is);
			} else if (file.getOriginalFilename().endsWith(".xlsx")) {
				workbook = new XSSFWorkbook(is);
			} else {
				throw new IllegalArgumentException("Received file does not have a standard excel extension.");
			}

			int rowCount = 0;
			String date = "";
			String beginTime = "";
			String endTime = "";
			Date beginDateTime = null;
			Date endDateTime = null;

			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIter = sheet.rowIterator();

			while (rowIter.hasNext()) {
				rowCount += 1;
				Row row = rowIter.next();

				if (rowCount == 1) {
					// TODO: Do something
					continue;
				}

				chairDtoList = new ArrayList<ChairDto>();
				venueDtoList = new ArrayList<VenueDto>();

				// Populating date
				date = dateFormat.format(row.getCell(0).getDateCellValue());
				beginTime = timeFormat.format(row.getCell(1).getDateCellValue());
				endTime = timeFormat.format(row.getCell(2).getDateCellValue());

				try {
					beginDateTime = dateTimeFormat.parse(date + " " + beginTime);
					endDateTime = dateTimeFormat.parse(date + " " + endTime);
				} catch (ParseException e) {
					e.printStackTrace();
				}

				// Populating Chair
				if (row.getCell(5) != null) {
					StringTokenizer tokenizer = new StringTokenizer(row.getCell(5).toString(), ",");
					while (tokenizer.hasMoreElements()) {
						chairDto = new ChairDto();
						chairDto.setName(((String) tokenizer.nextElement()).trim());
						chairDtoList.add(chairDto);
					}
				}

				// Populating Venue
				if (row.getCell(6) != null) {
					StringTokenizer tokenizer = new StringTokenizer(row.getCell(6).toString(), ",");
					while (tokenizer.hasMoreElements()) {
						venueDto = new VenueDto();
						venueDto.setName(((String) tokenizer.nextElement()).trim());
						venueDtoList.add(venueDto);
					}
				}

				programDto = new ProgramDto();
				programDto.setBeginDate(beginDateTime);
				programDto.setEndDate(endDateTime);
				programDto.setTitle(row.getCell(3) == null ? null : row.getCell(3).toString().trim());
				programDto.setDescription(row.getCell(4) == null ? null : row.getCell(4).toString().trim());
				programDto.setChairs(chairDtoList);
				programDto.setVenues(venueDtoList);
				programDto.setLevel(row.getCell(7) == null ? 0 : Double.valueOf(row.getCell(7).toString()).intValue());

				programDtoList.add(programDto);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return programDtoList;
	}

}
