package com.bofigo.rowmaterial.domain.service.productmaterial;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bofigo.rowmaterial.constant.ApplicationConstants;
import com.bofigo.rowmaterial.dao.model.ProductMaterialModel;
import com.bofigo.rowmaterial.dao.repository.ProductMaterialRepository;
import com.bofigo.rowmaterial.dao.repository.ProductRepository;
import com.bofigo.rowmaterial.dao.repository.RawMaterialCategoryRepository;
import com.bofigo.rowmaterial.dao.repository.RawMaterialRepository;
import com.bofigo.rowmaterial.domain.dto.input.ProductMaterialServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.ProductMaterialServiceOutput;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;
import com.bofigo.rowmaterial.mapper.ProductMaterialMapper;

@Service
public class ProductMaterialServiceImpl implements ProductMaterialService {

	private ProductMaterialMapper productMaterialMapper;
	private ProductMaterialRepository productMaterialRepository;

	private ProductRepository productRepository;
	private RawMaterialRepository rawMaterialRepository;
	private RawMaterialCategoryRepository rawMaterialCategoryRepository;

	public ProductMaterialServiceImpl(ProductMaterialRepository productMaterialRepository,
			ProductMaterialMapper productMaterialMapper, ProductRepository productRepository,
			RawMaterialRepository rawMaterialRepository, RawMaterialCategoryRepository rawMaterialCategoryRepository) {
		this.productMaterialRepository = productMaterialRepository;
		this.productMaterialMapper = productMaterialMapper;
		this.productRepository = productRepository;
		this.rawMaterialRepository = rawMaterialRepository;
		this.rawMaterialCategoryRepository = rawMaterialCategoryRepository;
	}

	@Override
	public ProductMaterialServiceOutput getProductMaterialById(Integer id) throws DataNotFoundException {
		ProductMaterialModel productMaterialModel = getProductMaterialModel(id);
		return prepareProductMaterialServiceOutput(productMaterialModel);
	}

	@Override
	public ProductMaterialServiceOutput createProductMaterial(ProductMaterialServiceInput productMaterialServiceInput)
			throws DataAlreadyExistException {

		Optional<ProductMaterialModel> productMaterial = productMaterialRepository.findByProductIdAndRawMaterialId(
				productMaterialServiceInput.getProductId(), productMaterialServiceInput.getRawMaterialId());

		if (productMaterial.isPresent()) {
			throw new DataAlreadyExistException("zaten var");
		}

		ProductMaterialModel insertedProductMaterialModel = insertProductMaterialModel(productMaterialServiceInput);
		return prepareProductMaterialServiceOutput(insertedProductMaterialModel);
	}

	@Override
	public ProductMaterialServiceOutput updateProductMaterial(Integer id,
			ProductMaterialServiceInput productMaterialServiceInput) throws DataNotFoundException {
		Optional<ProductMaterialModel> productMaterialModel = productMaterialRepository.findById(id);

		if (productMaterialModel.isPresent()) {
			ProductMaterialModel updatedProductMaterialModel = updateProductMaterialModel(productMaterialModel.get(),
					productMaterialServiceInput);
			return prepareProductMaterialServiceOutput(updatedProductMaterialModel);
		}

		throw new DataNotFoundException("bulunamadı");
	}

	@Override
	public ProductMaterialServiceOutput deleteProductMaterial(Integer id) throws DataNotFoundException {
		ProductMaterialModel productMaterialModel = getProductMaterialModel(id);
		productMaterialRepository.deleteById(id);
		return productMaterialMapper.mapModelToServiceOutput(productMaterialModel);
	}

	@Override
	public List<ProductMaterialServiceOutput> listAll() {
		List<ProductMaterialModel> productMaterialModelList = productMaterialRepository.findAll();
		return productMaterialMapper.mapModelToServiceOutputList(productMaterialModelList);
	}

	@Override
	public List<ProductMaterialServiceOutput> listByProductId(Integer productId) {
		List<ProductMaterialModel> productMaterialModelList = productMaterialRepository.listByProductId(productId);
		return productMaterialMapper.mapModelToServiceOutputList(productMaterialModelList);
	}

	private ProductMaterialModel getProductMaterialModel(Integer id) throws DataNotFoundException {
		Optional<ProductMaterialModel> productMaterial = productMaterialRepository.findById(id);

		if (!productMaterial.isPresent()) {
			throw new DataNotFoundException("bulunamadı");
		}
		return productMaterial.get();
	}

	public ProductMaterialServiceOutput prepareProductMaterialServiceOutput(ProductMaterialModel productMaterialModel) {
		ProductMaterialServiceOutput productMaterialServiceOutput = new ProductMaterialServiceOutput();

		if (productMaterialModel == null) {
			return productMaterialServiceOutput;
		}

		productMaterialServiceOutput = productMaterialMapper.mapModelToServiceOutput(productMaterialModel);

		return productMaterialServiceOutput;
	}

	public ProductMaterialModel insertProductMaterialModel(ProductMaterialServiceInput productMaterialServiceInput) {
		ProductMaterialModel productMaterialModel = productMaterialMapper
				.mapServiceInputToModel(productMaterialServiceInput);
		productMaterialModel.setStatus(ApplicationConstants.ACTIVE);

		productMaterialModel.setProduct(productRepository.findById(productMaterialServiceInput.getProductId()).get());
		productMaterialModel
				.setRawMaterial(rawMaterialRepository.findById(productMaterialServiceInput.getRawMaterialId()).get());
		productMaterialModel.setRawMaterialCategory(
				rawMaterialCategoryRepository.findById(productMaterialServiceInput.getRawMaterialCategoryId()).get());

		return productMaterialRepository.save(productMaterialModel);
	}

	public ProductMaterialModel updateProductMaterialModel(ProductMaterialModel productMaterialModel,
			ProductMaterialServiceInput productMaterialServiceInput) {
		productMaterialModel.setAmount(productMaterialServiceInput.getAmount());

		return productMaterialRepository.save(productMaterialModel);
	}

	@Override
	public ProductMaterialServiceOutput getProductMaterialByProductIdAndRawMaterialId(Integer productId,
			Integer rawMaterialId) throws DataNotFoundException {

		Optional<ProductMaterialModel> productMaterial = productMaterialRepository
				.findByProductIdAndRawMaterialId(productId, rawMaterialId);

		if (!productMaterial.isPresent()) {
			throw new DataNotFoundException("bulunamadı");
		}
		ProductMaterialModel productMaterialModel = productMaterial.get();

		return prepareProductMaterialServiceOutput(productMaterialModel);
	}

}
