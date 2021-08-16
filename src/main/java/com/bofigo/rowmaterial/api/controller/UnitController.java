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

import com.bofigo.rowmaterial.api.request.UnitApiRequest;
import com.bofigo.rowmaterial.api.response.UnitApiResponse;
import com.bofigo.rowmaterial.api.response.Response;
import com.bofigo.rowmaterial.domain.dto.UnitServiceInput;
import com.bofigo.rowmaterial.domain.dto.UnitServiceOutput;
import com.bofigo.rowmaterial.domain.service.UnitService;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;
import com.bofigo.rowmaterial.mapper.UnitMapper;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api("Unit Category")
@RequestMapping("/api/unit")
public class UnitController {

	private static Logger logger = LoggerFactory.getLogger(UnitController.class);
	
	private UnitService unitService;
	private UnitMapper unitMapper;

	public UnitController(UnitService unitService,
			UnitMapper unitMapper) {
		this.unitService = unitService;
		this.unitMapper = unitMapper;
	}

	@GetMapping(path = "")
	public Response<List<UnitApiResponse>> listUnit() throws DataNotFoundException {
		List<UnitServiceOutput> unitServiceOutputList = unitService
				.listAll();
		List<UnitApiResponse> unitApiResponseList = unitMapper
				.mapServiceOutputToApiResponseList(unitServiceOutputList);
		return new Response<>(unitApiResponseList);
	}

	@GetMapping(path = "/{id}")
	public Response<UnitApiResponse> getUnit(@PathVariable("id") Integer id)
			throws DataNotFoundException {
		UnitServiceOutput unitServiceOutput = unitService
				.getUnitById(id);
		UnitApiResponse unitApiResponse = unitMapper
				.mapServiceOutputToApiResponse(unitServiceOutput);
		return new Response<>(unitApiResponse);
	}

	@PostMapping(path = "")
	public Response<UnitApiResponse> createUnit(
			@RequestBody @Validated UnitApiRequest unitApiRequest)
			throws DataNotFoundException, DataAlreadyExistException {

		UnitServiceInput unitServiceInput = unitMapper
				.mapApiRequestToServiceInput(unitApiRequest);
		UnitServiceOutput unitServiceOutput = unitService
				.createUnit(unitServiceInput);

		UnitApiResponse unitApiResponse = unitMapper
				.mapServiceOutputToApiResponse(unitServiceOutput);

		return new Response<>(unitApiResponse);
	}

	@PutMapping(path = "/{id}")
	public Response<UnitApiResponse> updateUnit(@PathVariable("id") Integer id,
			@RequestBody @Validated UnitApiRequest unitApiRequest)
			throws DataNotFoundException, DataAlreadyExistException {

		UnitServiceInput unitServiceInput = unitMapper
				.mapApiRequestToServiceInput(unitApiRequest);
		UnitServiceOutput unitServiceOutput = unitService
				.updateUnit(id, unitServiceInput);

		UnitApiResponse unitApiResponse = unitMapper
				.mapServiceOutputToApiResponse(unitServiceOutput);

		return new Response<>(unitApiResponse);
	}

	@DeleteMapping(path = "/{id}")
	public Response<UnitApiResponse> deleteUnit(@PathVariable("id") Integer id)
			throws DataNotFoundException, DataAlreadyExistException {

		UnitServiceOutput unitServiceOutput = unitService
				.deleteUnit(id);

		UnitApiResponse unitApiResponse = unitMapper
				.mapServiceOutputToApiResponse(unitServiceOutput);

		return new Response<>(unitApiResponse);
	}

}
