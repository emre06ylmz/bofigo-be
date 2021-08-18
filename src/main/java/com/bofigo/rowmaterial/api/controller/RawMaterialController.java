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

import com.bofigo.rowmaterial.api.request.RawMaterialApiRequest;
import com.bofigo.rowmaterial.api.response.RawMaterialApiResponse;
import com.bofigo.rowmaterial.api.response.Response;
import com.bofigo.rowmaterial.domain.dto.input.RawMaterialServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.RawMaterialServiceOutput;
import com.bofigo.rowmaterial.domain.service.rawmaterial.RawMaterialService;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;
import com.bofigo.rowmaterial.mapper.RawMaterialMapper;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api("Raw Material")
@RequestMapping("/api/rawmaterial")
public class RawMaterialController {

	private static Logger logger = LoggerFactory.getLogger(RawMaterialController.class);

	private RawMaterialService rawMaterialService;
	private RawMaterialMapper rawMaterialMapper;

	public RawMaterialController(RawMaterialService rawMaterialService, RawMaterialMapper rawMaterialMapper) {
		this.rawMaterialService = rawMaterialService;
		this.rawMaterialMapper = rawMaterialMapper;
	}

	@GetMapping(path = "")
	public Response<List<RawMaterialApiResponse>> listRawMaterial() throws DataNotFoundException {
		List<RawMaterialServiceOutput> rawMaterialServiceOutputList = rawMaterialService.listAll();
		List<RawMaterialApiResponse> rawMaterialApiResponseList = rawMaterialMapper
				.mapServiceOutputToApiResponseList(rawMaterialServiceOutputList);
		return new Response<>(rawMaterialApiResponseList);
	}

	@GetMapping(path = "/{id}")
	public Response<RawMaterialApiResponse> getRawMaterial(@PathVariable("id") Integer id)
			throws DataNotFoundException {
		RawMaterialServiceOutput rawMaterialServiceOutput = rawMaterialService.getRawMaterialById(id);
		RawMaterialApiResponse rawMaterialApiResponse = rawMaterialMapper
				.mapServiceOutputToApiResponse(rawMaterialServiceOutput);
		return new Response<>(rawMaterialApiResponse);
	}

	@PostMapping(path = "")
	public Response<RawMaterialApiResponse> createRawMaterial(
			@RequestBody @Validated RawMaterialApiRequest rawMaterialApiRequest)
			throws DataNotFoundException, DataAlreadyExistException {

		RawMaterialServiceInput rawMaterialServiceInput = rawMaterialMapper
				.mapApiRequestToServiceInput(rawMaterialApiRequest);
		
		RawMaterialServiceOutput rawMaterialServiceOutput = rawMaterialService
				.createRawMaterial(rawMaterialServiceInput);

		RawMaterialApiResponse rawMaterialApiResponse = rawMaterialMapper
				.mapServiceOutputToApiResponse(rawMaterialServiceOutput);

		return new Response<>(rawMaterialApiResponse);
	}

	@PutMapping(path = "/{id}")
	public Response<RawMaterialApiResponse> updateRawMaterial(@PathVariable("id") Integer id,
			@RequestBody @Validated RawMaterialApiRequest rawMaterialApiRequest)
			throws DataNotFoundException, DataAlreadyExistException {

		RawMaterialServiceInput rawMaterialServiceInput = rawMaterialMapper
				.mapApiRequestToServiceInput(rawMaterialApiRequest);
		RawMaterialServiceOutput rawMaterialServiceOutput = rawMaterialService.updateRawMaterial(id,
				rawMaterialServiceInput);

		RawMaterialApiResponse rawMaterialApiResponse = rawMaterialMapper
				.mapServiceOutputToApiResponse(rawMaterialServiceOutput);

		return new Response<>(rawMaterialApiResponse);
	}

	@DeleteMapping(path = "/{id}")
	public Response<RawMaterialApiResponse> deleteRawMaterial(@PathVariable("id") Integer id)
			throws DataNotFoundException, DataAlreadyExistException {

		RawMaterialServiceOutput rawMaterialServiceOutput = rawMaterialService.deleteRawMaterial(id);

		RawMaterialApiResponse rawMaterialApiResponse = rawMaterialMapper
				.mapServiceOutputToApiResponse(rawMaterialServiceOutput);

		return new Response<>(rawMaterialApiResponse);
	}

}
