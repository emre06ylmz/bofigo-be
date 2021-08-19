package com.bofigo.rowmaterial.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.bofigo.rowmaterial.api.request.ProductMaterialApiRequest;
import com.bofigo.rowmaterial.api.response.ProductMaterialApiResponse;
import com.bofigo.rowmaterial.dao.model.ProductMaterialModel;
import com.bofigo.rowmaterial.domain.dto.input.ProductMaterialServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.ProductMaterialServiceOutput;

@Mapper(componentModel = "spring")
public abstract class ProductMaterialMapper {

	public static final ProductMaterialMapper INSTANCE = Mappers.getMapper(ProductMaterialMapper.class);

	public abstract ProductMaterialServiceOutput mapModelToServiceOutput(ProductMaterialModel productMaterialModel);

	public abstract List<ProductMaterialServiceOutput> mapModelToServiceOutputList(
			List<ProductMaterialModel> productMaterialModelList);

	public abstract ProductMaterialModel mapServiceInputToModel(
			ProductMaterialServiceInput productMaterialServiceInput);

	public abstract ProductMaterialApiResponse mapServiceOutputToApiResponse(
			ProductMaterialServiceOutput productMaterialServiceOutput);

	public abstract List<ProductMaterialApiResponse> mapServiceOutputToApiResponseList(
			List<ProductMaterialServiceOutput> productMaterialServiceOutputList);

	public abstract ProductMaterialServiceInput mapApiRequestToServiceInput(
			ProductMaterialApiRequest productMaterialApiRequest);

	public abstract List<ProductMaterialServiceInput> mapApiRequestToServiceInputList(
			List<ProductMaterialApiRequest> productMaterialApiRequestList);

}
