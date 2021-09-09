package com.bofigo.rowmaterial.domain.service.productcategory;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bofigo.rowmaterial.constant.ApplicationConstants;
import com.bofigo.rowmaterial.dao.model.ProductCategoryModel;
import com.bofigo.rowmaterial.dao.repository.ProductCategoryRepository;
import com.bofigo.rowmaterial.domain.dto.input.ProductCategoryServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.ProductCategoryServiceOutput;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;
import com.bofigo.rowmaterial.mapper.ProductCategoryMapper;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	private ProductCategoryMapper productCategoryMapper;
	private ProductCategoryRepository productCategoryRepository;

	public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository,
			ProductCategoryMapper productCategoryMapper) {
		this.productCategoryRepository = productCategoryRepository;
		this.productCategoryMapper = productCategoryMapper;
	}

	@Override
	public ProductCategoryServiceOutput getProductCategoryById(Integer id) throws DataNotFoundException {
		ProductCategoryModel productCategoryModel = getProductCategoryModel(id);
		return prepareProductCategoryServiceOutput(productCategoryModel);
	}

	@Override
	public ProductCategoryServiceOutput getProductCategoryByName(String name) {
		ProductCategoryModel productCategoryModel = productCategoryRepository.findByName(name);
		return prepareProductCategoryServiceOutput(productCategoryModel);
	}

	@Override
	public ProductCategoryServiceOutput createProductCategory(ProductCategoryServiceInput productCategoryServiceInput)
			throws DataAlreadyExistException {
		ProductCategoryModel productCategoryModel = productCategoryRepository
				.findByName(productCategoryServiceInput.getName());

		if (productCategoryModel != null) {
			throw new DataAlreadyExistException("zaten var");
		}

		ProductCategoryModel insertedProductCategoryModel = insertProductCategoryModel(productCategoryServiceInput);

		return prepareProductCategoryServiceOutput(insertedProductCategoryModel);
	}

	@Override
	public ProductCategoryServiceOutput updateProductCategory(Integer id,
			ProductCategoryServiceInput productCategoryServiceInput) throws DataNotFoundException {
		Optional<ProductCategoryModel> productCategoryModel = productCategoryRepository.findById(id);

		if (productCategoryModel.isPresent()) {
			ProductCategoryModel updatedProductCategoryModel = updateProductCategoryModel(productCategoryModel.get(),
					productCategoryServiceInput);
			updatedProductCategoryModel.setId(id);
			return prepareProductCategoryServiceOutput(updatedProductCategoryModel);
		}

		throw new DataNotFoundException("bulunamadı");
	}

	@Override
	public ProductCategoryServiceOutput deleteProductCategory(Integer id) throws DataNotFoundException {
		ProductCategoryModel productCategoryModel = getProductCategoryModel(id);
		productCategoryRepository.deleteById(id);
		return productCategoryMapper.mapModelToServiceOutput(productCategoryModel);
	}

	@Override
	public List<ProductCategoryServiceOutput> listAll() {
		List<ProductCategoryModel> productCategoryModelList = productCategoryRepository.findAll();
		return productCategoryMapper.mapModelToServiceOutputList(productCategoryModelList);
	}

	private ProductCategoryModel getProductCategoryModel(Integer id) throws DataNotFoundException {
		Optional<ProductCategoryModel> productCategory = productCategoryRepository.findById(id);

		if (!productCategory.isPresent()) {
			throw new DataNotFoundException("bulunamadı");
		}
		return productCategory.get();
	}

	public ProductCategoryServiceOutput prepareProductCategoryServiceOutput(ProductCategoryModel productCategoryModel) {
		ProductCategoryServiceOutput productCategoryServiceOutput = new ProductCategoryServiceOutput();

		if (productCategoryModel == null) {
			return productCategoryServiceOutput;
		}

		productCategoryServiceOutput = productCategoryMapper.mapModelToServiceOutput(productCategoryModel);

		return productCategoryServiceOutput;
	}

	public ProductCategoryModel insertProductCategoryModel(ProductCategoryServiceInput productCategoryServiceInput) {
		ProductCategoryModel productCategoryModel = productCategoryMapper
				.mapServiceInputToModel(productCategoryServiceInput);
		productCategoryModel.setStatus(ApplicationConstants.ACTIVE);

		return productCategoryRepository.save(productCategoryModel);
	}

	public ProductCategoryModel updateProductCategoryModel(ProductCategoryModel productCategoryModel,
			ProductCategoryServiceInput productCategoryServiceInput) {
		int id = productCategoryModel.getId();
		productCategoryModel = productCategoryMapper.mapServiceInputToModel(productCategoryServiceInput);
		productCategoryModel.setId(id);
		return productCategoryRepository.save(productCategoryModel);
	}

}
