package com.bofigo.rowmaterial.util;

import com.bofigo.rowmaterial.constant.ApplicationConstants;
import com.bofigo.rowmaterial.dao.model.UserTypeModel;

public class UserTypeRepositoryTestUtil {

	public static UserTypeModel getModel() {
		UserTypeModel model = new UserTypeModel();
		model.setName(ApplicationConstants.ROLE_DEVELOPER);
		model.setDetail(ApplicationConstants.ROLE_DEVELOPER);
		;
		model.prePersist();
		return model;
	}

}
