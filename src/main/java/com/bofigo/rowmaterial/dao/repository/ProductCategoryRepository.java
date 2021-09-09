package com.bofigo.rowmaterial.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.bofigo.rowmaterial.dao.model.ProductCategoryModel;

@Repository
public interface ProductCategoryRepository
		extends JpaRepository<ProductCategoryModel, Integer>, JpaSpecificationExecutor<ProductCategoryModel> {

	@Override
	Optional<ProductCategoryModel> findById(Integer id);

	ProductCategoryModel findByName(String name);

	@Override
	void deleteAll();

}
