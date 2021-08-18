package com.bofigo.rowmaterial.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.bofigo.rowmaterial.api.request.RawMaterialApiRequest;
import com.bofigo.rowmaterial.api.response.RawMaterialApiResponse;
import com.bofigo.rowmaterial.dao.model.RawMaterialModel;
import com.bofigo.rowmaterial.domain.dto.input.RawMaterialServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.RawMaterialServiceOutput;

@Mapper(componentModel = "spring")
public abstract class RawMaterialMapper {

	public static final RawMaterialMapper INSTANCE = Mappers.getMapper(RawMaterialMapper.class);

	public abstract RawMaterialServiceOutput mapModelToServiceOutput(
			RawMaterialModel rawMaterialModel);

	public abstract List<RawMaterialServiceOutput> mapModelToServiceOutputList(
			List<RawMaterialModel> rawMaterialModelList);

	public abstract RawMaterialModel mapServiceInputToModel(
			RawMaterialServiceInput rawMaterialServiceInput);

	public abstract RawMaterialApiResponse mapServiceOutputToApiResponse(
			RawMaterialServiceOutput rawMaterialServiceOutput);

	public abstract List<RawMaterialApiResponse> mapServiceOutputToApiResponseList(
			List<RawMaterialServiceOutput> rawMaterialServiceOutputList);

	public abstract RawMaterialServiceInput mapApiRequestToServiceInput(
			RawMaterialApiRequest rawMaterialApiRequest);

	public abstract List<RawMaterialServiceInput> mapApiRequestToServiceInputList(
			List<RawMaterialApiRequest> rawMaterialApiRequestList);

}
