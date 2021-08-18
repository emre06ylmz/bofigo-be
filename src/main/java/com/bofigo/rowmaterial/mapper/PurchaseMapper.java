package com.bofigo.rowmaterial.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.bofigo.rowmaterial.api.request.PurchaseApiRequest;
import com.bofigo.rowmaterial.api.response.PurchaseApiResponse;
import com.bofigo.rowmaterial.dao.model.PurchaseModel;
import com.bofigo.rowmaterial.domain.dto.input.PurchaseServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.PurchaseServiceOutput;

@Mapper(componentModel = "spring")
public abstract class PurchaseMapper {

	public static final PurchaseMapper INSTANCE = Mappers.getMapper(PurchaseMapper.class);

	public abstract PurchaseServiceOutput mapModelToServiceOutput(
			PurchaseModel purchaseModel);

	public abstract List<PurchaseServiceOutput> mapModelToServiceOutputList(
			List<PurchaseModel> purchaseModelList);

	public abstract PurchaseModel mapServiceInputToModel(
			PurchaseServiceInput purchaseServiceInput);

	public abstract PurchaseApiResponse mapServiceOutputToApiResponse(
			PurchaseServiceOutput purchaseServiceOutput);

	public abstract List<PurchaseApiResponse> mapServiceOutputToApiResponseList(
			List<PurchaseServiceOutput> purchaseServiceOutputList);

	public abstract PurchaseServiceInput mapApiRequestToServiceInput(
			PurchaseApiRequest purchaseApiRequest);

	public abstract List<PurchaseServiceInput> mapApiRequestToServiceInputList(
			List<PurchaseApiRequest> purchaseApiRequestList);

}
