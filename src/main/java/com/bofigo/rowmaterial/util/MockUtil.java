package com.bofigo.rowmaterial.util;

import com.bofigo.rowmaterial.constant.ApplicationConstants;
import com.bofigo.rowmaterial.dao.model.UserModel;

public class MockUtil {

	public static UserModel mockUser() {
		UserModel userModel = new UserModel();
		userModel.setUsername("ali");
		userModel.setPassword("ali");
		userModel.setName("ali");
		userModel.setSurname("ali");
		userModel.setRole(ApplicationConstants.ROLE_DEVELOPER);
		return userModel;
	}

}
