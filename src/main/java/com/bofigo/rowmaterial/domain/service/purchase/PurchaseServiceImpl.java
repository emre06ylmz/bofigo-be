package com.bofigo.rowmaterial.domain.service.purchase;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bofigo.rowmaterial.constant.ApplicationConstants;
import com.bofigo.rowmaterial.dao.model.PurchaseModel;
import com.bofigo.rowmaterial.dao.model.RawMaterialModel;
import com.bofigo.rowmaterial.dao.repository.PurchaseRepository;
import com.bofigo.rowmaterial.dao.repository.RawMaterialRepository;
import com.bofigo.rowmaterial.dao.repository.SupplierRepository;
import com.bofigo.rowmaterial.domain.dto.input.PurchaseServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.PurchaseServiceOutput;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;
import com.bofigo.rowmaterial.mapper.PurchaseMapper;

@Service
public class PurchaseServiceImpl implements PurchaseService {

	private PurchaseMapper purchaseMapper;
	private PurchaseRepository purchaseRepository;

	private SupplierRepository supplierRepository;
	private RawMaterialRepository rawMaterialRepository;

	public PurchaseServiceImpl(PurchaseRepository purchaseRepository, PurchaseMapper purchaseMapper,
			SupplierRepository supplierRepository, RawMaterialRepository rawMaterialRepository) {
		this.purchaseRepository = purchaseRepository;
		this.purchaseMapper = purchaseMapper;
		this.supplierRepository = supplierRepository;
		this.rawMaterialRepository = rawMaterialRepository;
	}

	@Override
	public PurchaseServiceOutput getPurchaseById(Integer id) throws DataNotFoundException {
		PurchaseModel purchaseModel = getPurchaseModel(id);
		return preparePurchaseServiceOutput(purchaseModel);
	}

	@Override
	public PurchaseServiceOutput createPurchase(PurchaseServiceInput purchaseServiceInput)
			throws DataAlreadyExistException {
		PurchaseModel insertedPurchaseModel = insertPurchaseModel(purchaseServiceInput);

		//UPDATE STOCK
		RawMaterialModel rawMaterial = rawMaterialRepository.findById(purchaseServiceInput.getRawMaterialId()).get();
		rawMaterial.setStock(rawMaterial.getStock() + purchaseServiceInput.getAmount());
		rawMaterialRepository.save(rawMaterial);
		
		return preparePurchaseServiceOutput(insertedPurchaseModel);
	}

	@Override
	public PurchaseServiceOutput updatePurchase(Integer id, PurchaseServiceInput purchaseServiceInput)
			throws DataNotFoundException {
		Optional<PurchaseModel> purchaseModel = purchaseRepository.findById(id);
		double oldStock = purchaseModel.get().getAmount();
		if (purchaseModel.isPresent()) {
			PurchaseModel updatedPurchaseModel = updatePurchaseModel(purchaseModel.get(), purchaseServiceInput);

			//UPDATE STOCK
			if (updatedPurchaseModel.getAmount() != oldStock) {
				RawMaterialModel rawMaterial = rawMaterialRepository.findById(purchaseServiceInput.getRawMaterialId())
						.get();
				rawMaterial.setStock(rawMaterial.getStock() + purchaseServiceInput.getAmount() - oldStock);
				rawMaterialRepository.save(rawMaterial);
			}

			return preparePurchaseServiceOutput(updatedPurchaseModel);
		}

		throw new DataNotFoundException("bulunamadı");
	}

	@Override
	public PurchaseServiceOutput deletePurchase(Integer id) throws DataNotFoundException {
		PurchaseModel purchaseModel = getPurchaseModel(id);
		purchaseRepository.deleteById(id);
		
		//UPDATE STOCK
		RawMaterialModel rawMaterial = rawMaterialRepository.findById(purchaseModel.getRawMaterial().getId())
				.get();
		rawMaterial.setStock(rawMaterial.getStock() - purchaseModel.getAmount());
		rawMaterialRepository.save(rawMaterial);
		
		return purchaseMapper.mapModelToServiceOutput(purchaseModel);
	}

	@Override
	public List<PurchaseServiceOutput> listAll() {
		List<PurchaseModel> purchaseModelList = purchaseRepository.findAll();
		return purchaseMapper.mapModelToServiceOutputList(purchaseModelList);
	}
	

	@Override
	public List<PurchaseServiceOutput> listByMaterialId(Integer rawMaterialId) {
		List<PurchaseModel> purchaseModelList = purchaseRepository.listByMaterialId(rawMaterialId);
		return purchaseMapper.mapModelToServiceOutputList(purchaseModelList);
	}

	private PurchaseModel getPurchaseModel(Integer id) throws DataNotFoundException {
		Optional<PurchaseModel> purchase = purchaseRepository.findById(id);

		if (!purchase.isPresent()) {
			throw new DataNotFoundException("bulunamadı");
		}
		return purchase.get();
	}

	public PurchaseServiceOutput preparePurchaseServiceOutput(PurchaseModel purchaseModel) {
		PurchaseServiceOutput purchaseServiceOutput = new PurchaseServiceOutput();

		if (purchaseModel == null) {
			return purchaseServiceOutput;
		}

		purchaseServiceOutput = purchaseMapper.mapModelToServiceOutput(purchaseModel);

		return purchaseServiceOutput;
	}

	public PurchaseModel insertPurchaseModel(PurchaseServiceInput purchaseServiceInput) {
		PurchaseModel purchaseModel = purchaseMapper.mapServiceInputToModel(purchaseServiceInput);
		purchaseModel.setStatus(ApplicationConstants.ACTIVE);

		purchaseModel.setDate(new Date());
		purchaseModel.setSupplier(supplierRepository.findById(purchaseServiceInput.getSupplierId()).get());
		purchaseModel.setRawMaterial(rawMaterialRepository.findById(purchaseServiceInput.getRawMaterialId()).get());

		return purchaseRepository.save(purchaseModel);
	}

	public PurchaseModel updatePurchaseModel(PurchaseModel purchaseModel, PurchaseServiceInput purchaseServiceInput) {
		purchaseModel.setAmount(purchaseServiceInput.getAmount());
		purchaseModel.setDate(purchaseServiceInput.getDate());
		purchaseModel.setExplanation(purchaseServiceInput.getExplanation());
		purchaseModel.setPrice(purchaseServiceInput.getPrice());

		return purchaseRepository.save(purchaseModel);
	}


}
