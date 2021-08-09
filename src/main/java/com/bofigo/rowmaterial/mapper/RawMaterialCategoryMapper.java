package com.bofigo.rowmaterial.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.bofigo.rowmaterial.api.request.RawMaterialCategoryApiRequest;
import com.bofigo.rowmaterial.api.response.RawMaterialCategoryApiResponse;
import com.bofigo.rowmaterial.dao.model.RawMaterialCategoryModel;
import com.bofigo.rowmaterial.domain.dto.RawMaterialCategoryServiceInput;
import com.bofigo.rowmaterial.domain.dto.RawMaterialCategoryServiceOutput;

@Mapper(componentModel = "spring")
public abstract class RawMaterialCategoryMapper {

	public static final RawMaterialCategoryMapper INSTANCE = Mappers.getMapper(RawMaterialCategoryMapper.class);

	public abstract RawMaterialCategoryServiceOutput mapModelToServiceOutput(
			RawMaterialCategoryModel rawMaterialCategoryModel);

	public abstract List<RawMaterialCategoryServiceOutput> mapModelToServiceOutputList(
			List<RawMaterialCategoryModel> rawMaterialCategoryModelList);

	public abstract RawMaterialCategoryModel mapServiceInputToModel(
			RawMaterialCategoryServiceInput rawMaterialCategoryServiceInput);

	public abstract RawMaterialCategoryApiResponse mapServiceOutputToApiResponse(
			RawMaterialCategoryServiceOutput rawMaterialCategoryServiceOutput);

	public abstract List<RawMaterialCategoryApiResponse> mapServiceOutputToApiResponseList(
			List<RawMaterialCategoryServiceOutput> rawMaterialCategoryServiceOutputList);

	public abstract RawMaterialCategoryServiceInput mapApiRequestToServiceInput(
			RawMaterialCategoryApiRequest rawMaterialCategoryApiRequest);

	public abstract List<RawMaterialCategoryServiceInput> mapApiRequestToServiceInputList(
			List<RawMaterialCategoryApiRequest> rawMaterialCategoryApiRequestList);

}
