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

import com.bofigo.rowmaterial.api.request.ProductModelCodeApiRequest;
import com.bofigo.rowmaterial.api.response.ProductModelCodeApiResponse;
import com.bofigo.rowmaterial.api.response.Response;
import com.bofigo.rowmaterial.domain.dto.input.ProductModelCodeServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.ProductModelCodeServiceOutput;
import com.bofigo.rowmaterial.domain.service.productmodelcode.ProductModelCodeService;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;
import com.bofigo.rowmaterial.mapper.ProductModelCodeMapper;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api("Product Model Code")
@RequestMapping("/api/productmodelcode")
public class ProductModelCodeController {

	private static Logger logger = LoggerFactory.getLogger(ProductModelCodeController.class);

	private ProductModelCodeService productModelCodeService;
	private ProductModelCodeMapper productModelCodeMapper;

	public ProductModelCodeController(ProductModelCodeService productModelCodeService,
			ProductModelCodeMapper productModelCodeMapper) {
		this.productModelCodeService = productModelCodeService;
		this.productModelCodeMapper = productModelCodeMapper;
	}

	@GetMapping(path = "")
	public Response<List<ProductModelCodeApiResponse>> listProductModelCode() throws DataNotFoundException {
		List<ProductModelCodeServiceOutput> productModelCodeServiceOutputList = productModelCodeService.listAll();
		List<ProductModelCodeApiResponse> productModelCodeApiResponseList = productModelCodeMapper
				.mapServiceOutputToApiResponseList(productModelCodeServiceOutputList);
		return new Response<>(productModelCodeApiResponseList);
	}

	@GetMapping(path = "/{id}")
	public Response<ProductModelCodeApiResponse> getProductModelCode(@PathVariable("id") Integer id)
			throws DataNotFoundException {
		ProductModelCodeServiceOutput productModelCodeServiceOutput = productModelCodeService
				.getProductModelCodeById(id);
		ProductModelCodeApiResponse productModelCodeApiResponse = productModelCodeMapper
				.mapServiceOutputToApiResponse(productModelCodeServiceOutput);
		return new Response<>(productModelCodeApiResponse);
	}

	@PostMapping(path = "")
	public Response<ProductModelCodeApiResponse> createProductModelCode(
			@RequestBody @Validated ProductModelCodeApiRequest productModelCodeApiRequest)
			throws DataNotFoundException, DataAlreadyExistException {

		ProductModelCodeServiceInput productModelCodeServiceInput = productModelCodeMapper
				.mapApiRequestToServiceInput(productModelCodeApiRequest);
		ProductModelCodeServiceOutput productModelCodeServiceOutput = productModelCodeService
				.createProductModelCode(productModelCodeServiceInput);

		ProductModelCodeApiResponse productModelCodeApiResponse = productModelCodeMapper
				.mapServiceOutputToApiResponse(productModelCodeServiceOutput);

		return new Response<>(productModelCodeApiResponse);
	}

	@PutMapping(path = "/{id}")
	public Response<ProductModelCodeApiResponse> updateProductModelCode(@PathVariable("id") Integer id,
			@RequestBody @Validated ProductModelCodeApiRequest productModelCodeApiRequest)
			throws DataNotFoundException, DataAlreadyExistException {

		ProductModelCodeServiceInput productModelCodeServiceInput = productModelCodeMapper
				.mapApiRequestToServiceInput(productModelCodeApiRequest);
		ProductModelCodeServiceOutput productModelCodeServiceOutput = productModelCodeService.updateProductModelCode(id,
				productModelCodeServiceInput);

		ProductModelCodeApiResponse productModelCodeApiResponse = productModelCodeMapper
				.mapServiceOutputToApiResponse(productModelCodeServiceOutput);

		return new Response<>(productModelCodeApiResponse);
	}

	@DeleteMapping(path = "/{id}")
	public Response<ProductModelCodeApiResponse> deleteProductModelCode(@PathVariable("id") Integer id)
			throws DataNotFoundException, DataAlreadyExistException {

		ProductModelCodeServiceOutput productModelCodeServiceOutput = productModelCodeService
				.deleteProductModelCode(id);

		ProductModelCodeApiResponse productModelCodeApiResponse = productModelCodeMapper
				.mapServiceOutputToApiResponse(productModelCodeServiceOutput);

		return new Response<>(productModelCodeApiResponse);
	}

}
