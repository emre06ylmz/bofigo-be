package com.bofigo.rowmaterial.domain.service.delivery;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bofigo.rowmaterial.constant.ApplicationConstants;
import com.bofigo.rowmaterial.dao.model.ProductMaterialModel;
import com.bofigo.rowmaterial.dao.model.DeliveryModel;
import com.bofigo.rowmaterial.dao.model.RawMaterialModel;
import com.bofigo.rowmaterial.dao.repository.ProductMaterialRepository;
import com.bofigo.rowmaterial.dao.repository.ProductRepository;
import com.bofigo.rowmaterial.dao.repository.DeliveryRepository;
import com.bofigo.rowmaterial.dao.repository.RawMaterialCategoryRepository;
import com.bofigo.rowmaterial.dao.repository.RawMaterialRepository;
import com.bofigo.rowmaterial.domain.dto.input.DeliveryServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.DeliveryServiceOutput;
import com.bofigo.rowmaterial.domain.service.delivery.DeliveryService;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;
import com.bofigo.rowmaterial.mapper.DeliveryMapper;

@Service
public class DeliveryServiceImpl implements DeliveryService {

	private DeliveryMapper deliveryMapper;
	private DeliveryRepository deliveryRepository;

	private ProductRepository productRepository;
	private RawMaterialRepository rawMaterialRepository;
	private ProductMaterialRepository productMaterialRepository;

	public DeliveryServiceImpl(DeliveryRepository deliveryRepository, DeliveryMapper deliveryMapper,
			ProductRepository productRepository, RawMaterialRepository rawMaterialRepository,
			RawMaterialCategoryRepository rawMaterialCategoryRepository,
			ProductMaterialRepository productMaterialRepository) {
		this.deliveryRepository = deliveryRepository;
		this.deliveryMapper = deliveryMapper;
		this.productRepository = productRepository;
		this.rawMaterialRepository = rawMaterialRepository;
		this.productMaterialRepository = productMaterialRepository;
	}

	@Override
	public DeliveryServiceOutput getDeliveryById(Integer id) throws DataNotFoundException {
		DeliveryModel deliveryModel = getDeliveryModel(id);
		return prepareDeliveryServiceOutput(deliveryModel);
	}

	@Override
	public DeliveryServiceOutput createDelivery(DeliveryServiceInput deliveryServiceInput)
			throws DataAlreadyExistException {
		DeliveryModel insertedDeliveryModel = insertDeliveryModel(deliveryServiceInput);

		// UPDATE STOCK
		List<ProductMaterialModel> productMaterialList = productMaterialRepository
				.listByProductId(insertedDeliveryModel.getProduct().getId());

		for (ProductMaterialModel productMaterialModel : productMaterialList) {
			RawMaterialModel rawMaterial = productMaterialModel.getRawMaterial();
			rawMaterial.setStock(
					rawMaterial.getStock() - insertedDeliveryModel.getCount() * productMaterialModel.getAmount());
			rawMaterialRepository.save(rawMaterial);
		}

		return prepareDeliveryServiceOutput(insertedDeliveryModel);
	}

	@Override
	public DeliveryServiceOutput updateDelivery(Integer id, DeliveryServiceInput deliveryServiceInput)
			throws DataNotFoundException {
		Optional<DeliveryModel> deliveryModel = deliveryRepository.findById(id);

		if (deliveryModel.isPresent()) {

			DeliveryModel delivery = deliveryModel.get();

			// UPDATE STOCK
			// eski ürün id sine göre stokları geri ekle
			List<ProductMaterialModel> productMaterialList = productMaterialRepository
					.listByProductId(delivery.getProduct().getId());
			for (ProductMaterialModel productMaterialModel : productMaterialList) {
				RawMaterialModel rawMaterial = productMaterialModel.getRawMaterial();
				rawMaterial.setStock(rawMaterial.getStock() + delivery.getCount() * productMaterialModel.getAmount());
				rawMaterialRepository.save(rawMaterial);
			}

			DeliveryModel updatedDeliveryModel = updateDeliveryModel(deliveryModel.get(),
					deliveryServiceInput);

			// yeni ürün id ye göre stoklardan düş
			productMaterialRepository.listByProductId(updatedDeliveryModel.getProduct().getId());
			for (ProductMaterialModel productMaterialModel : productMaterialList) {
				RawMaterialModel rawMaterial = productMaterialModel.getRawMaterial();
				rawMaterial.setStock(rawMaterial.getStock() + delivery.getCount() * productMaterialModel.getAmount());
				rawMaterialRepository.save(rawMaterial);
			}

			return prepareDeliveryServiceOutput(updatedDeliveryModel);
		}

		throw new DataNotFoundException("bulunamadı");
	}

	@Override
	public DeliveryServiceOutput deleteDelivery(Integer id) throws DataNotFoundException {
		DeliveryModel deliveryModel = getDeliveryModel(id);
		deliveryRepository.deleteById(id);
		return deliveryMapper.mapModelToServiceOutput(deliveryModel);
	}

	@Override
	public List<DeliveryServiceOutput> listAll() {
		List<DeliveryModel> deliveryModelList = deliveryRepository.findAll();
		return deliveryMapper.mapModelToServiceOutputList(deliveryModelList);
	}

	@Override
	public List<DeliveryServiceOutput> listByProductId(Integer productId) {
		List<DeliveryModel> deliveryModelList = deliveryRepository.listByProductId(productId);
		return deliveryMapper.mapModelToServiceOutputList(deliveryModelList);
	}

	private DeliveryModel getDeliveryModel(Integer id) throws DataNotFoundException {
		Optional<DeliveryModel> delivery = deliveryRepository.findById(id);

		if (!delivery.isPresent()) {
			throw new DataNotFoundException("bulunamadı");
		}
		return delivery.get();
	}

	public DeliveryServiceOutput prepareDeliveryServiceOutput(DeliveryModel deliveryModel) {
		DeliveryServiceOutput deliveryServiceOutput = new DeliveryServiceOutput();

		if (deliveryModel == null) {
			return deliveryServiceOutput;
		}

		deliveryServiceOutput = deliveryMapper.mapModelToServiceOutput(deliveryModel);

		return deliveryServiceOutput;
	}

	public DeliveryModel insertDeliveryModel(DeliveryServiceInput deliveryServiceInput) {
		DeliveryModel deliveryModel = deliveryMapper.mapServiceInputToModel(deliveryServiceInput);
		deliveryModel.setStatus(ApplicationConstants.ACTIVE);
		deliveryModel.setDate(new Date());
		deliveryModel.setProduct(productRepository.findById(deliveryServiceInput.getProductId()).get());

		return deliveryRepository.save(deliveryModel);
	}

	public DeliveryModel updateDeliveryModel(DeliveryModel deliveryModel,
			DeliveryServiceInput deliveryServiceInput) {
		int id = deliveryModel.getId();
		deliveryModel = deliveryMapper.mapServiceInputToModel(deliveryServiceInput);
		deliveryModel.setProduct(productRepository.findById(deliveryServiceInput.getProductId()).get());
		deliveryModel.setId(id);
		deliveryModel.setDate(new Date());
		return deliveryRepository.save(deliveryModel);
	}

	@Override
	public DeliveryServiceOutput getDeliveryByProductId(Integer productId) throws DataNotFoundException {

		Optional<DeliveryModel> delivery = deliveryRepository.findByProductId(productId);

		if (!delivery.isPresent()) {
			throw new DataNotFoundException("bulunamadı");
		}
		DeliveryModel deliveryModel = delivery.get();

		return prepareDeliveryServiceOutput(deliveryModel);
	}

}
