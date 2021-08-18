package com.bofigo.rowmaterial.domain.service.currencysettings;

import java.util.List;

import com.bofigo.rowmaterial.domain.dto.input.CurrencySettingsServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.CurrencySettingsServiceOutput;
import com.bofigo.rowmaterial.domain.service.Service;
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
