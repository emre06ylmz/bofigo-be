package com.bofigo.rowmaterial.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bofigo.rowmaterial.constant.ApplicationConstants;
import com.bofigo.rowmaterial.dao.model.UserModel;

public class MockUtilTest {

	private UserModel userModel;

	@BeforeEach
	public void setUp() {
		userModel = new UserModel();
		userModel.setUsername("ali");
		userModel.setPassword("ali");
		userModel.setName("ali");
		userModel.setSurname("ali");
		userModel.setRole(ApplicationConstants.ROLE_DEVELOPER);
	}

	@Test
	public void test_mockUser() {
		UserModel mockUser = MockUtil.mockUser();

		assertEquals(userModel.getUsername(), mockUser.getUsername());
		assertEquals(userModel.getPassword(), mockUser.getPassword());
		assertEquals(userModel.getName(), mockUser.getName());
		assertEquals(userModel.getSurname(), mockUser.getSurname());
		assertEquals(userModel.getRole(), mockUser.getRole());
	}

}
