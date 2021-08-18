package com.bofigo.rowmaterial.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.bofigo.rowmaterial.api.request.SupplierApiRequest;
import com.bofigo.rowmaterial.api.response.SupplierApiResponse;
import com.bofigo.rowmaterial.dao.model.SupplierModel;
import com.bofigo.rowmaterial.domain.dto.input.SupplierServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.SupplierServiceOutput;

@Mapper(componentModel = "spring")
public abstract class SupplierMapper {

	public static final SupplierMapper INSTANCE = Mappers.getMapper(SupplierMapper.class);

	public abstract SupplierServiceOutput mapModelToServiceOutput(
			SupplierModel supplierModel);

	public abstract List<SupplierServiceOutput> mapModelToServiceOutputList(
			List<SupplierModel> supplierModelList);

	public abstract SupplierModel mapServiceInputToModel(
			SupplierServiceInput supplierServiceInput);

	public abstract SupplierApiResponse mapServiceOutputToApiResponse(
			SupplierServiceOutput supplierServiceOutput);

	public abstract List<SupplierApiResponse> mapServiceOutputToApiResponseList(
			List<SupplierServiceOutput> supplierServiceOutputList);

	public abstract SupplierServiceInput mapApiRequestToServiceInput(
			SupplierApiRequest supplierApiRequest);

	public abstract List<SupplierServiceInput> mapApiRequestToServiceInputList(
			List<SupplierApiRequest> supplierApiRequestList);

}
