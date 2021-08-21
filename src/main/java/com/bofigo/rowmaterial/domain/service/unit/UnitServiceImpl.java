package com.bofigo.rowmaterial.domain.service.unit;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bofigo.rowmaterial.constant.ApplicationConstants;
import com.bofigo.rowmaterial.dao.model.UnitModel;
import com.bofigo.rowmaterial.dao.repository.UnitRepository;
import com.bofigo.rowmaterial.domain.dto.input.UnitServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.UnitServiceOutput;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;
import com.bofigo.rowmaterial.mapper.UnitMapper;

@Service
public class UnitServiceImpl implements UnitService {

	private UnitMapper unitMapper;
	private UnitRepository unitRepository;

	public UnitServiceImpl(UnitRepository unitRepository,
			UnitMapper unitMapper) {
		this.unitRepository = unitRepository;
		this.unitMapper = unitMapper;
	}

	@Override
	public UnitServiceOutput getUnitById(Integer id) throws DataNotFoundException {
		UnitModel unitModel = getUnitModel(id);
		return prepareUnitServiceOutput(unitModel);
	}

	@Override
	public UnitServiceOutput getUnitByName(String name) {
		UnitModel unitModel = unitRepository.findByName(name);
		return prepareUnitServiceOutput(unitModel);
	}

	@Override
	public UnitServiceOutput createUnit(
			UnitServiceInput unitServiceInput) throws DataAlreadyExistException {
		UnitModel unitModel = unitRepository
				.findByName(unitServiceInput.getName());

		if (unitModel != null) {
			throw new DataAlreadyExistException("zaten var");
		}

		UnitModel insertedUnitModel = insertUnitModel(
				unitServiceInput);

		return prepareUnitServiceOutput(insertedUnitModel);
	}

	@Override
	public UnitServiceOutput updateUnit(Integer id,
			UnitServiceInput unitServiceInput) throws DataNotFoundException {
		Optional<UnitModel> unitModel = unitRepository.findById(id);

		if (unitModel.isPresent()) {
			UnitModel updatedUnitModel = updateUnitModel(
					unitModel.get(), unitServiceInput);
			return prepareUnitServiceOutput(updatedUnitModel);
		}

		throw new DataNotFoundException("bulunamadı");
	}

	@Override
	public UnitServiceOutput deleteUnit(Integer id) throws DataNotFoundException {
		UnitModel unitModel = getUnitModel(id);
		unitRepository.deleteById(id);
		return unitMapper.mapModelToServiceOutput(unitModel);
	}

	@Override
	public List<UnitServiceOutput> listAll() {
		List<UnitModel> unitModelList = unitRepository.findAll();
		return unitMapper.mapModelToServiceOutputList(unitModelList);
	}

	private UnitModel getUnitModel(Integer id) throws DataNotFoundException {
		Optional<UnitModel> unit = unitRepository.findById(id);

		if (!unit.isPresent()) {
			throw new DataNotFoundException("bulunamadı");
		}
		return unit.get();
	}

	public UnitServiceOutput prepareUnitServiceOutput(
			UnitModel unitModel) {
		UnitServiceOutput unitServiceOutput = new UnitServiceOutput();

		if (unitModel == null) {
			return unitServiceOutput;
		}

		unitServiceOutput = unitMapper.mapModelToServiceOutput(unitModel);

		return unitServiceOutput;
	}

	public UnitModel insertUnitModel(
			UnitServiceInput unitServiceInput) {
		UnitModel unitModel = unitMapper
				.mapServiceInputToModel(unitServiceInput);
		unitModel.setStatus(ApplicationConstants.ACTIVE);

		return unitRepository.save(unitModel);
	}

	public UnitModel updateUnitModel(UnitModel unitModel,
			UnitServiceInput unitServiceInput) {
		unitModel.setName(unitServiceInput.getName());
		unitModel.setExplanation(unitServiceInput.getExplanation());

		return unitRepository.save(unitModel);
	}

}
