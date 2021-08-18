package com.bofigo.rowmaterial.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.bofigo.rowmaterial.api.request.CurrencySettingsApiRequest;
import com.bofigo.rowmaterial.api.response.CurrencySettingsApiResponse;
import com.bofigo.rowmaterial.dao.model.CurrencySettingsModel;
import com.bofigo.rowmaterial.domain.dto.input.CurrencySettingsServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.CurrencySettingsServiceOutput;

@Mapper(componentModel = "spring")
public abstract class CurrencySettingsMapper {

	public static final CurrencySettingsMapper INSTANCE = Mappers.getMapper(CurrencySettingsMapper.class);

	public abstract CurrencySettingsServiceOutput mapModelToServiceOutput(CurrencySettingsModel cuırrenySettingsModel);

	public abstract List<CurrencySettingsServiceOutput> mapModelToServiceOutputList(List<CurrencySettingsModel> cuırrenySettingsModelList);

	public abstract CurrencySettingsModel mapServiceInputToModel(CurrencySettingsServiceInput cuırrenySettingsServiceInput);

	public abstract CurrencySettingsApiResponse mapServiceOutputToApiResponse(CurrencySettingsServiceOutput cuırrenySettingsServiceOutput);

	public abstract List<CurrencySettingsApiResponse> mapServiceOutputToApiResponseList(
			List<CurrencySettingsServiceOutput> cuırrenySettingsServiceOutputList);

	public abstract CurrencySettingsServiceInput mapApiRequestToServiceInput(CurrencySettingsApiRequest cuırrenySettingsApiRequest);

	public abstract List<CurrencySettingsServiceInput> mapApiRequestToServiceInputList(
			List<CurrencySettingsApiRequest> cuırrenySettingsApiRequestList);

}
