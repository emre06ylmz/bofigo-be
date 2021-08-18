package com.bofigo.rowmaterial.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.bofigo.rowmaterial.api.request.UserApiRequest;
import com.bofigo.rowmaterial.api.response.UserApiResponse;
import com.bofigo.rowmaterial.dao.model.UserModel;
import com.bofigo.rowmaterial.domain.dto.input.UserServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.UserServiceOutput;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

	public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	public abstract UserServiceOutput mapModelToServiceOutput(UserModel userCategoryModel);

	public abstract List<UserServiceOutput> mapModelToServiceOutputList(List<UserModel> userCategoryModelList);

	public abstract UserModel mapServiceInputToModel(UserServiceInput userCategoryServiceInput);

	public abstract UserApiResponse mapServiceOutputToApiResponse(UserServiceOutput userCategoryServiceOutput);

	public abstract List<UserApiResponse> mapServiceOutputToApiResponseList(
			List<UserServiceOutput> userCategoryServiceOutputList);

	public abstract UserServiceInput mapApiRequestToServiceInput(UserApiRequest userCategoryApiRequest);

	public abstract List<UserServiceInput> mapApiRequestToServiceInputList(
			List<UserApiRequest> userCategoryApiRequestList);

}
