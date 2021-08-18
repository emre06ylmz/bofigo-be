package com.bofigo.rowmaterial.domain.service.supplier;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bofigo.rowmaterial.constant.ApplicationConstants;
import com.bofigo.rowmaterial.dao.model.SupplierModel;
import com.bofigo.rowmaterial.dao.repository.SupplierRepository;
import com.bofigo.rowmaterial.domain.dto.input.SupplierServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.SupplierServiceOutput;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;
import com.bofigo.rowmaterial.mapper.SupplierMapper;

@Service
public class SupplierServiceImpl implements SupplierService {

	private SupplierMapper supplierMapper;
	private SupplierRepository supplierRepository;

	public SupplierServiceImpl(SupplierRepository supplierRepository,
			SupplierMapper supplierMapper) {
		this.supplierRepository = supplierRepository;
		this.supplierMapper = supplierMapper;
	}

	@Override
	public SupplierServiceOutput getSupplierById(Integer id) throws DataNotFoundException {
		SupplierModel supplierModel = getSupplierModel(id);
		return prepareSupplierServiceOutput(supplierModel);
	}

	@Override
	public SupplierServiceOutput getSupplierByName(String name) {
		SupplierModel supplierModel = supplierRepository.findByName(name);
		return prepareSupplierServiceOutput(supplierModel);
	}

	@Override
	public SupplierServiceOutput createSupplier(
			SupplierServiceInput supplierServiceInput) throws DataAlreadyExistException {
		SupplierModel supplierModel = supplierRepository
				.findByName(supplierServiceInput.getName());

		if (supplierModel != null) {
			throw new DataAlreadyExistException("zaten var");
		}

		SupplierModel insertedSupplierModel = insertSupplierModel(
				supplierServiceInput);

		return prepareSupplierServiceOutput(insertedSupplierModel);
	}

	@Override
	public SupplierServiceOutput updateSupplier(Integer id,
			SupplierServiceInput supplierServiceInput) throws DataNotFoundException {
		Optional<SupplierModel> supplierModel = supplierRepository.findById(id);

		if (supplierModel.isPresent()) {
			SupplierModel updatedSupplierModel = updateSupplierModel(
					supplierModel.get(), supplierServiceInput);
			return prepareSupplierServiceOutput(updatedSupplierModel);
		}

		throw new DataNotFoundException("bulunamadı");
	}

	@Override
	public SupplierServiceOutput deleteSupplier(Integer id) throws DataNotFoundException {
		SupplierModel supplierModel = getSupplierModel(id);
		supplierRepository.deleteById(id);
		return supplierMapper.mapModelToServiceOutput(supplierModel);
	}

	@Override
	public List<SupplierServiceOutput> listAll() {
		List<SupplierModel> supplierModelList = supplierRepository.findAll();
		return supplierMapper.mapModelToServiceOutputList(supplierModelList);
	}

	private SupplierModel getSupplierModel(Integer id) throws DataNotFoundException {
		Optional<SupplierModel> supplier = supplierRepository.findById(id);

		if (!supplier.isPresent()) {
			throw new DataNotFoundException("bulunamadı");
		}
		return supplier.get();
	}

	public SupplierServiceOutput prepareSupplierServiceOutput(
			SupplierModel supplierModel) {
		SupplierServiceOutput supplierServiceOutput = new SupplierServiceOutput();

		if (supplierModel == null) {
			return supplierServiceOutput;
		}

		supplierServiceOutput.setName(supplierModel.getName());

		return supplierServiceOutput;
	}

	public SupplierModel insertSupplierModel(
			SupplierServiceInput supplierServiceInput) {
		SupplierModel supplierModel = supplierMapper
				.mapServiceInputToModel(supplierServiceInput);
		supplierModel.setStatus(ApplicationConstants.ACTIVE);

		return supplierRepository.save(supplierModel);
	}

	public SupplierModel updateSupplierModel(SupplierModel supplierModel,
			SupplierServiceInput supplierServiceInput) {
		supplierModel.setName(supplierServiceInput.getName());
		supplierModel.setExplanation(supplierServiceInput.getExplanation());

		return supplierRepository.save(supplierModel);
	}

}
