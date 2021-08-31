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

import com.bofigo.rowmaterial.api.request.ProductionApiRequest;
import com.bofigo.rowmaterial.api.response.ProductionApiResponse;
import com.bofigo.rowmaterial.api.response.Response;
import com.bofigo.rowmaterial.domain.dto.input.ProductionServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.ProductionServiceOutput;
import com.bofigo.rowmaterial.domain.service.production.ProductionService;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;
import com.bofigo.rowmaterial.mapper.ProductionMapper;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api("Production")
@RequestMapping("/api/production")
public class ProductionController {

	private static Logger logger = LoggerFactory.getLogger(ProductionController.class);

	private ProductionService productionService;
	private ProductionMapper productionMapper;

	public ProductionController(ProductionService productionService, ProductionMapper productionMapper) {
		this.productionService = productionService;
		this.productionMapper = productionMapper;
	}

	@GetMapping(path = "")
	public Response<List<ProductionApiResponse>> listProduction() throws DataNotFoundException {
		List<ProductionServiceOutput> productionServiceOutputList = productionService.listAll();
		List<ProductionApiResponse> productionApiResponseList = productionMapper
				.mapServiceOutputToApiResponseList(productionServiceOutputList);
		return new Response<>(productionApiResponseList);
	}

	@GetMapping(path = "/listByProductId/{id}")
	public Response<List<ProductionApiResponse>> listProductionByRawMaterialCategoryId(
			@PathVariable("id") Integer productId) throws DataNotFoundException {
		List<ProductionServiceOutput> productionServiceOutputList = productionService.listByProductId(productId);
		List<ProductionApiResponse> productionApiResponseList = productionMapper
				.mapServiceOutputToApiResponseList(productionServiceOutputList);
		return new Response<>(productionApiResponseList);
	}

	@GetMapping(path = "/{id}")
	public Response<ProductionApiResponse> getProduction(@PathVariable("id") Integer id) throws DataNotFoundException {
		ProductionServiceOutput productionServiceOutput = productionService.getProductionById(id);
		ProductionApiResponse productionApiResponse = productionMapper
				.mapServiceOutputToApiResponse(productionServiceOutput);
		return new Response<>(productionApiResponse);
	}

	@PostMapping(path = "")
	public Response<ProductionApiResponse> createProduction(
			@RequestBody @Validated ProductionApiRequest productionApiRequest)
			throws DataNotFoundException, DataAlreadyExistException {

		ProductionServiceInput productionServiceInput = productionMapper
				.mapApiRequestToServiceInput(productionApiRequest);

		ProductionServiceOutput productionServiceOutput = productionService.createProduction(productionServiceInput);

		ProductionApiResponse productionApiResponse = productionMapper
				.mapServiceOutputToApiResponse(productionServiceOutput);

		return new Response<>(productionApiResponse);
	}

	@PutMapping(path = "/{id}")
	public Response<ProductionApiResponse> updateProduction(@PathVariable("id") Integer id,
			@RequestBody @Validated ProductionApiRequest productionApiRequest)
			throws DataNotFoundException, DataAlreadyExistException {

		ProductionServiceInput productionServiceInput = productionMapper
				.mapApiRequestToServiceInput(productionApiRequest);
		ProductionServiceOutput productionServiceOutput = productionService.updateProduction(id,
				productionServiceInput);

		ProductionApiResponse productionApiResponse = productionMapper
				.mapServiceOutputToApiResponse(productionServiceOutput);

		return new Response<>(productionApiResponse);
	}

	@DeleteMapping(path = "/{id}")
	public Response<ProductionApiResponse> deleteProduction(@PathVariable("id") Integer id)
			throws DataNotFoundException, DataAlreadyExistException {

		ProductionServiceOutput productionServiceOutput = productionService.deleteProduction(id);

		ProductionApiResponse productionApiResponse = productionMapper
				.mapServiceOutputToApiResponse(productionServiceOutput);

		return new Response<>(productionApiResponse);
	}

}
