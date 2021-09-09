package com.bofigo.rowmaterial.domain.service.productmodelcode;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bofigo.rowmaterial.constant.ApplicationConstants;
import com.bofigo.rowmaterial.dao.model.ProductModelCodeModel;
import com.bofigo.rowmaterial.dao.repository.ProductModelCodeRepository;
import com.bofigo.rowmaterial.domain.dto.input.ProductModelCodeServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.ProductModelCodeServiceOutput;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;
import com.bofigo.rowmaterial.mapper.ProductModelCodeMapper;

@Service
public class ProductModelCodeServiceImpl implements ProductModelCodeService {

	private ProductModelCodeMapper productModelCodeMapper;
	private ProductModelCodeRepository productModelCodeRepository;

	public ProductModelCodeServiceImpl(ProductModelCodeRepository productModelCodeRepository,
			ProductModelCodeMapper productModelCodeMapper) {
		this.productModelCodeRepository = productModelCodeRepository;
		this.productModelCodeMapper = productModelCodeMapper;
	}

	@Override
	public ProductModelCodeServiceOutput getProductModelCodeById(Integer id) throws DataNotFoundException {
		ProductModelCodeModel productModelCodeModel = getProductModelCodeModel(id);
		return prepareProductModelCodeServiceOutput(productModelCodeModel);
	}

	@Override
	public ProductModelCodeServiceOutput getProductModelCodeByName(String name) {
		ProductModelCodeModel productModelCodeModel = productModelCodeRepository.findByName(name);
		return prepareProductModelCodeServiceOutput(productModelCodeModel);
	}

	@Override
	public ProductModelCodeServiceOutput createProductModelCode(
			ProductModelCodeServiceInput productModelCodeServiceInput) throws DataAlreadyExistException {
		ProductModelCodeModel productModelCodeModel = productModelCodeRepository
				.findByName(productModelCodeServiceInput.getName());

		if (productModelCodeModel != null) {
			throw new DataAlreadyExistException("zaten var");
		}

		ProductModelCodeModel insertedProductModelCodeModel = insertProductModelCodeModel(productModelCodeServiceInput);

		return prepareProductModelCodeServiceOutput(insertedProductModelCodeModel);
	}

	@Override
	public ProductModelCodeServiceOutput updateProductModelCode(Integer id,
			ProductModelCodeServiceInput productModelCodeServiceInput) throws DataNotFoundException {
		Optional<ProductModelCodeModel> productModelCodeModel = productModelCodeRepository.findById(id);

		if (productModelCodeModel.isPresent()) {
			ProductModelCodeModel updatedProductModelCodeModel = updateProductModelCodeModel(productModelCodeModel.get(),
					productModelCodeServiceInput);
			updatedProductModelCodeModel.setId(id);
			return prepareProductModelCodeServiceOutput(updatedProductModelCodeModel);
		}

		throw new DataNotFoundException("bulunamadı");
	}

	@Override
	public ProductModelCodeServiceOutput deleteProductModelCode(Integer id) throws DataNotFoundException {
		ProductModelCodeModel productModelCodeModel = getProductModelCodeModel(id);
		productModelCodeRepository.deleteById(id);
		return productModelCodeMapper.mapModelToServiceOutput(productModelCodeModel);
	}

	@Override
	public List<ProductModelCodeServiceOutput> listAll() {
		List<ProductModelCodeModel> productModelCodeModelList = productModelCodeRepository.findAll();
		return productModelCodeMapper.mapModelToServiceOutputList(productModelCodeModelList);
	}

	private ProductModelCodeModel getProductModelCodeModel(Integer id) throws DataNotFoundException {
		Optional<ProductModelCodeModel> productModelCode = productModelCodeRepository.findById(id);

		if (!productModelCode.isPresent()) {
			throw new DataNotFoundException("bulunamadı");
		}
		return productModelCode.get();
	}

	public ProductModelCodeServiceOutput prepareProductModelCodeServiceOutput(
			ProductModelCodeModel productModelCodeModel) {
		ProductModelCodeServiceOutput productModelCodeServiceOutput = new ProductModelCodeServiceOutput();

		if (productModelCodeModel == null) {
			return productModelCodeServiceOutput;
		}

		productModelCodeServiceOutput = productModelCodeMapper.mapModelToServiceOutput(productModelCodeModel);

		return productModelCodeServiceOutput;
	}

	public ProductModelCodeModel insertProductModelCodeModel(ProductModelCodeServiceInput productModelCodeServiceInput) {
		ProductModelCodeModel productModelCodeModel = productModelCodeMapper
				.mapServiceInputToModel(productModelCodeServiceInput);
		productModelCodeModel.setStatus(ApplicationConstants.ACTIVE);

		return productModelCodeRepository.save(productModelCodeModel);
	}

	public ProductModelCodeModel updateProductModelCodeModel(ProductModelCodeModel productModelCodeModel,
			ProductModelCodeServiceInput productModelCodeServiceInput) {
		int id = productModelCodeModel.getId();
		productModelCodeModel = productModelCodeMapper.mapServiceInputToModel(productModelCodeServiceInput);
		productModelCodeModel.setId(id);
		return productModelCodeRepository.save(productModelCodeModel);
	}

}
