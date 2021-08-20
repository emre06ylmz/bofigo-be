package com.bofigo.rowmaterial.domain.service.productmaterial;

import java.util.List;

import com.bofigo.rowmaterial.domain.dto.input.ProductMaterialServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.ProductMaterialServiceOutput;
import com.bofigo.rowmaterial.domain.service.Service;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;

public interface ProductMaterialService extends Service {

	ProductMaterialServiceOutput getProductMaterialById(Integer id) throws DataNotFoundException;

	ProductMaterialServiceOutput getProductMaterialByProductIdAndRawMaterialId(Integer productId, Integer rawMaterialId) throws DataNotFoundException;

	ProductMaterialServiceOutput createProductMaterial(ProductMaterialServiceInput productMaterialServiceInput)
			throws DataAlreadyExistException;

	ProductMaterialServiceOutput updateProductMaterial(Integer id,
			ProductMaterialServiceInput productMaterialServiceInput) throws DataNotFoundException;

	ProductMaterialServiceOutput deleteProductMaterial(Integer id) throws DataNotFoundException;

	List<ProductMaterialServiceOutput> listAll();

	List<ProductMaterialServiceOutput> listByProductId(Integer productId);

}
