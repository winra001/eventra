package com.eventra.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

public class ProgramDto implements Serializable {

	private static final long serialVersionUID = 6898769506336844127L;

	private Date beginDate;

	private Date endDate;

	private String title;

	@Column(columnDefinition = "BLOB")
	private String description;

	private int level;

	private List<ChairDto> chairs;

	private List<VenueDto> venues;

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<ChairDto> getChairs() {
		return chairs;
	}

	public void setChairs(List<ChairDto> chairs) {
		this.chairs = chairs;
	}

	public List<VenueDto> getVenues() {
		return venues;
	}

	public void setVenues(List<VenueDto> venues) {
		this.venues = venues;
	}

}
