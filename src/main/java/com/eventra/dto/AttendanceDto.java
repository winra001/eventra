package com.eventra.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

public class AttendanceDto implements Serializable {

	private static final long serialVersionUID = -1678146357748624982L;

	@NotNull
	private String fullName;

	@Email
	@NotNull
	private String email;

	private String reference;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof AttendanceDto))
			return false;
		AttendanceDto other = (AttendanceDto) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AttendanceDto [fullName=");
		builder.append(fullName);
		builder.append(", email=");
		builder.append(email);
		builder.append(", reference=");
		builder.append(reference);
		builder.append("]");
		return builder.toString();
	}

}
