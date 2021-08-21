package com.bofigo.rowmaterial.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bofigo.rowmaterial.dao.model.PurchaseModel;
import com.bofigo.rowmaterial.dao.model.RawMaterialModel;

@Repository
public interface PurchaseRepository
		extends JpaRepository<PurchaseModel, Integer>, JpaSpecificationExecutor<PurchaseModel> {

	@Override
	Optional<PurchaseModel> findById(Integer id);

	@Override
	void deleteAll();

	@Query( "select r from PurchaseModel r where r.rawMaterial.id=:rawMaterialId" )
	public List<PurchaseModel> listByMaterialId(int rawMaterialId);
}
