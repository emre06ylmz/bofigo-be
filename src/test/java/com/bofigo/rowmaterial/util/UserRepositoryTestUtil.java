package com.bofigo.rowmaterial.util;

import com.bofigo.rowmaterial.constant.ApplicationConstants;
import com.bofigo.rowmaterial.dao.model.UserModel;

public class UserRepositoryTestUtil {

	public static UserModel getModel() {
		UserModel model = new UserModel();
		model.setName("Deneme");
		model.setPassword("Deneme");
		model.setRole(ApplicationConstants.ROLE_DEVELOPER);
		model.setStatus(ApplicationConstants.ACTIVE);
		model.setSurname("Deneme");
		model.setUsername("Deneme");
		model.prePersist();
		return model;
	}

}
