package com.bofigo.rowmaterial.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.bofigo.rowmaterial.dao.model.UnitModel;

@Repository
public interface UnitRepository extends JpaRepository<UnitModel, Integer>, JpaSpecificationExecutor<UnitModel> {

	@Override
	Optional<UnitModel> findById(Integer id);

	UnitModel findByName(String name);

	@Override
	void deleteAll();
	
}
