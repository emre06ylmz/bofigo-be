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

import com.bofigo.rowmaterial.api.request.ProductApiRequest;
import com.bofigo.rowmaterial.api.response.ProductApiResponse;
import com.bofigo.rowmaterial.api.response.Response;
import com.bofigo.rowmaterial.domain.dto.input.ProductServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.ProductServiceOutput;
import com.bofigo.rowmaterial.domain.service.product.ProductService;
import com.bofigo.rowmaterial.domain.service.productmaterial.ProductMaterialService;
import com.bofigo.rowmaterial.domain.service.purchase.PurchaseService;
import com.bofigo.rowmaterial.domain.service.rawmaterial.RawMaterialService;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;
import com.bofigo.rowmaterial.exception.OperationNotValidException;
import com.bofigo.rowmaterial.mapper.ProductMapper;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api("Product")
@RequestMapping("/api/product")
public class ProductController {

	private static Logger logger = LoggerFactory.getLogger(ProductController.class);

	private ProductService productService;
	private PurchaseService purchaseService;
	private RawMaterialService rawMaterialService;
	private ProductMaterialService productMaterialService;
	private ProductMapper productMapper;

	public ProductController(ProductService productService, ProductMapper productMapper,
			PurchaseService purchaseService, RawMaterialService rawMaterialService,
			ProductMaterialService productMaterialService) {
		this.productService = productService;
		this.productMapper = productMapper;
		this.purchaseService = purchaseService;
		this.productMaterialService = productMaterialService;
		this.rawMaterialService = rawMaterialService;
	}

	@GetMapping(path = "")
	public Response<List<ProductApiResponse>> listProduct() throws DataNotFoundException {
		List<ProductServiceOutput> productServiceOutputList = productService.listAll();
		List<ProductApiResponse> productApiResponseList = productMapper
				.mapServiceOutputToApiResponseList(productServiceOutputList);
		return new Response<>(productApiResponseList);
	}

	@GetMapping(path = "/calculate")
	public Response<String> calculate() throws DataNotFoundException, OperationNotValidException {
		productService.calculateProductCosts();
		return new Response<>("OK");
	}

	@GetMapping(path = "/{id}")
	public Response<ProductApiResponse> getProduct(@PathVariable("id") Integer id) throws DataNotFoundException {
		ProductServiceOutput productServiceOutput = productService.getProductById(id);
		ProductApiResponse productApiResponse = productMapper.mapServiceOutputToApiResponse(productServiceOutput);
		return new Response<>(productApiResponse);
	}

	@PostMapping(path = "")
	public Response<ProductApiResponse> createProduct(@RequestBody @Validated ProductApiRequest productApiRequest)
			throws DataNotFoundException, DataAlreadyExistException {

		ProductServiceInput productServiceInput = productMapper.mapApiRequestToServiceInput(productApiRequest);
		ProductServiceOutput productServiceOutput = productService.createProduct(productServiceInput);

		ProductApiResponse productApiResponse = productMapper.mapServiceOutputToApiResponse(productServiceOutput);

		return new Response<>(productApiResponse);
	}

	@PutMapping(path = "/{id}")
	public Response<ProductApiResponse> updateProduct(@PathVariable("id") Integer id,
			@RequestBody @Validated ProductApiRequest productApiRequest)
			throws DataNotFoundException, DataAlreadyExistException {

		ProductServiceInput productServiceInput = productMapper.mapApiRequestToServiceInput(productApiRequest);
		ProductServiceOutput productServiceOutput = productService.updateProduct(id, productServiceInput);

		ProductApiResponse productApiResponse = productMapper.mapServiceOutputToApiResponse(productServiceOutput);

		return new Response<>(productApiResponse);
	}

	@DeleteMapping(path = "/{id}")
	public Response<ProductApiResponse> deleteProduct(@PathVariable("id") Integer id)
			throws DataNotFoundException, DataAlreadyExistException, OperationNotValidException {

		ProductServiceOutput productServiceOutput = productService.deleteProduct(id);

		ProductApiResponse productApiResponse = productMapper.mapServiceOutputToApiResponse(productServiceOutput);

		return new Response<>(productApiResponse);
	}

}
