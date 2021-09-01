package com.bofigo.rowmaterial.domain.service.production;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bofigo.rowmaterial.constant.ApplicationConstants;
import com.bofigo.rowmaterial.dao.model.ProductionModel;
import com.bofigo.rowmaterial.dao.model.RawMaterialModel;
import com.bofigo.rowmaterial.dao.repository.ProductRepository;
import com.bofigo.rowmaterial.dao.repository.ProductionRepository;
import com.bofigo.rowmaterial.dao.repository.RawMaterialCategoryRepository;
import com.bofigo.rowmaterial.dao.repository.RawMaterialRepository;
import com.bofigo.rowmaterial.domain.dto.input.ProductionServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.ProductionServiceOutput;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;
import com.bofigo.rowmaterial.mapper.ProductionMapper;

@Service
public class ProductionServiceImpl implements ProductionService {

	private ProductionMapper productionMapper;
	private ProductionRepository productionRepository;

	private ProductRepository productRepository;
	private RawMaterialRepository rawMaterialRepository;
	private RawMaterialCategoryRepository rawMaterialCategoryRepository;

	public ProductionServiceImpl(ProductionRepository productionRepository, ProductionMapper productionMapper,
			ProductRepository productRepository, RawMaterialRepository rawMaterialRepository,
			RawMaterialCategoryRepository rawMaterialCategoryRepository) {
		this.productionRepository = productionRepository;
		this.productionMapper = productionMapper;
		this.productRepository = productRepository;
		this.rawMaterialRepository = rawMaterialRepository;
		this.rawMaterialCategoryRepository = rawMaterialCategoryRepository;
	}

	@Override
	public ProductionServiceOutput getProductionById(Integer id) throws DataNotFoundException {
		ProductionModel productionModel = getProductionModel(id);
		return prepareProductionServiceOutput(productionModel);
	}

	@Override
	public ProductionServiceOutput createProduction(ProductionServiceInput productionServiceInput)
			throws DataAlreadyExistException {
		ProductionModel insertedProductionModel = insertProductionModel(productionServiceInput);
		return prepareProductionServiceOutput(insertedProductionModel);
	}

	@Override
	public ProductionServiceOutput updateProduction(Integer id, ProductionServiceInput productionServiceInput)
			throws DataNotFoundException {
		Optional<ProductionModel> productionModel = productionRepository.findById(id);

		if (productionModel.isPresent()) {
			ProductionModel updatedProductionModel = updateProductionModel(productionModel.get(),
					productionServiceInput);
			return prepareProductionServiceOutput(updatedProductionModel);
		}

		throw new DataNotFoundException("bulunamadı");
	}

	@Override
	public ProductionServiceOutput deleteProduction(Integer id) throws DataNotFoundException {
		ProductionModel productionModel = getProductionModel(id);
		productionRepository.deleteById(id);
		return productionMapper.mapModelToServiceOutput(productionModel);
	}

	@Override
	public List<ProductionServiceOutput> listAll() {
		List<ProductionModel> productionModelList = productionRepository.findAll();
		return productionMapper.mapModelToServiceOutputList(productionModelList);
	}

	@Override
	public List<ProductionServiceOutput> listByProductId(Integer productId) {
		List<ProductionModel> productionModelList = productionRepository.listByProductId(productId);
		return productionMapper.mapModelToServiceOutputList(productionModelList);
	}

	private ProductionModel getProductionModel(Integer id) throws DataNotFoundException {
		Optional<ProductionModel> production = productionRepository.findById(id);

		if (!production.isPresent()) {
			throw new DataNotFoundException("bulunamadı");
		}
		return production.get();
	}

	public ProductionServiceOutput prepareProductionServiceOutput(ProductionModel productionModel) {
		ProductionServiceOutput productionServiceOutput = new ProductionServiceOutput();

		if (productionModel == null) {
			return productionServiceOutput;
		}

		productionServiceOutput = productionMapper.mapModelToServiceOutput(productionModel);

		return productionServiceOutput;
	}

	public ProductionModel insertProductionModel(ProductionServiceInput productionServiceInput) {
		ProductionModel productionModel = productionMapper.mapServiceInputToModel(productionServiceInput);
		productionModel.setStatus(ApplicationConstants.ACTIVE);
		productionModel.setDate(new Date());
		productionModel.setProduct(productRepository.findById(productionServiceInput.getProductId()).get());

		RawMaterialModel rawMaterial = rawMaterialRepository.findById(purchaseServiceInput.getRawMaterialId()).get();
		rawMaterial.setStock(rawMaterial.getStock() + purchaseServiceInput.getAmount());
		
		return productionRepository.save(productionModel);
	}

	public ProductionModel updateProductionModel(ProductionModel productionModel,
			ProductionServiceInput productionServiceInput) {
		int id = productionModel.getId();
		productionModel = productionMapper.mapServiceInputToModel(productionServiceInput);
		productionModel.setProduct(productRepository.findById(productionServiceInput.getProductId()).get());
		productionModel.setId(id);
		productionModel.setDate(new Date());
		return productionRepository.save(productionModel);
	}

	@Override
	public ProductionServiceOutput getProductionByProductId(Integer productId) throws DataNotFoundException {

		Optional<ProductionModel> production = productionRepository.findByProductId(productId);

		if (!production.isPresent()) {
			throw new DataNotFoundException("bulunamadı");
		}
		ProductionModel productionModel = production.get();

		return prepareProductionServiceOutput(productionModel);
	}

}
