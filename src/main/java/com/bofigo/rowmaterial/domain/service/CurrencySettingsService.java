package com.bofigo.rowmaterial.domain.service;

import java.util.List;

import com.bofigo.rowmaterial.domain.dto.CurrencySettingsServiceInput;
import com.bofigo.rowmaterial.domain.dto.CurrencySettingsServiceOutput;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;

public interface CurrencySettingsService extends Service {

	CurrencySettingsServiceOutput getCurrencySettingsById(Integer id) throws DataNotFoundException;

	CurrencySettingsServiceOutput createCurrencySettings(CurrencySettingsServiceInput userServiceInput)
			throws DataAlreadyExistException;

	CurrencySettingsServiceOutput updateCurrencySettings(Integer id, CurrencySettingsServiceInput userServiceInput)
			throws DataNotFoundException;

	CurrencySettingsServiceOutput deleteCurrencySettings(Integer id) throws DataNotFoundException;

	List<CurrencySettingsServiceOutput> listAll();

}
