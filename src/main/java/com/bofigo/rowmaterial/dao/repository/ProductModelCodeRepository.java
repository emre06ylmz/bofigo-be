package com.bofigo.rowmaterial.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.bofigo.rowmaterial.dao.model.ProductModelCodeModel;

@Repository
public interface ProductModelCodeRepository
		extends JpaRepository<ProductModelCodeModel, Integer>, JpaSpecificationExecutor<ProductModelCodeModel> {

	@Override
	Optional<ProductModelCodeModel> findById(Integer id);

	ProductModelCodeModel findByName(String name);

	@Override
	void deleteAll();

}
