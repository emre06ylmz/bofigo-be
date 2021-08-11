package com.bofigo.rowmaterial.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.bofigo.rowmaterial.api.request.UserTypeApiRequest;
import com.bofigo.rowmaterial.api.response.UserTypeApiResponse;
import com.bofigo.rowmaterial.dao.model.UserTypeModel;
import com.bofigo.rowmaterial.domain.dto.UserTypeServiceInput;
import com.bofigo.rowmaterial.domain.dto.UserTypeServiceOutput;

@Mapper(componentModel = "spring")
public abstract class UserTypeMapper {

	public static final UserTypeMapper INSTANCE = Mappers.getMapper(UserTypeMapper.class);

	public abstract UserTypeServiceOutput mapModelToServiceOutput(UserTypeModel userCategoryModel);

	public abstract List<UserTypeServiceOutput> mapModelToServiceOutputList(List<UserTypeModel> userCategoryModelList);

	public abstract UserTypeModel mapServiceInputToModel(UserTypeServiceInput userCategoryServiceInput);

	public abstract UserTypeApiResponse mapServiceOutputToApiResponse(UserTypeServiceOutput userCategoryServiceOutput);

	public abstract List<UserTypeApiResponse> mapServiceOutputToApiResponseList(
			List<UserTypeServiceOutput> userCategoryServiceOutputList);

	public abstract UserTypeServiceInput mapApiRequestToServiceInput(UserTypeApiRequest userCategoryApiRequest);

	public abstract List<UserTypeServiceInput> mapApiRequestToServiceInputList(
			List<UserTypeApiRequest> userCategoryApiRequestList);

}
