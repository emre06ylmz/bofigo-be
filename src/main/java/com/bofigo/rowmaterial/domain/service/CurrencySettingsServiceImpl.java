package com.bofigo.rowmaterial.domain.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bofigo.rowmaterial.constant.ApplicationConstants;
import com.bofigo.rowmaterial.dao.model.CurrencySettingsModel;
import com.bofigo.rowmaterial.dao.repository.CurrencySettingsRepository;
import com.bofigo.rowmaterial.domain.dto.CurrencySettingsServiceInput;
import com.bofigo.rowmaterial.domain.dto.CurrencySettingsServiceOutput;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;
import com.bofigo.rowmaterial.mapper.CurrencySettingsMapper;

@Service
public class CurrencySettingsServiceImpl implements CurrencySettingsService {

	private CurrencySettingsMapper currenySettingsMapper;
	private CurrencySettingsRepository currenySettingsRepository;

	public CurrencySettingsServiceImpl(CurrencySettingsRepository currenySettingsRepository,
			CurrencySettingsMapper currenySettingsMapper) {
		this.currenySettingsRepository = currenySettingsRepository;
		this.currenySettingsMapper = currenySettingsMapper;
	}

	@Override
	public CurrencySettingsServiceOutput getCurrencySettingsById(Integer id) throws DataNotFoundException {
		CurrencySettingsModel currenySettingsModel = getCurrencySettingsModel(id);
		return prepareCurrencySettingsServiceOutput(currenySettingsModel);
	}

	@Override
	public CurrencySettingsServiceOutput createCurrencySettings(
			CurrencySettingsServiceInput currenySettingsServiceInput) throws DataAlreadyExistException {
		CurrencySettingsModel insertedCurrencySettingsModel = insertCurrencySettingsModel(currenySettingsServiceInput);
		return prepareCurrencySettingsServiceOutput(insertedCurrencySettingsModel);
	}

	@Override
	public CurrencySettingsServiceOutput updateCurrencySettings(Integer id,
			CurrencySettingsServiceInput currenySettingsServiceInput) throws DataNotFoundException {
		CurrencySettingsModel currenySettingsModel = getCurrencySettingsModel(id);
		if (currenySettingsModel == null) {
			throw new DataNotFoundException("bulunamadı");
		}

		CurrencySettingsModel updatedCurrencySettingsModel = updateCurrencySettingsModel(currenySettingsModel,
				currenySettingsServiceInput);

		return prepareCurrencySettingsServiceOutput(updatedCurrencySettingsModel);
	}

	@Override
	public CurrencySettingsServiceOutput deleteCurrencySettings(Integer id) throws DataNotFoundException {
		CurrencySettingsModel currenySettingsModel = getCurrencySettingsModel(id);
		currenySettingsRepository.deleteById(id);
		return currenySettingsMapper.mapModelToServiceOutput(currenySettingsModel);
	}

	@Override
	public List<CurrencySettingsServiceOutput> listAll() {
		List<CurrencySettingsModel> currenySettingsModelList = currenySettingsRepository.findAll();
		return currenySettingsMapper.mapModelToServiceOutputList(currenySettingsModelList);
	}

	private CurrencySettingsModel getCurrencySettingsModel(Integer id) throws DataNotFoundException {
		Optional<CurrencySettingsModel> currenySettings = currenySettingsRepository.findById(id);

		if (!currenySettings.isPresent()) {
			throw new DataNotFoundException("bulunamadı");
		}
		return currenySettings.get();
	}

	public CurrencySettingsServiceOutput prepareCurrencySettingsServiceOutput(
			CurrencySettingsModel currenySettingsModel) {
		CurrencySettingsServiceOutput currenySettingsServiceOutput = new CurrencySettingsServiceOutput();

		if (currenySettingsModel == null) {
			return currenySettingsServiceOutput;
		}

		currenySettingsServiceOutput.setDollar(currenySettingsModel.getDollar());
		currenySettingsServiceOutput.setEuro(currenySettingsModel.getEuro());
		currenySettingsServiceOutput.setLastUpdateDate(new Date());

		return currenySettingsServiceOutput;
	}

	public CurrencySettingsModel insertCurrencySettingsModel(CurrencySettingsServiceInput currenySettingsServiceInput) {
		CurrencySettingsModel currenySettingsModel = currenySettingsMapper
				.mapServiceInputToModel(currenySettingsServiceInput);
		currenySettingsModel.setStatus(ApplicationConstants.ACTIVE);

		return currenySettingsRepository.save(currenySettingsModel);
	}

	public CurrencySettingsModel updateCurrencySettingsModel(CurrencySettingsModel currenySettingsModel,
			CurrencySettingsServiceInput currenySettingsServiceInput) {
		currenySettingsModel.setDollar(currenySettingsServiceInput.getDollar());
		currenySettingsModel.setEuro(currenySettingsServiceInput.getEuro());
		currenySettingsModel.setLastUpdateDate(new Date());

		return currenySettingsRepository.save(currenySettingsModel);
	}

}
