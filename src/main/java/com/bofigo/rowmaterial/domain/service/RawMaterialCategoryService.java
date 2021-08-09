package com.bofigo.rowmaterial.domain.service;

import java.util.List;

import com.bofigo.rowmaterial.domain.dto.RawMaterialCategoryServiceInput;
import com.bofigo.rowmaterial.domain.dto.RawMaterialCategoryServiceOutput;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;

public interface RawMaterialCategoryService extends Service {

	RawMaterialCategoryServiceOutput getRawMaterialCategoryById(Integer id) throws DataNotFoundException;

	RawMaterialCategoryServiceOutput getRawMaterialCategoryByName(String name);

	RawMaterialCategoryServiceOutput createRawMaterialCategory(
			RawMaterialCategoryServiceInput rawMaterialCategoryServiceInput) throws DataAlreadyExistException;

	RawMaterialCategoryServiceOutput updateRawMaterialCategory(Integer id,
			RawMaterialCategoryServiceInput rawMaterialCategoryServiceInput) throws DataNotFoundException;

	RawMaterialCategoryServiceOutput deleteRawMaterialCategory(Integer id) throws DataNotFoundException;

	List<RawMaterialCategoryServiceOutput> listAll();

}
