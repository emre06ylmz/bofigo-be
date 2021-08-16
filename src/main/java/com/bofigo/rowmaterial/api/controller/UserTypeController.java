package com.bofigo.rowmaterial.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bofigo.rowmaterial.api.request.UserTypeApiRequest;
import com.bofigo.rowmaterial.api.response.Response;
import com.bofigo.rowmaterial.api.response.UserTypeApiResponse;
import com.bofigo.rowmaterial.domain.dto.UserTypeServiceInput;
import com.bofigo.rowmaterial.domain.dto.UserTypeServiceOutput;
import com.bofigo.rowmaterial.domain.service.UserTypeService;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;
import com.bofigo.rowmaterial.mapper.UserTypeMapper;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api("UserType")
@RequestMapping("/api/userType")
public class UserTypeController {

	private static Logger logger = LoggerFactory.getLogger(UserTypeController.class);
	
	private UserTypeService userTypeService;
	private UserTypeMapper userTypeMapper;

	public UserTypeController(UserTypeService userTypeService,
			UserTypeMapper userTypeMapper) {
		this.userTypeService = userTypeService;
		this.userTypeMapper = userTypeMapper;
	}

	@GetMapping(path = "")
	public Response<List<UserTypeApiResponse>> listUserType() throws DataNotFoundException {
		List<UserTypeServiceOutput> userTypeServiceOutputList = userTypeService
				.listAll();
		List<UserTypeApiResponse> userTypeApiResponseList = userTypeMapper
				.mapServiceOutputToApiResponseList(userTypeServiceOutputList);
		return new Response<>(userTypeApiResponseList);
	}

	@GetMapping(path = "/{id}")
	public Response<UserTypeApiResponse> getUserType(@PathVariable("id") Integer id)
			throws DataNotFoundException {
		UserTypeServiceOutput userTypeServiceOutput = userTypeService
				.getUserTypeById(id);
		UserTypeApiResponse userTypeApiResponse = userTypeMapper
				.mapServiceOutputToApiResponse(userTypeServiceOutput);
		return new Response<>(userTypeApiResponse);
	}

	@PostMapping(path = "")
	public Response<UserTypeApiResponse> createUserType(
			@RequestBody @Validated UserTypeApiRequest userTypeApiRequest)
			throws DataNotFoundException, DataAlreadyExistException {

		UserTypeServiceInput userTypeServiceInput = userTypeMapper
				.mapApiRequestToServiceInput(userTypeApiRequest);
		UserTypeServiceOutput userTypeServiceOutput = userTypeService
				.createUserType(userTypeServiceInput);

		UserTypeApiResponse userTypeApiResponse = userTypeMapper
				.mapServiceOutputToApiResponse(userTypeServiceOutput);

		return new Response<>(userTypeApiResponse);
	}

	@PutMapping(path = "/{id}")
	public Response<UserTypeApiResponse> deleteUserType(@PathVariable("id") Integer id,
			@RequestBody @Validated UserTypeApiRequest userTypeApiRequest)
			throws DataNotFoundException, DataAlreadyExistException {

		UserTypeServiceInput userTypeServiceInput = userTypeMapper
				.mapApiRequestToServiceInput(userTypeApiRequest);
		UserTypeServiceOutput userTypeServiceOutput = userTypeService
				.updateUserType(id, userTypeServiceInput);

		UserTypeApiResponse userTypeApiResponse = userTypeMapper
				.mapServiceOutputToApiResponse(userTypeServiceOutput);

		return new Response<>(userTypeApiResponse);
	}

	@DeleteMapping(path = "/{id}")
	public Response<UserTypeApiResponse> updateUserType(@PathVariable("id") Integer id)
			throws DataNotFoundException, DataAlreadyExistException {

		UserTypeServiceOutput userTypeServiceOutput = userTypeService
				.deleteUserType(id);

		UserTypeApiResponse userTypeApiResponse = userTypeMapper
				.mapServiceOutputToApiResponse(userTypeServiceOutput);

		return new Response<>(userTypeApiResponse);
	}

}