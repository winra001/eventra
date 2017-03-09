package com.eventra.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Chair implements Serializable {

	private static final long serialVersionUID = 5415566250144729297L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;

	@OneToMany(mappedBy = "chair", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<ProgramChair> programChairs = new HashSet<>();
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		if (!(obj instanceof Chair))
			return false;
		Chair other = (Chair) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Chair [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", programChairs=");
		builder.append(programChairs);
		builder.append("]");
		return builder.toString();
	}

}
