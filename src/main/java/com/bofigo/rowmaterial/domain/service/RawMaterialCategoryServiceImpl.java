package com.bofigo.rowmaterial.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bofigo.rowmaterial.constant.ApplicationConstants;
import com.bofigo.rowmaterial.dao.model.RawMaterialCategoryModel;
import com.bofigo.rowmaterial.dao.repository.RawMaterialCategoryRepository;
import com.bofigo.rowmaterial.domain.dto.RawMaterialCategoryServiceInput;
import com.bofigo.rowmaterial.domain.dto.RawMaterialCategoryServiceOutput;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;
import com.bofigo.rowmaterial.mapper.RawMaterialCategoryMapper;

@Service
public class RawMaterialCategoryServiceImpl implements RawMaterialCategoryService {

	private RawMaterialCategoryMapper rawMaterialCategoryMapper;
	private RawMaterialCategoryRepository rawMaterialCategoryRepository;

	public RawMaterialCategoryServiceImpl(RawMaterialCategoryRepository rawMaterialCategoryRepository,
			RawMaterialCategoryMapper rawMaterialCategoryMapper) {
		this.rawMaterialCategoryRepository = rawMaterialCategoryRepository;
		this.rawMaterialCategoryMapper = rawMaterialCategoryMapper;
	}

	@Override
	public RawMaterialCategoryServiceOutput getRawMaterialCategoryById(Integer id) throws DataNotFoundException {
		RawMaterialCategoryModel rawMaterialCategoryModel = getRawMaterialCategoryModel(id);
		return prepareRawMaterialCategoryServiceOutput(rawMaterialCategoryModel);
	}

	@Override
	public RawMaterialCategoryServiceOutput getRawMaterialCategoryByName(String name) {
		RawMaterialCategoryModel rawMaterialCategoryModel = rawMaterialCategoryRepository.findByName(name);
		return prepareRawMaterialCategoryServiceOutput(rawMaterialCategoryModel);
	}

	@Override
	public RawMaterialCategoryServiceOutput createRawMaterialCategory(
			RawMaterialCategoryServiceInput rawMaterialCategoryServiceInput) throws DataAlreadyExistException {
		RawMaterialCategoryModel rawMaterialCategoryModel = rawMaterialCategoryRepository
				.findByName(rawMaterialCategoryServiceInput.getName());

		if (rawMaterialCategoryModel != null) {
			throw new DataAlreadyExistException("zaten var");
		}

		RawMaterialCategoryModel insertedRawMaterialCategoryModel = insertRawMaterialCategoryModel(
				rawMaterialCategoryServiceInput);

		return prepareRawMaterialCategoryServiceOutput(insertedRawMaterialCategoryModel);
	}

	@Override
	public RawMaterialCategoryServiceOutput updateRawMaterialCategory(Integer id,
			RawMaterialCategoryServiceInput rawMaterialCategoryServiceInput) throws DataNotFoundException {
		Optional<RawMaterialCategoryModel> rawMaterialCategoryModel = rawMaterialCategoryRepository.findById(id);

		if (rawMaterialCategoryModel.isPresent()) {
			RawMaterialCategoryModel updatedRawMaterialCategoryModel = updateRawMaterialCategoryModel(
					rawMaterialCategoryModel.get(), rawMaterialCategoryServiceInput);
			return prepareRawMaterialCategoryServiceOutput(updatedRawMaterialCategoryModel);
		}

		throw new DataNotFoundException("bulunamadı");
	}

	@Override
	public RawMaterialCategoryServiceOutput deleteRawMaterialCategory(Integer id) throws DataNotFoundException {
		RawMaterialCategoryModel rawMaterialCategoryModel = getRawMaterialCategoryModel(id);
		rawMaterialCategoryRepository.deleteById(id);
		return rawMaterialCategoryMapper.mapModelToServiceOutput(rawMaterialCategoryModel);
	}

	@Override
	public List<RawMaterialCategoryServiceOutput> listAll() {
		List<RawMaterialCategoryModel> rawMaterialCategoryModelList = rawMaterialCategoryRepository.findAll();
		return rawMaterialCategoryMapper.mapModelToServiceOutputList(rawMaterialCategoryModelList);
	}

	private RawMaterialCategoryModel getRawMaterialCategoryModel(Integer id) throws DataNotFoundException {
		Optional<RawMaterialCategoryModel> rawMaterialCategory = rawMaterialCategoryRepository.findById(id);

		if (!rawMaterialCategory.isPresent()) {
			throw new DataNotFoundException("bulunamadı");
		}
		return rawMaterialCategory.get();
	}

	public RawMaterialCategoryServiceOutput prepareRawMaterialCategoryServiceOutput(
			RawMaterialCategoryModel rawMaterialCategoryModel) {
		RawMaterialCategoryServiceOutput rawMaterialCategoryServiceOutput = new RawMaterialCategoryServiceOutput();

		if (rawMaterialCategoryModel == null) {
			return rawMaterialCategoryServiceOutput;
		}

		rawMaterialCategoryServiceOutput.setName(rawMaterialCategoryModel.getName());

		return rawMaterialCategoryServiceOutput;
	}

	public RawMaterialCategoryModel insertRawMaterialCategoryModel(
			RawMaterialCategoryServiceInput rawMaterialCategoryServiceInput) {
		RawMaterialCategoryModel rawMaterialCategoryModel = rawMaterialCategoryMapper
				.mapServiceInputToModel(rawMaterialCategoryServiceInput);
		rawMaterialCategoryModel.setStatus(ApplicationConstants.ACTIVE);

		return rawMaterialCategoryRepository.save(rawMaterialCategoryModel);
	}

	public RawMaterialCategoryModel updateRawMaterialCategoryModel(RawMaterialCategoryModel rawMaterialCategoryModel,
			RawMaterialCategoryServiceInput rawMaterialCategoryServiceInput) {
		rawMaterialCategoryModel.setName(rawMaterialCategoryServiceInput.getName());

		return rawMaterialCategoryRepository.save(rawMaterialCategoryModel);
	}

}
