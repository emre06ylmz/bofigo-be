package com.bofigo.rowmaterial.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
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

	@Query("select r from ProductModel r where r.productCategory.id=:productCategoryId")
	public List<ProductModel> listByCategoryId(int productCategoryId);
}
