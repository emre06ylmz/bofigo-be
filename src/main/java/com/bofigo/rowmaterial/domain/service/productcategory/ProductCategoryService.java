package com.bofigo.rowmaterial.domain.service.productcategory;

import java.util.List;

import com.bofigo.rowmaterial.domain.dto.input.ProductCategoryServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.ProductCategoryServiceOutput;
import com.bofigo.rowmaterial.domain.service.Service;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;

public interface ProductCategoryService extends Service {

	ProductCategoryServiceOutput getProductCategoryById(Integer id) throws DataNotFoundException;

	ProductCategoryServiceOutput getProductCategoryByName(String name);

	ProductCategoryServiceOutput createProductCategory(
			ProductCategoryServiceInput productCategoryServiceInput) throws DataAlreadyExistException;

	ProductCategoryServiceOutput updateProductCategory(Integer id,
			ProductCategoryServiceInput productCategoryServiceInput) throws DataNotFoundException;

	ProductCategoryServiceOutput deleteProductCategory(Integer id) throws DataNotFoundException;

	List<ProductCategoryServiceOutput> listAll();

}
