package com.bofigo.rowmaterial.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.bofigo.rowmaterial.dao.model.RawMaterialCategoryModel;

@Repository
public interface RawMaterialCategoryRepository
		extends JpaRepository<RawMaterialCategoryModel, Integer>, JpaSpecificationExecutor<RawMaterialCategoryModel> {

	@Override
	Optional<RawMaterialCategoryModel> findById(Integer id);

	RawMaterialCategoryModel findByName(String name);

	@Override
	void deleteAll();

}
