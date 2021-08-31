package com.bofigo.rowmaterial.domain.service.production;

import java.util.List;

import com.bofigo.rowmaterial.domain.dto.input.ProductionServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.ProductionServiceOutput;
import com.bofigo.rowmaterial.domain.service.Service;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;

public interface ProductionService extends Service {

	ProductionServiceOutput getProductionById(Integer id) throws DataNotFoundException;

	ProductionServiceOutput getProductionByProductId(Integer productId) throws DataNotFoundException;

	ProductionServiceOutput createProduction(ProductionServiceInput productionServiceInput)
			throws DataAlreadyExistException;

	ProductionServiceOutput updateProduction(Integer id, ProductionServiceInput productionServiceInput)
			throws DataNotFoundException;

	ProductionServiceOutput deleteProduction(Integer id) throws DataNotFoundException;

	List<ProductionServiceOutput> listAll();

	List<ProductionServiceOutput> listByProductId(Integer productId);

}
