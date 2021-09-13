package com.bofigo.rowmaterial.domain.service.product;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bofigo.rowmaterial.constant.ApplicationConstants;
import com.bofigo.rowmaterial.dao.model.CurrencySettingsModel;
import com.bofigo.rowmaterial.dao.model.ProductMaterialModel;
import com.bofigo.rowmaterial.dao.model.ProductModel;
import com.bofigo.rowmaterial.dao.model.PurchaseModel;
import com.bofigo.rowmaterial.dao.model.RawMaterialModel;
import com.bofigo.rowmaterial.dao.repository.CurrencySettingsRepository;
import com.bofigo.rowmaterial.dao.repository.ProductCategoryRepository;
import com.bofigo.rowmaterial.dao.repository.ProductMaterialRepository;
import com.bofigo.rowmaterial.dao.repository.ProductModelCodeRepository;
import com.bofigo.rowmaterial.dao.repository.ProductRepository;
import com.bofigo.rowmaterial.dao.repository.PurchaseRepository;
import com.bofigo.rowmaterial.dao.repository.RawMaterialRepository;
import com.bofigo.rowmaterial.domain.dto.input.ProductServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.ProductServiceOutput;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;
import com.bofigo.rowmaterial.exception.OperationNotValidException;
import com.bofigo.rowmaterial.mapper.ProductMapper;
import com.bofigo.rowmaterial.util.CurrencyUtil;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductMapper productMapper;
	private ProductRepository productRepository;

	private ProductMaterialRepository productMaterialRepository;

	private ProductCategoryRepository productCategoryRepository;
	private ProductModelCodeRepository productModelCodeRepository;

	private PurchaseRepository purchaseRepository;
	private RawMaterialRepository rawMaterialRepository;

	private CurrencySettingsRepository currencySettingsRepository;

	public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper,
			ProductMaterialRepository productMaterialRepository, PurchaseRepository purchaseRepository,
			RawMaterialRepository rawMaterialRepository, ProductCategoryRepository productCategoryRepository,
			ProductModelCodeRepository productModelCodeRepository,
			CurrencySettingsRepository currencySettingsRepository) {
		this.productRepository = productRepository;
		this.productMapper = productMapper;
		this.productMaterialRepository = productMaterialRepository;
		this.purchaseRepository = purchaseRepository;
		this.rawMaterialRepository = rawMaterialRepository;
		this.productCategoryRepository = productCategoryRepository;
		this.productModelCodeRepository = productModelCodeRepository;
		this.currencySettingsRepository = currencySettingsRepository;
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
	public ProductServiceOutput deleteProduct(Integer id) throws DataNotFoundException, OperationNotValidException {
		ProductModel productModel = getProductModel(id);

		try {
			productRepository.deleteById(id);
		} catch (Exception e) {
			throw new OperationNotValidException("Silme işlemi gerçekleştirilemedi");
		}

		return productMapper.mapModelToServiceOutput(productModel);
	}

	@Override
	public List<ProductServiceOutput> listAll() {
		List<ProductModel> productModelList = productRepository.findAll();
		List<ProductServiceOutput> products = productMapper.mapModelToServiceOutputList(productModelList);;

		products.forEach(product -> {
			double cost = product.getCost_TL();
			double tax = product.getTax();
			double cargo = product.getCargo();
			double taxRatio = (100 + tax) / 100;
			product.setCost_Plus(cost * 1.05);
			product.setCost_PlusTax(cost * 1.05 * taxRatio );
			product.setCost_Total(cost * 1.05 * taxRatio + cargo);
		});

		return products;
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
		productModel.setProductCategory(
				productCategoryRepository.findById(productServiceInput.getProductCategoryId()).get());
		productModel.setProductModelCode(
				productModelCodeRepository.findById(productServiceInput.getProductModelCodeId()).get());

		return productRepository.save(productModel);
	}

	public ProductModel updateProductModel(ProductModel productModel, ProductServiceInput productServiceInput) {
		int id = productModel.getId();
		productServiceInput.setStock(productModel.getStock());
		productServiceInput.setCost_TL(productModel.getCost_TL());
		productModel = productMapper.mapServiceInputToModel(productServiceInput);
		productModel.setProductCategory(
				productCategoryRepository.findById(productServiceInput.getProductCategoryId()).get());
		productModel.setProductModelCode(
				productModelCodeRepository.findById(productServiceInput.getProductModelCodeId()).get());
		productModel.setId(id);

		return productRepository.save(productModel);
	}

	@Override
	public void calculateProductCosts() throws OperationNotValidException {
		double totalCost_TL = 0;
		double purchasePrice = 0;

		List<CurrencySettingsModel> currencyList = currencySettingsRepository.findAll();
		if (currencyList.size() == 0) {
			throw new OperationNotValidException("Döviz ayarlarını kontrol ediniz.");
		}

		CurrencySettingsModel currencySettings = currencyList.get(0);

		List<ProductModel> productList = productRepository.findAll();

		for (ProductModel product : productList) {

			// get material list for product
			List<ProductMaterialModel> productMaterialList = productMaterialRepository.listByProductId(product.getId());
			totalCost_TL = 0;

			for (ProductMaterialModel productMaterial : productMaterialList) {
				Optional<RawMaterialModel> rawMaterial = rawMaterialRepository
						.findById(productMaterial.getRawMaterial().getId());
				RawMaterialModel rawMaterialModel = rawMaterial.get();
				// calculate price for material
				purchasePrice = getMaterialPriceForProduct(rawMaterialModel);

				if (rawMaterialModel.getSelectedCurrency().equals(CurrencyUtil.CURRENCY_TL)) {
					totalCost_TL += purchasePrice * productMaterial.getAmount();
				} else if (rawMaterialModel.getSelectedCurrency().equals(CurrencyUtil.CURRENCY_USD)) {
					totalCost_TL += purchasePrice * productMaterial.getAmount() * currencySettings.getDollar();
				} else if (rawMaterialModel.getSelectedCurrency().equals(CurrencyUtil.CURRENCY_EURO)) {
					totalCost_TL += purchasePrice * productMaterial.getAmount() * currencySettings.getEuro();
				}

			}

			product.setCost_TL(totalCost_TL);

			totalCost_TL = totalCost_TL + (totalCost_TL * product.getTax() / 100) + product.getCargo();
			product.setCost_Total(totalCost_TL);

			productRepository.save(product);

		}
	}

	private double getMaterialPriceForProduct(RawMaterialModel rawMaterial) throws OperationNotValidException {
		double purchasePrice = 0;
		List<PurchaseModel> purchaseList = purchaseRepository.listByMaterialId(rawMaterial.getId());

		if (purchaseList.size() == 0) {
			throw new OperationNotValidException(rawMaterial.getName() + " hammaddesi için satınalma bulunmamaktadır.");
		}

		Collections.sort(purchaseList, new Comparator<PurchaseModel>() {
			public int compare(PurchaseModel o1, PurchaseModel o2) {
				return o2.getDate().compareTo(o1.getDate());
			}
		});

		purchasePrice = purchaseList.get(0).getPrice();
		return purchasePrice;
	}

}
