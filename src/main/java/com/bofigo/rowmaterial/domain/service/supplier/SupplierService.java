package com.bofigo.rowmaterial.domain.service.supplier;

import java.util.List;

import com.bofigo.rowmaterial.domain.dto.input.SupplierServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.SupplierServiceOutput;
import com.bofigo.rowmaterial.domain.service.Service;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;

public interface SupplierService extends Service {

	SupplierServiceOutput getSupplierById(Integer id) throws DataNotFoundException;

	SupplierServiceOutput getSupplierByName(String name);

	SupplierServiceOutput createSupplier(
			SupplierServiceInput supplierServiceInput) throws DataAlreadyExistException;

	SupplierServiceOutput updateSupplier(Integer id,
			SupplierServiceInput supplierServiceInput) throws DataNotFoundException;

	SupplierServiceOutput deleteSupplier(Integer id) throws DataNotFoundException;

	List<SupplierServiceOutput> listAll();

}
