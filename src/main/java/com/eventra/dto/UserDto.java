package com.eventra.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class UserDto implements Serializable {

	private static final long serialVersionUID = 2224742635079161035L;

	@NotNull(message = "Email is required")
	@NotBlank(message = "Email is required")
	@Email
	private String username;

	@NotNull(message = "Password is required")
	@NotBlank(message = "Password is required")
	private String password;

	@NotNull
	@NotBlank(message = "Confirm Password is required")
	private String confirmPassword;

	@NotNull(message = "First name is required")
	@NotBlank(message = "First name is required")
	private String firstName;

	@NotNull(message = "Last name is required")
	@NotBlank(message = "Last name is required")
	private String lastName;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof UserDto))
			return false;
		UserDto other = (UserDto) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserDto [username=");
		builder.append(username);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append("]");
		return builder.toString();
	}

}
