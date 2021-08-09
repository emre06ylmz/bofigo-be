package com.bofigo.rowmaterial.api.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bofigo.rowmaterial.api.request.RawMaterialCategoryApiRequest;
import com.bofigo.rowmaterial.api.response.RawMaterialCategoryApiResponse;
import com.bofigo.rowmaterial.api.response.Response;
import com.bofigo.rowmaterial.domain.dto.RawMaterialCategoryServiceInput;
import com.bofigo.rowmaterial.domain.dto.RawMaterialCategoryServiceOutput;
import com.bofigo.rowmaterial.domain.service.RawMaterialCategoryService;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;
import com.bofigo.rowmaterial.mapper.RawMaterialCategoryMapper;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api("Raw Material Category")
@RequestMapping("/api/rawmaterialcategory")
public class RawMaterialCategoryController {

	private RawMaterialCategoryService rawMaterialCategoryService;
	private RawMaterialCategoryMapper rawMaterialCategoryMapper;

	public RawMaterialCategoryController(RawMaterialCategoryService rawMaterialCategoryService,
			RawMaterialCategoryMapper rawMaterialCategoryMapper) {
		this.rawMaterialCategoryService = rawMaterialCategoryService;
		this.rawMaterialCategoryMapper = rawMaterialCategoryMapper;
	}

	@GetMapping(path = "")
	public Response<List<RawaterialCategoryApiResponse>> listRawMaterialCategory() throws DataNotFoundException {
		List<RawMaterialCategoryServiceOutput> rawMaterialCategoryServiceOutputList = rawMaterialCategoryService
				.listAll();
		List<RawMaterialCategoryApiResponse> rawMaterialCategoryApiResponseList = rawMaterialCategoryMapper
				.mapServiceOutputToApiResponseList(rawMaterialCategoryServiceOutputList);

		return new Response<>(rawMaterialCategoryApiResponseList);
	}

	@GetMapping(path = "/{id}")
	public Response<RawMaterialCategoryApiResponse> getRawMaterialCategory(@PathVariable("id") Integer id)
			throws DataNotFoundException {
		RawMaterialCategoryServiceOutput rawMaterialCategoryServiceOutput = rawMaterialCategoryService
				.getRawMaterialCategoryById(id);
		RawMaterialCategoryApiResponse rawMaterialCategoryApiResponse = rawMaterialCategoryMapper
				.mapServiceOutputToApiResponse(rawMaterialCategoryServiceOutput);
		return new Response<>(rawMaterialCategoryApiResponse);
	}

	@PostMapping(path = "")
	public Response<RawMaterialCategoryApiResponse> createRawMaterialCategory(
			@RequestBody @Validated RawMaterialCategoryApiRequest rawMaterialCategoryApiRequest)
			throws DataNotFoundException, DataAlreadyExistException {

		RawMaterialCategoryServiceInput rawMaterialCategoryServiceInput = rawMaterialCategoryMapper
				.mapApiRequestToServiceInput(rawMaterialCategoryApiRequest);
		RawMaterialCategoryServiceOutput rawMaterialCategoryServiceOutput = rawMaterialCategoryService
				.createRawMaterialCategory(rawMaterialCategoryServiceInput);

		RawMaterialCategoryApiResponse rawMaterialCategoryApiResponse = rawMaterialCategoryMapper
				.mapServiceOutputToApiResponse(rawMaterialCategoryServiceOutput);

		return new Response<>(rawMaterialCategoryApiResponse);
	}

	@PutMapping(path = "/{id}")
	public Response<RawMaterialCategoryApiResponse> updateRawMaterialCategory(@PathVariable("id") Integer id,
			@RequestBody @Validated RawMateriMalCategoryApiRequest rawMaterialCategoryApiRequest)
			throws DataNotFoundException, DataAlreadyExistException {

		RawMaterialCategoryServiceInput rawMaterialCategoryServiceInput = rawMaterialCategoryMapper
				.mapApiRequestToServiceInput(rawMaterialCategoryApiRequest);
		RawMaterialCategoryServiceOutput rawMaterialCategoryServiceOutput = rawMaterialCategoryService
				.updateRawMaterialCategory(id, rawMaterialCategoryServiceInput);

		RawMaterialCategoryApiResponse rawMaterialCategoryApiResponse = rawMaterialCategoryMapper
				.mapServiceOutputToApiResponse(rawMaterialCategoryServiceOutput);

		return new Response<>(rawMaterialCategoryApiResponse);
	}

	@DeleteMapping(path = "/{id}")
	public Response<RawMaterialCategoryApiResponse> updateRawMaterialCategory(@PathVariable("id") Integer id)
			throws DataNotFoundException, DataAlreadyExistException {

		RawMaterialCategoryServiceOutput rawMaterialCategoryServiceOutput = rawMaterialCategoryService
				.deleteRawMaterialCategory(id);

		RawMaterialCategoryApiResponse rawMaterialCategoryApiResponse = rawMaterialCategoryMapper
				.mapServiceOutputToApiResponse(rawMaterialCategoryServiceOutput);

		return new Response<>(rawMaterialCategoryApiResponse);
	}

}
