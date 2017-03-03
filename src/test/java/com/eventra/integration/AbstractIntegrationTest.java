package com.eventra.integration;

import java.util.HashSet;
import java.util.Set;

import org.junit.rules.TestName;
import org.springframework.beans.factory.annotation.Autowired;

import com.eventra.dao.RoleDao;
import com.eventra.dao.UserDao;
import com.eventra.enums.RolesEnum;
import com.eventra.model.User;
import com.eventra.model.security.Role;
import com.eventra.model.security.UserRole;
import com.eventra.utils.UserUtils;

public abstract class AbstractIntegrationTest {
	
	@Autowired
	protected RoleDao roleDao;
	
	@Autowired
	protected UserDao userDao;
	
	protected Role createRole(RolesEnum rolesEnum) {
		return new Role(rolesEnum);
	}

	protected User createUser(String username) {
		// Create User instance and set the Plan saved entity as Foreign Key
		User basicUser = UserUtils.createBasicUser(username);

		Role basicRole = createRole(RolesEnum.BASIC);
		roleDao.save(basicRole);

		// Sets the Set<UserRole> collection in the User entity.
		// We add a UserRole object set with the User and Role objects we've just created
		Set<UserRole> userRoles = new HashSet<>();
		UserRole userRole = new UserRole(basicUser, basicRole);
		userRoles.add(userRole);

		// IMPORTANT!!!
		// To add values to a collection within a JPA Entity,
		// always use the getter method first and all the objects afterwards.
		basicUser.getUserRoles().addAll(userRoles);

		basicUser = userDao.save(basicUser);

		return basicUser;
	}

	protected User createUser(TestName testName) {
		return createUser(testName.getMethodName());
	}
	
}
