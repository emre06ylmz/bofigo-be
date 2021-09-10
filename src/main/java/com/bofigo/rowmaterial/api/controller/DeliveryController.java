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

import com.bofigo.rowmaterial.api.request.DeliveryApiRequest;
import com.bofigo.rowmaterial.api.response.DeliveryApiResponse;
import com.bofigo.rowmaterial.api.response.Response;
import com.bofigo.rowmaterial.domain.dto.input.DeliveryServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.DeliveryServiceOutput;
import com.bofigo.rowmaterial.domain.service.delivery.DeliveryService;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;
import com.bofigo.rowmaterial.mapper.DeliveryMapper;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api("Delivery")
@RequestMapping("/api/delivery")
public class DeliveryController {

	private static Logger logger = LoggerFactory.getLogger(DeliveryController.class);

	private DeliveryService deliveryService;
	private DeliveryMapper deliveryMapper;

	public DeliveryController(DeliveryService deliveryService, DeliveryMapper deliveryMapper) {
		this.deliveryService = deliveryService;
		this.deliveryMapper = deliveryMapper;
	}

	@GetMapping(path = "")
	public Response<List<DeliveryApiResponse>> listDelivery() throws DataNotFoundException {
		List<DeliveryServiceOutput> deliveryServiceOutputList = deliveryService.listAll();
		List<DeliveryApiResponse> deliveryApiResponseList = deliveryMapper
				.mapServiceOutputToApiResponseList(deliveryServiceOutputList);
		return new Response<>(deliveryApiResponseList);
	}

	@GetMapping(path = "/listByProductId/{id}")
	public Response<List<DeliveryApiResponse>> listDeliveryByRawMaterialCategoryId(
			@PathVariable("id") Integer productId) throws DataNotFoundException {
		List<DeliveryServiceOutput> deliveryServiceOutputList = deliveryService.listByProductId(productId);
		List<DeliveryApiResponse> deliveryApiResponseList = deliveryMapper
				.mapServiceOutputToApiResponseList(deliveryServiceOutputList);
		return new Response<>(deliveryApiResponseList);
	}

	@GetMapping(path = "/{id}")
	public Response<DeliveryApiResponse> getDelivery(@PathVariable("id") Integer id) throws DataNotFoundException {
		DeliveryServiceOutput deliveryServiceOutput = deliveryService.getDeliveryById(id);
		DeliveryApiResponse deliveryApiResponse = deliveryMapper
				.mapServiceOutputToApiResponse(deliveryServiceOutput);
		return new Response<>(deliveryApiResponse);
	}

	@PostMapping(path = "")
	public Response<DeliveryApiResponse> createDelivery(
			@RequestBody @Validated DeliveryApiRequest deliveryApiRequest)
			throws DataNotFoundException, DataAlreadyExistException {

		DeliveryServiceInput deliveryServiceInput = deliveryMapper
				.mapApiRequestToServiceInput(deliveryApiRequest);

		DeliveryServiceOutput deliveryServiceOutput = deliveryService.createDelivery(deliveryServiceInput);

		DeliveryApiResponse deliveryApiResponse = deliveryMapper
				.mapServiceOutputToApiResponse(deliveryServiceOutput);

		return new Response<>(deliveryApiResponse);
	}

	@PutMapping(path = "/{id}")
	public Response<DeliveryApiResponse> updateDelivery(@PathVariable("id") Integer id,
			@RequestBody @Validated DeliveryApiRequest deliveryApiRequest)
			throws DataNotFoundException, DataAlreadyExistException {

		DeliveryServiceInput deliveryServiceInput = deliveryMapper
				.mapApiRequestToServiceInput(deliveryApiRequest);
		DeliveryServiceOutput deliveryServiceOutput = deliveryService.updateDelivery(id,
				deliveryServiceInput);

		DeliveryApiResponse deliveryApiResponse = deliveryMapper
				.mapServiceOutputToApiResponse(deliveryServiceOutput);

		return new Response<>(deliveryApiResponse);
	}

	@DeleteMapping(path = "/{id}")
	public Response<DeliveryApiResponse> deleteDelivery(@PathVariable("id") Integer id)
			throws DataNotFoundException, DataAlreadyExistException {

		DeliveryServiceOutput deliveryServiceOutput = deliveryService.deleteDelivery(id);

		DeliveryApiResponse deliveryApiResponse = deliveryMapper
				.mapServiceOutputToApiResponse(deliveryServiceOutput);

		return new Response<>(deliveryApiResponse);
	}

}
