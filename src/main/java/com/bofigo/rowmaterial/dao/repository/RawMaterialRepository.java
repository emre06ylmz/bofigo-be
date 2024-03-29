package com.bofigo.rowmaterial.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bofigo.rowmaterial.dao.model.RawMaterialModel;

@Repository
public interface RawMaterialRepository
		extends JpaRepository<RawMaterialModel, Integer>, JpaSpecificationExecutor<RawMaterialModel> {

	@Override
	Optional<RawMaterialModel> findById(Integer id);

	RawMaterialModel findByName(String name);

	@Override
	void deleteAll();

	@Query( "select r from RawMaterialModel r where r.rawMaterialCategory.id=:rawMaterialCategoryId" )
	public List<RawMaterialModel> listByCategoryId(int rawMaterialCategoryId);

}
