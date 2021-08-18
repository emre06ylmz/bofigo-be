package com.bofigo.rowmaterial.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.bofigo.rowmaterial.api.request.ProductApiRequest;
import com.bofigo.rowmaterial.api.response.ProductApiResponse;
import com.bofigo.rowmaterial.dao.model.ProductModel;
import com.bofigo.rowmaterial.domain.dto.input.ProductServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.ProductServiceOutput;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {

	public static final ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

	public abstract ProductServiceOutput mapModelToServiceOutput(
			ProductModel productModel);

	public abstract List<ProductServiceOutput> mapModelToServiceOutputList(
			List<ProductModel> productModelList);

	public abstract ProductModel mapServiceInputToModel(
			ProductServiceInput productServiceInput);

	public abstract ProductApiResponse mapServiceOutputToApiResponse(
			ProductServiceOutput productServiceOutput);

	public abstract List<ProductApiResponse> mapServiceOutputToApiResponseList(
			List<ProductServiceOutput> productServiceOutputList);

	public abstract ProductServiceInput mapApiRequestToServiceInput(
			ProductApiRequest productApiRequest);

	public abstract List<ProductServiceInput> mapApiRequestToServiceInputList(
			List<ProductApiRequest> productApiRequestList);

}
