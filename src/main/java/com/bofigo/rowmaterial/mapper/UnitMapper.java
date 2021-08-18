package com.bofigo.rowmaterial.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.bofigo.rowmaterial.api.request.UnitApiRequest;
import com.bofigo.rowmaterial.api.response.UnitApiResponse;
import com.bofigo.rowmaterial.dao.model.UnitModel;
import com.bofigo.rowmaterial.domain.dto.input.UnitServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.UnitServiceOutput;

@Mapper(componentModel = "spring")
public abstract class UnitMapper {

	public static final UnitMapper INSTANCE = Mappers.getMapper(UnitMapper.class);

	public abstract UnitServiceOutput mapModelToServiceOutput(
			UnitModel unitModel);

	public abstract List<UnitServiceOutput> mapModelToServiceOutputList(
			List<UnitModel> unitModelList);

	public abstract UnitModel mapServiceInputToModel(
			UnitServiceInput unitServiceInput);

	public abstract UnitApiResponse mapServiceOutputToApiResponse(
			UnitServiceOutput unitServiceOutput);

	public abstract List<UnitApiResponse> mapServiceOutputToApiResponseList(
			List<UnitServiceOutput> unitServiceOutputList);

	public abstract UnitServiceInput mapApiRequestToServiceInput(
			UnitApiRequest unitApiRequest);

	public abstract List<UnitServiceInput> mapApiRequestToServiceInputList(
			List<UnitApiRequest> unitApiRequestList);

}
