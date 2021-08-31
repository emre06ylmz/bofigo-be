package com.bofigo.rowmaterial.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bofigo.rowmaterial.dao.model.ProductionModel;

@Repository
public interface ProductionRepository
		extends JpaRepository<ProductionModel, Integer>, JpaSpecificationExecutor<ProductionModel> {

	@Override
	Optional<ProductionModel> findById(Integer id);

	Optional<ProductionModel> findByProductId(Integer productId);

	@Override
	void deleteAll();

	@Query("select r from ProductionModel r where r.product.id=:productId")
	List<ProductionModel> listByProductId(Integer productId);

}
