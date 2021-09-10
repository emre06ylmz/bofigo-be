package com.bofigo.rowmaterial.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.bofigo.rowmaterial.api.request.DeliveryApiRequest;
import com.bofigo.rowmaterial.api.response.DeliveryApiResponse;
import com.bofigo.rowmaterial.dao.model.DeliveryModel;
import com.bofigo.rowmaterial.domain.dto.input.DeliveryServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.DeliveryServiceOutput;

@Mapper(componentModel = "spring")
public abstract class DeliveryMapper {

	public static final DeliveryMapper INSTANCE = Mappers.getMapper(DeliveryMapper.class);

	public abstract DeliveryServiceOutput mapModelToServiceOutput(DeliveryModel deliveryModel);

	public abstract List<DeliveryServiceOutput> mapModelToServiceOutputList(
			List<DeliveryModel> deliveryModelList);

	public abstract DeliveryModel mapServiceInputToModel(DeliveryServiceInput deliveryServiceInput);

	public abstract DeliveryApiResponse mapServiceOutputToApiResponse(
			DeliveryServiceOutput deliveryServiceOutput);

	public abstract List<DeliveryApiResponse> mapServiceOutputToApiResponseList(
			List<DeliveryServiceOutput> deliveryServiceOutputList);

	public abstract DeliveryServiceInput mapApiRequestToServiceInput(DeliveryApiRequest deliveryApiRequest);

	public abstract List<DeliveryServiceInput> mapApiRequestToServiceInputList(
			List<DeliveryApiRequest> deliveryApiRequestList);

}
