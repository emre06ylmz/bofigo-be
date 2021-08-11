package com.bofigo.rowmaterial.util;

import com.bofigo.rowmaterial.dao.model.RawMaterialCategoryModel;

public class RawMaterialCategoryRepositoryTestUtil {

	public static RawMaterialCategoryModel getModel() {
		RawMaterialCategoryModel model = new RawMaterialCategoryModel();
		model.setName("Deneme");
		model.setExplanation("Deneme");
		model.prePersist();
		return model;
	}

}
