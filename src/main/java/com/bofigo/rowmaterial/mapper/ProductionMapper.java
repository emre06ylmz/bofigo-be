package com.bofigo.rowmaterial.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.bofigo.rowmaterial.api.request.ProductionApiRequest;
import com.bofigo.rowmaterial.api.response.ProductionApiResponse;
import com.bofigo.rowmaterial.dao.model.ProductionModel;
import com.bofigo.rowmaterial.domain.dto.input.ProductionServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.ProductionServiceOutput;

@Mapper(componentModel = "spring")
public abstract class ProductionMapper {

	public static final ProductionMapper INSTANCE = Mappers.getMapper(ProductionMapper.class);

	public abstract ProductionServiceOutput mapModelToServiceOutput(ProductionModel productionModel);

	public abstract List<ProductionServiceOutput> mapModelToServiceOutputList(
			List<ProductionModel> productionModelList);

	public abstract ProductionModel mapServiceInputToModel(ProductionServiceInput productionServiceInput);

	public abstract ProductionApiResponse mapServiceOutputToApiResponse(
			ProductionServiceOutput productionServiceOutput);

	public abstract List<ProductionApiResponse> mapServiceOutputToApiResponseList(
			List<ProductionServiceOutput> productionServiceOutputList);

	public abstract ProductionServiceInput mapApiRequestToServiceInput(ProductionApiRequest productionApiRequest);

	public abstract List<ProductionServiceInput> mapApiRequestToServiceInputList(
			List<ProductionApiRequest> productionApiRequestList);

}
