package com.eventra.enums;

public enum RolesEnum {

	BASIC(1, "ROLE_BASIC"),
	ADMIN(2, "ROLE_ADMIN");

	private final Integer id;

	private final String roleName;

	private RolesEnum(Integer id, String roleName) {
		this.id = id;
		this.roleName = roleName;
	}

	public Integer getId() {
		return id;
	}

	public String getRoleName() {
		return roleName;
	}

}
