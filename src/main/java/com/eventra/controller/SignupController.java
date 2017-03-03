package com.eventra.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eventra.dto.UserDto;
import com.eventra.enums.RolesEnum;
import com.eventra.model.User;
import com.eventra.model.security.Role;
import com.eventra.model.security.UserRole;
import com.eventra.service.UserService;
import com.eventra.utils.UserUtils;

@Controller
public class SignupController {

	private static final Logger LOG = LoggerFactory.getLogger(SignupController.class);

	@Autowired
	private UserService userService;

	public static final String SIGNUP_URL_MAPPING = "/signup";

	private static final String SUBSCRIPTION_VIEW_NAME = "user/signup";

	public static final String USERDTO_MODEL_KEY_NAME = "userDto";

	public static final String DUPLICATED_USERNAME_KEY = "duplicatedUsername";

	public static final String SIGNED_UP_MESSAGE_KEY = "signedUp";

	public static final String ERROR_MESSAGE_KEY = "message";

	@RequestMapping(value = SIGNUP_URL_MAPPING, method = RequestMethod.GET)
	public String signupGet(ModelMap model) {
		model.addAttribute(USERDTO_MODEL_KEY_NAME, new UserDto());
		return SUBSCRIPTION_VIEW_NAME;
	}

	@RequestMapping(value = SIGNUP_URL_MAPPING, method = RequestMethod.POST)
	public String signupPost(@ModelAttribute(USERDTO_MODEL_KEY_NAME) @Valid UserDto userDto, ModelMap model) {
		boolean duplicates = false;
		List<String> errorMessages = new ArrayList<>();

		this.checkForDuplicates(userDto, model);

		if (model.containsKey(DUPLICATED_USERNAME_KEY)) {
			LOG.warn("The username already exists. Displaying error to the user");
			model.addAttribute(SIGNED_UP_MESSAGE_KEY, "false");
			errorMessages.add("Email already exist");
			duplicates = true;
		}

		if (duplicates) {
			model.addAttribute(ERROR_MESSAGE_KEY, errorMessages);
			return SUBSCRIPTION_VIEW_NAME;
		}

		User user = UserUtils.fromUserDtoToDomainUser(userDto);

		User registeredUser = null;

		Set<UserRole> roles = new HashSet<>();
		roles.add(new UserRole(user, new Role(RolesEnum.BASIC)));
		registeredUser = userService.createUser(user, roles);

		// Auto logins the registered user
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null, registeredUser.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);

		LOG.info("User created successfully");

		model.addAttribute(SIGNED_UP_MESSAGE_KEY, "true");

		return SUBSCRIPTION_VIEW_NAME;
	}

	private void checkForDuplicates(UserDto userDto, ModelMap model) {
		if (userService.findByUsername(userDto.getUsername()) != null) {
			model.addAttribute(DUPLICATED_USERNAME_KEY, true);
		}
	}

}
