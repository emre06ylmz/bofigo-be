package com.bofigo.rowmaterial.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.bofigo.rowmaterial.dao.model.ProductModel;

@Repository
public interface ProductRepository
		extends JpaRepository<ProductModel, Integer>, JpaSpecificationExecutor<ProductModel> {

	@Override
	Optional<ProductModel> findById(Integer id);

	ProductModel findByName(String name);

	@Override
	void deleteAll();

}
