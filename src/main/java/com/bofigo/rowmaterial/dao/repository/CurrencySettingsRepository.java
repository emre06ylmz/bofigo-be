package com.bofigo.rowmaterial.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.bofigo.rowmaterial.dao.model.CurrencySettingsModel;

@Repository
public interface CurrencySettingsRepository
		extends JpaRepository<CurrencySettingsModel, Integer>, JpaSpecificationExecutor<CurrencySettingsModel> {

	@Override
	Optional<CurrencySettingsModel> findById(Integer id);

	@Override
	void deleteAll();

}
