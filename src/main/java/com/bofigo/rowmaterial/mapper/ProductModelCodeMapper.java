package com.bofigo.rowmaterial.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.bofigo.rowmaterial.api.request.ProductModelCodeApiRequest;
import com.bofigo.rowmaterial.api.response.ProductModelCodeApiResponse;
import com.bofigo.rowmaterial.dao.model.ProductModelCodeModel;
import com.bofigo.rowmaterial.domain.dto.input.ProductModelCodeServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.ProductModelCodeServiceOutput;

@Mapper(componentModel = "spring")
public abstract class ProductModelCodeMapper {

	public static final ProductModelCodeMapper INSTANCE = Mappers.getMapper(ProductModelCodeMapper.class);

	public abstract ProductModelCodeServiceOutput mapModelToServiceOutput(
			ProductModelCodeModel productModelCodeModel);

	public abstract List<ProductModelCodeServiceOutput> mapModelToServiceOutputList(
			List<ProductModelCodeModel> productModelCodeModelList);

	public abstract ProductModelCodeModel mapServiceInputToModel(
			ProductModelCodeServiceInput productModelCodeServiceInput);

	public abstract ProductModelCodeApiResponse mapServiceOutputToApiResponse(
			ProductModelCodeServiceOutput productModelCodeServiceOutput);

	public abstract List<ProductModelCodeApiResponse> mapServiceOutputToApiResponseList(
			List<ProductModelCodeServiceOutput> productModelCodeServiceOutputList);

	public abstract ProductModelCodeServiceInput mapApiRequestToServiceInput(
			ProductModelCodeApiRequest productModelCodeApiRequest);

	public abstract List<ProductModelCodeServiceInput> mapApiRequestToServiceInputList(
			List<ProductModelCodeApiRequest> productModelCodeApiRequestList);

}
