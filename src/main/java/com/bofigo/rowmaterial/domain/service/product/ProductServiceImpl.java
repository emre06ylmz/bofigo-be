package com.bofigo.rowmaterial.domain.service.product;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bofigo.rowmaterial.constant.ApplicationConstants;
import com.bofigo.rowmaterial.dao.model.ProductMaterialModel;
import com.bofigo.rowmaterial.dao.model.ProductModel;
import com.bofigo.rowmaterial.dao.model.PurchaseModel;
import com.bofigo.rowmaterial.dao.model.RawMaterialModel;
import com.bofigo.rowmaterial.dao.repository.ProductMaterialRepository;
import com.bofigo.rowmaterial.dao.repository.ProductRepository;
import com.bofigo.rowmaterial.dao.repository.PurchaseRepository;
import com.bofigo.rowmaterial.dao.repository.RawMaterialRepository;
import com.bofigo.rowmaterial.domain.dto.input.ProductServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.ProductServiceOutput;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;
import com.bofigo.rowmaterial.mapper.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductMapper productMapper;
	private ProductRepository productRepository;

	private ProductMaterialRepository productMaterialRepository;

	private PurchaseRepository purchaseRepository;
	private RawMaterialRepository rawMaterialRepository;

	public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper,
			ProductMaterialRepository productMaterialRepository, PurchaseRepository purchaseRepository,
			RawMaterialRepository rawMaterialRepository) {
		this.productRepository = productRepository;
		this.productMapper = productMapper;
		this.productMaterialRepository = productMaterialRepository;
		this.purchaseRepository = purchaseRepository;
		this.rawMaterialRepository = rawMaterialRepository;
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

		productServiceOutput = productMapper.mapModelToServiceOutput(productModel);

		return productServiceOutput;
	}

	public ProductModel insertProductModel(ProductServiceInput productServiceInput) {
		ProductModel productModel = productMapper.mapServiceInputToModel(productServiceInput);
		productModel.setStatus(ApplicationConstants.ACTIVE);

		return productRepository.save(productModel);
	}

	public ProductModel updateProductModel(ProductModel productModel, ProductServiceInput productServiceInput) {
		productModel = productMapper.mapServiceInputToModel(productServiceInput);

		return productRepository.save(productModel);
	}

	@Override
	public void calculateProductCosts() {
		double totalCost = 0;
		double purchasePrice = 0;

		List<ProductModel> productList = productRepository.findAll();

		for (ProductModel product : productList) {

			// get material list for product
			List<ProductMaterialModel> productMaterialList = productMaterialRepository
					.listByProductId(product.getId());
			totalCost = 0;

			for (ProductMaterialModel productMaterial : productMaterialList) {
				Optional<RawMaterialModel> rawMaterial = rawMaterialRepository.findById(productMaterial.getRawMaterial().getId());

				// calculate price for material
				purchasePrice = getMaterialPriceForProduct(rawMaterial.get());
				totalCost += purchasePrice * productMaterial.getAmount();
			}

			product.setCost(totalCost);
			productRepository.save(product);

		}
	}

	private double getMaterialPriceForProduct(RawMaterialModel rawMaterial) {
		double purchasePrice = 0;
		List<PurchaseModel> purchaseList = purchaseRepository.listByMaterialId(rawMaterial.getId());

		Collections.sort(purchaseList, new Comparator<PurchaseModel>() {
			public int compare(PurchaseModel o1, PurchaseModel o2) {
				return o2.getDate().compareTo(o1.getDate());
			}
		});

		purchasePrice = purchaseList.get(0).getPrice();
		return purchasePrice;
	}

}
