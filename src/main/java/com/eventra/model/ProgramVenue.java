package com.eventra.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "program_venue")
public class ProgramVenue implements Serializable {

	private static final long serialVersionUID = 3441508535246672228L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "program_id")
	private Program program;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "venue_id")
	private Venue venue;

	public ProgramVenue() {
	}

	public ProgramVenue(Program program, Venue venue) {
		this.program = program;
		this.venue = venue;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
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
		if (!(obj instanceof ProgramVenue))
			return false;
		ProgramVenue other = (ProgramVenue) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProgramVenue [id=");
		builder.append(id);
		builder.append(", program=");
		builder.append(program);
		builder.append(", venue=");
		builder.append(venue);
		builder.append("]");
		return builder.toString();
	}

}
