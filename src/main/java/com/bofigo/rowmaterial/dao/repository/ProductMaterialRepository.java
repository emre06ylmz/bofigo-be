package com.bofigo.rowmaterial.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bofigo.rowmaterial.dao.model.ProductMaterialModel;

@Repository
public interface ProductMaterialRepository
		extends JpaRepository<ProductMaterialModel, Integer>, JpaSpecificationExecutor<ProductMaterialModel> {

	@Override
	Optional<ProductMaterialModel> findById(Integer id);

	Optional<ProductMaterialModel> findByProductIdAndRawMaterialId(Integer productId, Integer rawMaterialId);

	@Override
	void deleteAll();

	@Query("select r from ProductMaterialModel r where r.product.id=:productId")
	List<ProductMaterialModel> listByProductId(Integer productId);

}
