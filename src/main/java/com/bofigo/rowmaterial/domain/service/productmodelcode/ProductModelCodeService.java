package com.bofigo.rowmaterial.domain.service.productmodelcode;

import java.util.List;

import com.bofigo.rowmaterial.domain.dto.input.ProductModelCodeServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.ProductModelCodeServiceOutput;
import com.bofigo.rowmaterial.domain.service.Service;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;

public interface ProductModelCodeService extends Service {

	ProductModelCodeServiceOutput getProductModelCodeById(Integer id) throws DataNotFoundException;

	ProductModelCodeServiceOutput getProductModelCodeByName(String name);

	ProductModelCodeServiceOutput createProductModelCode(
			ProductModelCodeServiceInput productCategoryServiceInput) throws DataAlreadyExistException;

	ProductModelCodeServiceOutput updateProductModelCode(Integer id,
			ProductModelCodeServiceInput productCategoryServiceInput) throws DataNotFoundException;

	ProductModelCodeServiceOutput deleteProductModelCode(Integer id) throws DataNotFoundException;

	List<ProductModelCodeServiceOutput> listAll();

}
