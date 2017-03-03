package com.eventra.utils;

import com.eventra.dto.UserDto;
import com.eventra.model.User;

public class UserUtils {

	public static <T extends UserDto> User fromUserDtoToDomainUser(T userDto) {
		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setPassword(userDto.getPassword());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEnabled(true);
		return user;
	}

	public static User createBasicUser(String username) {
		User user = new User();
		user.setUsername(username);
		user.setPassword("secret");
		user.setFirstName("firstName");
		user.setLastName("lastName");
		user.setEnabled(true);
		return user;
	}
	
}
