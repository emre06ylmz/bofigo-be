package com.bofigo.rowmaterial.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.bofigo.rowmaterial.dao.model.SupplierModel;

@Repository
public interface SupplierRepository
		extends JpaRepository<SupplierModel, Integer>, JpaSpecificationExecutor<SupplierModel> {

	@Override
	Optional<SupplierModel> findById(Integer id);

	SupplierModel findByName(String name);

	@Override
	void deleteAll();

}
