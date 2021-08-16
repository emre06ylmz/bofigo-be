package com.bofigo.rowmaterial.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.bofigo.rowmaterial.dao.model.PurchaseModel;

@Repository
public interface PurchaseRepository
		extends JpaRepository<PurchaseModel, Integer>, JpaSpecificationExecutor<PurchaseModel> {

	@Override
	Optional<PurchaseModel> findById(Integer id);

	@Override
	void deleteAll();

}
