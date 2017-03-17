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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "program_chair")
public class ProgramChair implements Serializable {

	private static final long serialVersionUID = -7944070733324500425L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "program_id")
	private Program program;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "chair_id")
	private Chair chair;

	public ProgramChair() {
	}

	public ProgramChair(Program program, Chair chair) {
		this.program = program;
		this.chair = chair;
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

	public Chair getChair() {
		return chair;
	}

	public void setChair(Chair chair) {
		this.chair = chair;
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
		if (!(obj instanceof ProgramChair))
			return false;
		ProgramChair other = (ProgramChair) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProgramChair [id=");
		builder.append(id);
		builder.append(", program=");
		builder.append(program);
		builder.append(", chair=");
		builder.append(chair);
		builder.append("]");
		return builder.toString();
	}

}
