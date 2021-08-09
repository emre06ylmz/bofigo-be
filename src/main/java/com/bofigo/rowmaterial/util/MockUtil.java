package com.bofigo.rowmaterial.util;

import com.bofigo.rowmaterial.dao.model.UserModel;

public class MockUtil {

	public static UserModel mockUser() {
		UserModel userModel = new UserModel();
		userModel.setUsername("emre");
		userModel.setPassword("emre");
		userModel.setName("emre");
		userModel.setSurname("emre");
		userModel.setRole("USER");
		return userModel;
	}

}
