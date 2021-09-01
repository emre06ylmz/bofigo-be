package com.bofigo.rowmaterial.domain.service.rawmaterial;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bofigo.rowmaterial.constant.ApplicationConstants;
import com.bofigo.rowmaterial.dao.model.RawMaterialModel;
import com.bofigo.rowmaterial.dao.repository.RawMaterialCategoryRepository;
import com.bofigo.rowmaterial.dao.repository.RawMaterialRepository;
import com.bofigo.rowmaterial.dao.repository.UnitRepository;
import com.bofigo.rowmaterial.domain.dto.input.RawMaterialServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.RawMaterialServiceOutput;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;
import com.bofigo.rowmaterial.mapper.RawMaterialCategoryMapper;
import com.bofigo.rowmaterial.mapper.RawMaterialMapper;
import com.bofigo.rowmaterial.mapper.UnitMapper;

@Service
public class RawMaterialServiceImpl implements RawMaterialService {

	private RawMaterialMapper rawMaterialMapper;
	private RawMaterialRepository rawMaterialRepository;

	private UnitMapper unitMapper;
	private UnitRepository unitRepository;

	private RawMaterialCategoryMapper rawMaterialCategoryMapper;
	private RawMaterialCategoryRepository rawMaterialCategoryRepository;

	public RawMaterialServiceImpl(RawMaterialRepository rawMaterialRepository, RawMaterialMapper rawMaterialMapper,
			UnitRepository unitRepository, UnitMapper unitMapper,
			RawMaterialCategoryRepository rawMaterialCategoryRepository,
			RawMaterialCategoryMapper rawMaterialCategoryMapper) {
		this.rawMaterialRepository = rawMaterialRepository;
		this.rawMaterialMapper = rawMaterialMapper;
		this.unitRepository = unitRepository;
		this.unitMapper = unitMapper;
		this.rawMaterialCategoryRepository = rawMaterialCategoryRepository;
		this.rawMaterialCategoryMapper = rawMaterialCategoryMapper;
	}

	@Override
	public RawMaterialServiceOutput getRawMaterialById(Integer id) throws DataNotFoundException {
		RawMaterialModel rawMaterialModel = getRawMaterialModel(id);
		return prepareRawMaterialServiceOutput(rawMaterialModel);
	}

	@Override
	public RawMaterialServiceOutput getRawMaterialByName(String name) {
		RawMaterialModel rawMaterialModel = rawMaterialRepository.findByName(name);
		return prepareRawMaterialServiceOutput(rawMaterialModel);
	}

	@Override
	public RawMaterialServiceOutput createRawMaterial(RawMaterialServiceInput rawMaterialServiceInput)
			throws DataAlreadyExistException {
		RawMaterialModel rawMaterialModel = rawMaterialRepository.findByName(rawMaterialServiceInput.getName());

		if (rawMaterialModel != null) {
			throw new DataAlreadyExistException("zaten var");
		}

		RawMaterialModel insertedRawMaterialModel = insertRawMaterialModel(rawMaterialServiceInput);

		return prepareRawMaterialServiceOutput(insertedRawMaterialModel);
	}

	@Override
	public RawMaterialServiceOutput updateRawMaterial(Integer id, RawMaterialServiceInput rawMaterialServiceInput)
			throws DataNotFoundException {
		Optional<RawMaterialModel> rawMaterialModel = rawMaterialRepository.findById(id);

		if (rawMaterialModel.isPresent()) {
			RawMaterialModel updatedRawMaterialModel = updateRawMaterialModel(rawMaterialModel.get(),
					rawMaterialServiceInput);
			return prepareRawMaterialServiceOutput(updatedRawMaterialModel);
		}

		throw new DataNotFoundException("bulunamadı");
	}

	@Override
	public RawMaterialServiceOutput deleteRawMaterial(Integer id) throws DataNotFoundException {
		RawMaterialModel rawMaterialModel = getRawMaterialModel(id);
		rawMaterialRepository.deleteById(id);
		return rawMaterialMapper.mapModelToServiceOutput(rawMaterialModel);
	}

	@Override
	public List<RawMaterialServiceOutput> listAll() {
		List<RawMaterialModel> rawMaterialModelList = rawMaterialRepository.findAll();
		return rawMaterialMapper.mapModelToServiceOutputList(rawMaterialModelList);
	}

	@Override
	public List<RawMaterialServiceOutput> listByCategoryId(int rawMaterialCategoryId) {
		List<RawMaterialModel> rawMaterialModelList = rawMaterialRepository.listByCategoryId(rawMaterialCategoryId);
		return rawMaterialMapper.mapModelToServiceOutputList(rawMaterialModelList);
	}

	private RawMaterialModel getRawMaterialModel(Integer id) throws DataNotFoundException {
		Optional<RawMaterialModel> rawMaterial = rawMaterialRepository.findById(id);

		if (!rawMaterial.isPresent()) {
			throw new DataNotFoundException("bulunamadı");
		}

		return rawMaterial.get();
	}

	public RawMaterialServiceOutput prepareRawMaterialServiceOutput(RawMaterialModel rawMaterialModel) {
		RawMaterialServiceOutput rawMaterialServiceOutput = new RawMaterialServiceOutput();

		if (rawMaterialModel == null) {
			return rawMaterialServiceOutput;
		}

		rawMaterialServiceOutput = rawMaterialMapper.mapModelToServiceOutput(rawMaterialModel);

		return rawMaterialServiceOutput;
	}

	public RawMaterialModel insertRawMaterialModel(RawMaterialServiceInput rawMaterialServiceInput) {
		RawMaterialModel rawMaterialModel = rawMaterialMapper.mapServiceInputToModel(rawMaterialServiceInput);
		rawMaterialModel.setStatus(ApplicationConstants.ACTIVE);

		rawMaterialModel.setUnitModel(unitRepository.findById(rawMaterialServiceInput.getUnitId()).get());
		rawMaterialModel.setRawMaterialCategoryModel(
				rawMaterialCategoryRepository.findById(rawMaterialServiceInput.getRawMaterialCategoryId()).get());

		return rawMaterialRepository.save(rawMaterialModel);
	}

	public RawMaterialModel updateRawMaterialModel(RawMaterialModel rawMaterialModel,
			RawMaterialServiceInput rawMaterialServiceInput) {
		int id = rawMaterialModel.getId();
		rawMaterialModel = rawMaterialMapper.mapServiceInputToModel(rawMaterialServiceInput);
		rawMaterialModel.setId(id);
		return rawMaterialRepository.save(rawMaterialModel);
	}

}
