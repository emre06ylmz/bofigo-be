package com.bofigo.rowmaterial.dao.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class BaseModelTest {

	@Test
	public void test_prePersist() {
		BaseModel model = new UserModel();
		model.prePersist();

		assertNotNull(model.getCreateDate());
		assertNotNull(model.getCreatedBy());
		assertNotNull(model.getStatus());
		assertNotNull(model.getUpdateDate());
		assertNotNull(model.getUpdatedBy());
	}

	@Test
	public void test_preUpdate() {
		BaseModel model = new UserModel();
		model.preUpdate();

		assertNotNull(model.getStatus());
		assertNotNull(model.getUpdateDate());
		assertNotNull(model.getUpdatedBy());
	}

}
