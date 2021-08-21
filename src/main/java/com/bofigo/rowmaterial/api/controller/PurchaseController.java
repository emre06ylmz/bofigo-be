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

import com.bofigo.rowmaterial.api.request.PurchaseApiRequest;
import com.bofigo.rowmaterial.api.response.PurchaseApiResponse;
import com.bofigo.rowmaterial.api.response.RawMaterialApiResponse;
import com.bofigo.rowmaterial.api.response.Response;
import com.bofigo.rowmaterial.domain.dto.input.PurchaseServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.PurchaseServiceOutput;
import com.bofigo.rowmaterial.domain.dto.output.RawMaterialServiceOutput;
import com.bofigo.rowmaterial.domain.service.purchase.PurchaseService;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;
import com.bofigo.rowmaterial.mapper.PurchaseMapper;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api("Purchase")
@RequestMapping("/api/purchase")
public class PurchaseController {

	private static Logger logger = LoggerFactory.getLogger(PurchaseController.class);

	private PurchaseService purchaseService;
	private PurchaseMapper purchaseMapper;

	public PurchaseController(PurchaseService purchaseService, PurchaseMapper purchaseMapper) {
		this.purchaseService = purchaseService;
		this.purchaseMapper = purchaseMapper;
	}

	@GetMapping(path = "")
	public Response<List<PurchaseApiResponse>> listPurchase() throws DataNotFoundException {
		List<PurchaseServiceOutput> purchaseServiceOutputList = purchaseService.listAll();
		List<PurchaseApiResponse> purchaseApiResponseList = purchaseMapper
				.mapServiceOutputToApiResponseList(purchaseServiceOutputList);
		return new Response<>(purchaseApiResponseList);
	}
	
	@GetMapping(path = "/listByMaterialId/{id}")
	public Response<List<PurchaseApiResponse>> listPurchaseByRawMaterialId(@PathVariable("id") Integer rawMaterialId) throws DataNotFoundException {
		List<PurchaseServiceOutput> purchaseServiceOutputList = purchaseService.listByMaterialId(rawMaterialId);
		List<PurchaseApiResponse> purchaseApiResponseList = purchaseMapper
				.mapServiceOutputToApiResponseList(purchaseServiceOutputList);
		return new Response<>(purchaseApiResponseList);
	}
	

	@GetMapping(path = "/{id}")
	public Response<PurchaseApiResponse> getPurchase(@PathVariable("id") Integer id) throws DataNotFoundException {
		PurchaseServiceOutput purchaseServiceOutput = purchaseService.getPurchaseById(id);
		PurchaseApiResponse purchaseApiResponse = purchaseMapper.mapServiceOutputToApiResponse(purchaseServiceOutput);
		return new Response<>(purchaseApiResponse);
	}

	@PostMapping(path = "")
	public Response<PurchaseApiResponse> createPurchase(@RequestBody @Validated PurchaseApiRequest purchaseApiRequest)
			throws DataNotFoundException, DataAlreadyExistException {

		PurchaseServiceInput purchaseServiceInput = purchaseMapper.mapApiRequestToServiceInput(purchaseApiRequest);

		PurchaseServiceOutput purchaseServiceOutput = purchaseService.createPurchase(purchaseServiceInput);

		PurchaseApiResponse purchaseApiResponse = purchaseMapper.mapServiceOutputToApiResponse(purchaseServiceOutput);

		return new Response<>(purchaseApiResponse);
	}

	@PutMapping(path = "/{id}")
	public Response<PurchaseApiResponse> updatePurchase(@PathVariable("id") Integer id,
			@RequestBody @Validated PurchaseApiRequest purchaseApiRequest)
			throws DataNotFoundException, DataAlreadyExistException {

		PurchaseServiceInput purchaseServiceInput = purchaseMapper.mapApiRequestToServiceInput(purchaseApiRequest);
		PurchaseServiceOutput purchaseServiceOutput = purchaseService.updatePurchase(id, purchaseServiceInput);

		PurchaseApiResponse purchaseApiResponse = purchaseMapper.mapServiceOutputToApiResponse(purchaseServiceOutput);

		return new Response<>(purchaseApiResponse);
	}

	@DeleteMapping(path = "/{id}")
	public Response<PurchaseApiResponse> deletePurchase(@PathVariable("id") Integer id)
			throws DataNotFoundException, DataAlreadyExistException {

		PurchaseServiceOutput purchaseServiceOutput = purchaseService.deletePurchase(id);

		PurchaseApiResponse purchaseApiResponse = purchaseMapper.mapServiceOutputToApiResponse(purchaseServiceOutput);

		return new Response<>(purchaseApiResponse);
	}

}
