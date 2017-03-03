package com.eventra.integration;

import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.eventra.enums.RolesEnum;
import com.eventra.model.User;
import com.eventra.model.security.Role;
import com.eventra.model.security.UserRole;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoIntegrationTest extends AbstractIntegrationTest {

	@Rule
	public TestName testName = new TestName();

	@Before
	public void init() {
		Assert.assertNotNull(roleDao);
		Assert.assertNotNull(userDao);
	}

	@Test
	public void testCreateNewRole() throws Exception {
		Role basicRole = createRole(RolesEnum.BASIC);
		roleDao.save(basicRole);
		Role retrievedRole = roleDao.findOne(RolesEnum.BASIC.getId());
		Assert.assertNotNull(retrievedRole);
	}

	@Test
	public void createNewUser() throws Exception {
		String username = testName.getMethodName();

		User basicUser = createUser(username);

		User newlyCreatedUser = userDao.findOne(basicUser.getId());

		// If all relationships contain data after running the findOne method,
		// it means our Repositories work correctly.
		Assert.assertNotNull(newlyCreatedUser);
		Assert.assertTrue(newlyCreatedUser.getId() != 0);
		Set<UserRole> newlyCreatedUserRoles = newlyCreatedUser.getUserRoles();
		for (UserRole ur : newlyCreatedUserRoles) {
			Assert.assertNotNull(ur.getRole());
			Assert.assertNotNull(ur.getRole().getId());
		}
	}

}
