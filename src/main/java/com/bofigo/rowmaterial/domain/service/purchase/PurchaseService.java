package com.bofigo.rowmaterial.domain.service.purchase;

import java.util.List;

import com.bofigo.rowmaterial.domain.dto.input.PurchaseServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.PurchaseServiceOutput;
import com.bofigo.rowmaterial.domain.service.Service;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;

public interface PurchaseService extends Service {

	PurchaseServiceOutput getPurchaseById(Integer id) throws DataNotFoundException;

	PurchaseServiceOutput createPurchase(PurchaseServiceInput purchaseServiceInput)
			throws DataAlreadyExistException;

	PurchaseServiceOutput updatePurchase(Integer id, PurchaseServiceInput purchaseServiceInput)
			throws DataNotFoundException;

	PurchaseServiceOutput deletePurchase(Integer id) throws DataNotFoundException;

	List<PurchaseServiceOutput> listAll();

	List<PurchaseServiceOutput> listByMaterialId(Integer rawMaterialId);

}
