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

import com.bofigo.rowmaterial.api.request.ProductMaterialApiRequest;
import com.bofigo.rowmaterial.api.response.ProductMaterialApiResponse;
import com.bofigo.rowmaterial.api.response.Response;
import com.bofigo.rowmaterial.domain.dto.input.ProductMaterialServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.ProductMaterialServiceOutput;
import com.bofigo.rowmaterial.domain.service.productmaterial.ProductMaterialService;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;
import com.bofigo.rowmaterial.mapper.ProductMaterialMapper;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api("Product Material")
@RequestMapping("/api/productmaterial")
public class ProductMaterialController {

	private static Logger logger = LoggerFactory.getLogger(ProductMaterialController.class);

	private ProductMaterialService productMaterialService;
	private ProductMaterialMapper productMaterialMapper;

	public ProductMaterialController(ProductMaterialService productMaterialService,
			ProductMaterialMapper productMaterialMapper) {
		this.productMaterialService = productMaterialService;
		this.productMaterialMapper = productMaterialMapper;
	}

	@GetMapping(path = "")
	public Response<List<ProductMaterialApiResponse>> listProductMaterial() throws DataNotFoundException {
		List<ProductMaterialServiceOutput> productMaterialServiceOutputList = productMaterialService.listAll();
		List<ProductMaterialApiResponse> productMaterialApiResponseList = productMaterialMapper
				.mapServiceOutputToApiResponseList(productMaterialServiceOutputList);
		return new Response<>(productMaterialApiResponseList);
	}

	@GetMapping(path = "/listByProductId/{id}")
	public Response<List<ProductMaterialApiResponse>> listProductMaterialByRawMaterialCategoryId(
			@PathVariable("id") Integer productId) throws DataNotFoundException {
		List<ProductMaterialServiceOutput> productMaterialServiceOutputList = productMaterialService
				.listByProductId(productId);
		List<ProductMaterialApiResponse> productMaterialApiResponseList = productMaterialMapper
				.mapServiceOutputToApiResponseList(productMaterialServiceOutputList);
		return new Response<>(productMaterialApiResponseList);
	}

	@GetMapping(path = "/{id}")
	public Response<ProductMaterialApiResponse> getProductMaterial(@PathVariable("id") Integer id)
			throws DataNotFoundException {
		ProductMaterialServiceOutput productMaterialServiceOutput = productMaterialService.getProductMaterialById(id);
		ProductMaterialApiResponse productMaterialApiResponse = productMaterialMapper
				.mapServiceOutputToApiResponse(productMaterialServiceOutput);
		return new Response<>(productMaterialApiResponse);
	}

	@PostMapping(path = "")
	public Response<ProductMaterialApiResponse> createProductMaterial(
			@RequestBody @Validated ProductMaterialApiRequest productMaterialApiRequest)
			throws DataNotFoundException, DataAlreadyExistException {

		ProductMaterialServiceInput productMaterialServiceInput = productMaterialMapper
				.mapApiRequestToServiceInput(productMaterialApiRequest);

		ProductMaterialServiceOutput productMaterialServiceOutput = productMaterialService
				.createProductMaterial(productMaterialServiceInput);

		ProductMaterialApiResponse productMaterialApiResponse = productMaterialMapper
				.mapServiceOutputToApiResponse(productMaterialServiceOutput);

		return new Response<>(productMaterialApiResponse);
	}

	@PutMapping(path = "/{id}")
	public Response<ProductMaterialApiResponse> updateProductMaterial(@PathVariable("id") Integer id,
			@RequestBody @Validated ProductMaterialApiRequest productMaterialApiRequest)
			throws DataNotFoundException, DataAlreadyExistException {

		ProductMaterialServiceInput productMaterialServiceInput = productMaterialMapper
				.mapApiRequestToServiceInput(productMaterialApiRequest);
		ProductMaterialServiceOutput productMaterialServiceOutput = productMaterialService.updateProductMaterial(id,
				productMaterialServiceInput);

		ProductMaterialApiResponse productMaterialApiResponse = productMaterialMapper
				.mapServiceOutputToApiResponse(productMaterialServiceOutput);

		return new Response<>(productMaterialApiResponse);
	}

	@DeleteMapping(path = "/{id}")
	public Response<ProductMaterialApiResponse> deleteProductMaterial(@PathVariable("id") Integer id)
			throws DataNotFoundException, DataAlreadyExistException {

		ProductMaterialServiceOutput productMaterialServiceOutput = productMaterialService.deleteProductMaterial(id);

		ProductMaterialApiResponse productMaterialApiResponse = productMaterialMapper
				.mapServiceOutputToApiResponse(productMaterialServiceOutput);

		return new Response<>(productMaterialApiResponse);
	}

}
