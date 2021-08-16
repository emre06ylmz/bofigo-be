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

import com.bofigo.rowmaterial.api.request.UserApiRequest;
import com.bofigo.rowmaterial.api.response.Response;
import com.bofigo.rowmaterial.api.response.UserApiResponse;
import com.bofigo.rowmaterial.domain.dto.UserServiceInput;
import com.bofigo.rowmaterial.domain.dto.UserServiceOutput;
import com.bofigo.rowmaterial.domain.service.UserService;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;
import com.bofigo.rowmaterial.mapper.UserMapper;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api("User")
@RequestMapping("/api/user")
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private UserService userService;
	private UserMapper userMapper;

	public UserController(UserService userService,
			UserMapper userMapper) {
		this.userService = userService;
		this.userMapper = userMapper;
	}

	@GetMapping(path = "")
	public Response<List<UserApiResponse>> listUser() throws DataNotFoundException {
		List<UserServiceOutput> userServiceOutputList = userService
				.listAll();
		List<UserApiResponse> userApiResponseList = userMapper
				.mapServiceOutputToApiResponseList(userServiceOutputList);
		return new Response<>(userApiResponseList);
	}

	@GetMapping(path = "/{id}")
	public Response<UserApiResponse> getUser(@PathVariable("id") Integer id)
			throws DataNotFoundException {
		UserServiceOutput userServiceOutput = userService
				.getUserById(id);
		UserApiResponse userApiResponse = userMapper
				.mapServiceOutputToApiResponse(userServiceOutput);
		return new Response<>(userApiResponse);
	}

	@PostMapping(path = "")
	public Response<UserApiResponse> createUser(
			@RequestBody @Validated UserApiRequest userApiRequest)
			throws DataNotFoundException, DataAlreadyExistException {

		UserServiceInput userServiceInput = userMapper
				.mapApiRequestToServiceInput(userApiRequest);
		UserServiceOutput userServiceOutput = userService
				.createUser(userServiceInput);

		UserApiResponse userApiResponse = userMapper
				.mapServiceOutputToApiResponse(userServiceOutput);

		return new Response<>(userApiResponse);
	}

	@PutMapping(path = "/{id}")
	public Response<UserApiResponse> updateUser(@PathVariable("id") Integer id,
			@RequestBody @Validated UserApiRequest userApiRequest)
			throws DataNotFoundException, DataAlreadyExistException {

		UserServiceInput userServiceInput = userMapper
				.mapApiRequestToServiceInput(userApiRequest);
		UserServiceOutput userServiceOutput = userService
				.updateUser(id, userServiceInput);

		UserApiResponse userApiResponse = userMapper
				.mapServiceOutputToApiResponse(userServiceOutput);

		return new Response<>(userApiResponse);
	}

	@DeleteMapping(path = "/{id}")
	public Response<UserApiResponse> deleteUser(@PathVariable("id") Integer id)
			throws DataNotFoundException, DataAlreadyExistException {

		UserServiceOutput userServiceOutput = userService
				.deleteUser(id);

		UserApiResponse userApiResponse = userMapper
				.mapServiceOutputToApiResponse(userServiceOutput);

		return new Response<>(userApiResponse);
	}

}