package com.bofigo.rowmaterial.domain.service.product;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bofigo.rowmaterial.constant.ApplicationConstants;
import com.bofigo.rowmaterial.dao.model.CurrencySettingsModel;
import com.bofigo.rowmaterial.dao.model.ProductModel;
import com.bofigo.rowmaterial.dao.repository.CurrencySettingsRepository;
import com.bofigo.rowmaterial.dao.repository.ProductRepository;
import com.bofigo.rowmaterial.domain.dto.input.ProductServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.ProductServiceOutput;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;
import com.bofigo.rowmaterial.mapper.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductMapper productMapper;
	private ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper ) {
		this.productRepository = productRepository;
		this.productMapper = productMapper;
	}

	@Override
	public ProductServiceOutput getProductById(Integer id) throws DataNotFoundException {
		ProductModel productModel = getProductModel(id);
		return prepareProductServiceOutput(productModel);
	}

	@Override
	public ProductServiceOutput getProductByName(String name) {
		ProductModel productModel = productRepository.findByName(name);
		return prepareProductServiceOutput(productModel);
	}

	@Override
	public ProductServiceOutput createProduct(ProductServiceInput productServiceInput)
			throws DataAlreadyExistException {
		ProductModel productModel = productRepository.findByName(productServiceInput.getName());

		if (productModel != null) {
			throw new DataAlreadyExistException("zaten var");
		}

		ProductModel insertedProductModel = insertProductModel(productServiceInput);

		return prepareProductServiceOutput(insertedProductModel);
	}

	@Override
	public ProductServiceOutput updateProduct(Integer id, ProductServiceInput productServiceInput)
			throws DataNotFoundException {
		Optional<ProductModel> productModel = productRepository.findById(id);

		if (productModel.isPresent()) {
			ProductModel updatedProductModel = updateProductModel(productModel.get(), productServiceInput);
			return prepareProductServiceOutput(updatedProductModel);
		}

		throw new DataNotFoundException("bulunamadı");
	}

	@Override
	public ProductServiceOutput deleteProduct(Integer id) throws DataNotFoundException {
		ProductModel productModel = getProductModel(id);
		productRepository.deleteById(id);
		return productMapper.mapModelToServiceOutput(productModel);
	}

	@Override
	public List<ProductServiceOutput> listAll() {
		List<ProductModel> productModelList = productRepository.findAll();
		return productMapper.mapModelToServiceOutputList(productModelList);
	}

	private ProductModel getProductModel(Integer id) throws DataNotFoundException {
		Optional<ProductModel> product = productRepository.findById(id);

		if (!product.isPresent()) {
			throw new DataNotFoundException("bulunamadı");
		}
		return product.get();
	}

	public ProductServiceOutput prepareProductServiceOutput(ProductModel productModel) {
		ProductServiceOutput productServiceOutput = new ProductServiceOutput();

		if (productModel == null) {
			return productServiceOutput;
		}

		productServiceOutput.setName(productModel.getName());
		productServiceOutput.setExplanation(productModel.getExplanation());
		productServiceOutput.setSelectedCurrency(productModel.getSelectedCurrency());

		return productServiceOutput;
	}

	public ProductModel insertProductModel(ProductServiceInput productServiceInput) {
		ProductModel productModel = productMapper.mapServiceInputToModel(productServiceInput);
		productModel.setStatus(ApplicationConstants.ACTIVE);
		
		return productRepository.save(productModel);
	}

	public ProductModel updateProductModel(ProductModel productModel, ProductServiceInput productServiceInput) {
		productModel.setName(productServiceInput.getName());
		productModel.setExplanation(productServiceInput.getExplanation());
		productModel.setSelectedCurrency(productServiceInput.getSelectedCurrency());

		return productRepository.save(productModel);
	}

}
