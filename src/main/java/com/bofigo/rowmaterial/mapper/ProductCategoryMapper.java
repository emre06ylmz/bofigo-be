package com.bofigo.rowmaterial.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.bofigo.rowmaterial.api.request.ProductCategoryApiRequest;
import com.bofigo.rowmaterial.api.response.ProductCategoryApiResponse;
import com.bofigo.rowmaterial.dao.model.ProductCategoryModel;
import com.bofigo.rowmaterial.domain.dto.input.ProductCategoryServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.ProductCategoryServiceOutput;

@Mapper(componentModel = "spring")
public abstract class ProductCategoryMapper {

	public static final ProductCategoryMapper INSTANCE = Mappers.getMapper(ProductCategoryMapper.class);

	public abstract ProductCategoryServiceOutput mapModelToServiceOutput(
			ProductCategoryModel productCategoryModel);

	public abstract List<ProductCategoryServiceOutput> mapModelToServiceOutputList(
			List<ProductCategoryModel> productCategoryModelList);

	public abstract ProductCategoryModel mapServiceInputToModel(
			ProductCategoryServiceInput productCategoryServiceInput);

	public abstract ProductCategoryApiResponse mapServiceOutputToApiResponse(
			ProductCategoryServiceOutput productCategoryServiceOutput);

	public abstract List<ProductCategoryApiResponse> mapServiceOutputToApiResponseList(
			List<ProductCategoryServiceOutput> productCategoryServiceOutputList);

	public abstract ProductCategoryServiceInput mapApiRequestToServiceInput(
			ProductCategoryApiRequest productCategoryApiRequest);

	public abstract List<ProductCategoryServiceInput> mapApiRequestToServiceInputList(
			List<ProductCategoryApiRequest> productCategoryApiRequestList);

}
