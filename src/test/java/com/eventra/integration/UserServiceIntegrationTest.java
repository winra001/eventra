package com.eventra.integration;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.eventra.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceIntegrationTest extends AbstractIntegrationTest {

	@Rule
	public TestName testName = new TestName();

	@Test
	public void testCreateNewUser() throws Exception {
		User user = createUser(testName);
		Assert.assertNotNull(user);
		Assert.assertNotNull(user.getId());
	}

}
