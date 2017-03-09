package com.eventra.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Program implements Serializable {

	private static final long serialVersionUID = 2358045404417850616L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "begin_date")
	private Date beginDate;

	@Column(name = "end_date")
	private Date endDate;

	private String title;

	@Column(columnDefinition = "BLOB")
	private String description;

	private int level;

	@OneToMany(mappedBy = "program", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<ProgramVenue> programVenues = new HashSet<>();

	@OneToMany(mappedBy = "program", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<ProgramChair> programChairs = new HashSet<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public Set<ProgramVenue> getProgramVenues() {
		return programVenues;
	}

	public void setProgramVenues(Set<ProgramVenue> programVenues) {
		this.programVenues = programVenues;
	}

	public Set<ProgramChair> getProgramChairs() {
		return programChairs;
	}

	public void setProgramChairs(Set<ProgramChair> programChairs) {
		this.programChairs = programChairs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Program))
			return false;
		Program other = (Program) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Program [id=");
		builder.append(id);
		builder.append(", beginDate=");
		builder.append(beginDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", title=");
		builder.append(title);
		builder.append(", description=");
		builder.append(description);
		builder.append(", level=");
		builder.append(level);
		builder.append(", programVenues=");
		builder.append(programVenues);
		builder.append(", programChairs=");
		builder.append(programChairs);
		builder.append("]");
		return builder.toString();
	}

}
