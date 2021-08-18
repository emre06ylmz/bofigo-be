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

import com.bofigo.rowmaterial.api.request.SupplierApiRequest;
import com.bofigo.rowmaterial.api.response.SupplierApiResponse;
import com.bofigo.rowmaterial.api.response.Response;
import com.bofigo.rowmaterial.domain.dto.input.SupplierServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.SupplierServiceOutput;
import com.bofigo.rowmaterial.domain.service.supplier.SupplierService;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;
import com.bofigo.rowmaterial.mapper.SupplierMapper;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api("Supplier Category")
@RequestMapping("/api/supplier")
public class SupplierController {

	private static Logger logger = LoggerFactory.getLogger(SupplierController.class);
	
	private SupplierService supplierService;
	private SupplierMapper supplierMapper;

	public SupplierController(SupplierService supplierService,
			SupplierMapper supplierMapper) {
		this.supplierService = supplierService;
		this.supplierMapper = supplierMapper;
	}

	@GetMapping(path = "")
	public Response<List<SupplierApiResponse>> listSupplier() throws DataNotFoundException {
		List<SupplierServiceOutput> supplierServiceOutputList = supplierService
				.listAll();
		List<SupplierApiResponse> supplierApiResponseList = supplierMapper
				.mapServiceOutputToApiResponseList(supplierServiceOutputList);
		return new Response<>(supplierApiResponseList);
	}

	@GetMapping(path = "/{id}")
	public Response<SupplierApiResponse> getSupplier(@PathVariable("id") Integer id)
			throws DataNotFoundException {
		SupplierServiceOutput supplierServiceOutput = supplierService
				.getSupplierById(id);
		SupplierApiResponse supplierApiResponse = supplierMapper
				.mapServiceOutputToApiResponse(supplierServiceOutput);
		return new Response<>(supplierApiResponse);
	}

	@PostMapping(path = "")
	public Response<SupplierApiResponse> createSupplier(
			@RequestBody @Validated SupplierApiRequest supplierApiRequest)
			throws DataNotFoundException, DataAlreadyExistException {

		SupplierServiceInput supplierServiceInput = supplierMapper
				.mapApiRequestToServiceInput(supplierApiRequest);
		SupplierServiceOutput supplierServiceOutput = supplierService
				.createSupplier(supplierServiceInput);

		SupplierApiResponse supplierApiResponse = supplierMapper
				.mapServiceOutputToApiResponse(supplierServiceOutput);

		return new Response<>(supplierApiResponse);
	}

	@PutMapping(path = "/{id}")
	public Response<SupplierApiResponse> updateSupplier(@PathVariable("id") Integer id,
			@RequestBody @Validated SupplierApiRequest supplierApiRequest)
			throws DataNotFoundException, DataAlreadyExistException {

		SupplierServiceInput supplierServiceInput = supplierMapper
				.mapApiRequestToServiceInput(supplierApiRequest);
		SupplierServiceOutput supplierServiceOutput = supplierService
				.updateSupplier(id, supplierServiceInput);

		SupplierApiResponse supplierApiResponse = supplierMapper
				.mapServiceOutputToApiResponse(supplierServiceOutput);

		return new Response<>(supplierApiResponse);
	}

	@DeleteMapping(path = "/{id}")
	public Response<SupplierApiResponse> deleteSupplier(@PathVariable("id") Integer id)
			throws DataNotFoundException, DataAlreadyExistException {

		SupplierServiceOutput supplierServiceOutput = supplierService
				.deleteSupplier(id);

		SupplierApiResponse supplierApiResponse = supplierMapper
				.mapServiceOutputToApiResponse(supplierServiceOutput);

		return new Response<>(supplierApiResponse);
	}

}
