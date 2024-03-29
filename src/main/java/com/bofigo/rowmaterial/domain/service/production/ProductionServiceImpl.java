package com.bofigo.rowmaterial.domain.service.production;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bofigo.rowmaterial.constant.ApplicationConstants;
import com.bofigo.rowmaterial.dao.model.ProductMaterialModel;
import com.bofigo.rowmaterial.dao.model.ProductModel;
import com.bofigo.rowmaterial.dao.model.ProductionModel;
import com.bofigo.rowmaterial.dao.model.RawMaterialModel;
import com.bofigo.rowmaterial.dao.repository.ProductMaterialRepository;
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
	private ProductMaterialRepository productMaterialRepository;

	public ProductionServiceImpl(ProductionRepository productionRepository, ProductionMapper productionMapper,
			ProductRepository productRepository, RawMaterialRepository rawMaterialRepository,
			RawMaterialCategoryRepository rawMaterialCategoryRepository,
			ProductMaterialRepository productMaterialRepository) {
		this.productionRepository = productionRepository;
		this.productionMapper = productionMapper;
		this.productRepository = productRepository;
		this.rawMaterialRepository = rawMaterialRepository;
		this.productMaterialRepository = productMaterialRepository;
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

		// UPDATE PRODUCT STOCK
		ProductModel product = productRepository.findById(productionServiceInput.getProductId()).get();
		product.setStock( product.getStock() + productionServiceInput.getCount());
		productRepository.save(product);
		
		// UPDATE MATERIAL STOCK
		List<ProductMaterialModel> productMaterialList = productMaterialRepository
				.listByProductId(insertedProductionModel.getProduct().getId());

		for (ProductMaterialModel productMaterialModel : productMaterialList) {
			RawMaterialModel rawMaterial = productMaterialModel.getRawMaterial();
			rawMaterial.setStock(
					rawMaterial.getStock() - insertedProductionModel.getCount() * productMaterialModel.getAmount());
			rawMaterialRepository.save(rawMaterial);
		}

		return prepareProductionServiceOutput(insertedProductionModel);
	}

	@Override
	public ProductionServiceOutput updateProduction(Integer id, ProductionServiceInput productionServiceInput)
			throws DataNotFoundException {
		Optional<ProductionModel> productionModel = productionRepository.findById(id);

		if (productionModel.isPresent()) {

			ProductionModel production = productionModel.get();

			// UPDATE PRODUCT STOCK
			ProductModel product = productRepository.findById(productionServiceInput.getProductId()).get();
			product.setStock( product.getStock() - production.getCount() + productionServiceInput.getCount() );
			productRepository.save(product);
			
			
			// UPDATE MATERIAL STOCK
			// eski ürün id sine göre stokları geri ekle
			List<ProductMaterialModel> productMaterialList = productMaterialRepository
					.listByProductId(production.getProduct().getId());
			for (ProductMaterialModel productMaterialModel : productMaterialList) {
				RawMaterialModel rawMaterial = productMaterialModel.getRawMaterial();
				rawMaterial.setStock(rawMaterial.getStock() + production.getCount() * productMaterialModel.getAmount());
				rawMaterialRepository.save(rawMaterial);
			}

			ProductionModel updatedProductionModel = updateProductionModel(productionModel.get(),
					productionServiceInput);

			// yeni ürün id ye göre stoklardan düş
			productMaterialRepository.listByProductId(updatedProductionModel.getProduct().getId());
			for (ProductMaterialModel productMaterialModel : productMaterialList) {
				RawMaterialModel rawMaterial = productMaterialModel.getRawMaterial();
				rawMaterial.setStock(rawMaterial.getStock() - production.getCount() * productMaterialModel.getAmount());
				rawMaterialRepository.save(rawMaterial);
			}

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
