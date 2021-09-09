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

import com.bofigo.rowmaterial.api.request.ProductCategoryApiRequest;
import com.bofigo.rowmaterial.api.response.ProductCategoryApiResponse;
import com.bofigo.rowmaterial.api.response.Response;
import com.bofigo.rowmaterial.domain.dto.input.ProductCategoryServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.ProductCategoryServiceOutput;
import com.bofigo.rowmaterial.domain.service.productcategory.ProductCategoryService;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;
import com.bofigo.rowmaterial.mapper.ProductCategoryMapper;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api("Product Category")
@RequestMapping("/api/productcategory")
public class ProductCategoryController {

	private static Logger logger = LoggerFactory.getLogger(ProductCategoryController.class);

	private ProductCategoryService productCategoryService;
	private ProductCategoryMapper productCategoryMapper;

	public ProductCategoryController(ProductCategoryService productCategoryService,
			ProductCategoryMapper productCategoryMapper) {
		this.productCategoryService = productCategoryService;
		this.productCategoryMapper = productCategoryMapper;
	}

	@GetMapping(path = "")
	public Response<List<ProductCategoryApiResponse>> listProductCategory() throws DataNotFoundException {
		List<ProductCategoryServiceOutput> productCategoryServiceOutputList = productCategoryService.listAll();
		List<ProductCategoryApiResponse> productCategoryApiResponseList = productCategoryMapper
				.mapServiceOutputToApiResponseList(productCategoryServiceOutputList);
		return new Response<>(productCategoryApiResponseList);
	}

	@GetMapping(path = "/{id}")
	public Response<ProductCategoryApiResponse> getProductCategory(@PathVariable("id") Integer id)
			throws DataNotFoundException {
		ProductCategoryServiceOutput productCategoryServiceOutput = productCategoryService.getProductCategoryById(id);
		ProductCategoryApiResponse productCategoryApiResponse = productCategoryMapper
				.mapServiceOutputToApiResponse(productCategoryServiceOutput);
		return new Response<>(productCategoryApiResponse);
	}

	@PostMapping(path = "")
	public Response<ProductCategoryApiResponse> createProductCategory(
			@RequestBody @Validated ProductCategoryApiRequest productCategoryApiRequest)
			throws DataNotFoundException, DataAlreadyExistException {

		ProductCategoryServiceInput productCategoryServiceInput = productCategoryMapper
				.mapApiRequestToServiceInput(productCategoryApiRequest);
		ProductCategoryServiceOutput productCategoryServiceOutput = productCategoryService
				.createProductCategory(productCategoryServiceInput);

		ProductCategoryApiResponse productCategoryApiResponse = productCategoryMapper
				.mapServiceOutputToApiResponse(productCategoryServiceOutput);

		return new Response<>(productCategoryApiResponse);
	}

	@PutMapping(path = "/{id}")
	public Response<ProductCategoryApiResponse> updateProductCategory(@PathVariable("id") Integer id,
			@RequestBody @Validated ProductCategoryApiRequest productCategoryApiRequest)
			throws DataNotFoundException, DataAlreadyExistException {

		ProductCategoryServiceInput productCategoryServiceInput = productCategoryMapper
				.mapApiRequestToServiceInput(productCategoryApiRequest);
		ProductCategoryServiceOutput productCategoryServiceOutput = productCategoryService.updateProductCategory(id,
				productCategoryServiceInput);

		ProductCategoryApiResponse productCategoryApiResponse = productCategoryMapper
				.mapServiceOutputToApiResponse(productCategoryServiceOutput);

		return new Response<>(productCategoryApiResponse);
	}

	@DeleteMapping(path = "/{id}")
	public Response<ProductCategoryApiResponse> deleteProductCategory(@PathVariable("id") Integer id)
			throws DataNotFoundException, DataAlreadyExistException {

		ProductCategoryServiceOutput productCategoryServiceOutput = productCategoryService.deleteProductCategory(id);

		ProductCategoryApiResponse productCategoryApiResponse = productCategoryMapper
				.mapServiceOutputToApiResponse(productCategoryServiceOutput);

		return new Response<>(productCategoryApiResponse);
	}

}
