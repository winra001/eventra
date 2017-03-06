package com.eventra.service;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eventra.dto.AttendanceDto;

@Service
public class PoiService {

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

}
