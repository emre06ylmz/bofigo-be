package com.bofigo.rowmaterial.domain.service.product;

import java.util.List;

import com.bofigo.rowmaterial.domain.dto.input.ProductServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.ProductServiceOutput;
import com.bofigo.rowmaterial.domain.service.Service;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;
import com.bofigo.rowmaterial.exception.OperationNotValidException;

public interface ProductService extends Service {

	ProductServiceOutput getProductById(Integer id) throws DataNotFoundException;

	ProductServiceOutput getProductByName(String name);

	ProductServiceOutput createProduct(
			ProductServiceInput productServiceInput) throws DataAlreadyExistException;

	ProductServiceOutput updateProduct(Integer id,
			ProductServiceInput productServiceInput) throws DataNotFoundException;

	ProductServiceOutput deleteProduct(Integer id) throws DataNotFoundException, OperationNotValidException;

	List<ProductServiceOutput> listAll();

	void calculateProductCosts();
}
