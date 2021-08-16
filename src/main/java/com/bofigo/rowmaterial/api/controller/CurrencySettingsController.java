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

import com.bofigo.rowmaterial.api.request.CurrencySettingsApiRequest;
import com.bofigo.rowmaterial.api.response.CurrencySettingsApiResponse;
import com.bofigo.rowmaterial.api.response.Response;
import com.bofigo.rowmaterial.domain.dto.CurrencySettingsServiceInput;
import com.bofigo.rowmaterial.domain.dto.CurrencySettingsServiceOutput;
import com.bofigo.rowmaterial.domain.service.CurrencySettingsService;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;
import com.bofigo.rowmaterial.mapper.CurrencySettingsMapper;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api("CurrencySettings")
@RequestMapping("/api/settings/currency")
public class CurrencySettingsController {

	private static Logger logger = LoggerFactory.getLogger(CurrencySettingsController.class);

	private CurrencySettingsService currenySettingsService;
	private CurrencySettingsMapper currenySettingsMapper;

	public CurrencySettingsController(CurrencySettingsService currenySettingsService,
			CurrencySettingsMapper currenySettingsMapper) {
		this.currenySettingsService = currenySettingsService;
		this.currenySettingsMapper = currenySettingsMapper;
	}

	@GetMapping(path = "")
	public Response<List<CurrencySettingsApiResponse>> listCurrencySettings() throws DataNotFoundException {
		List<CurrencySettingsServiceOutput> currenySettingsServiceOutputList = currenySettingsService.listAll();
		List<CurrencySettingsApiResponse> currenySettingsApiResponseList = currenySettingsMapper
				.mapServiceOutputToApiResponseList(currenySettingsServiceOutputList);
		return new Response<>(currenySettingsApiResponseList);
	}

	@GetMapping(path = "/{id}")
	public Response<CurrencySettingsApiResponse> getCurrencySettings(@PathVariable("id") Integer id)
			throws DataNotFoundException {
		CurrencySettingsServiceOutput currenySettingsServiceOutput = currenySettingsService.getCurrencySettingsById(id);
		CurrencySettingsApiResponse currenySettingsApiResponse = currenySettingsMapper
				.mapServiceOutputToApiResponse(currenySettingsServiceOutput);
		return new Response<>(currenySettingsApiResponse);
	}

	@PostMapping(path = "")
	public Response<CurrencySettingsApiResponse> createCurrencySettings(
			@RequestBody @Validated CurrencySettingsApiRequest currenySettingsApiRequest)
			throws DataNotFoundException, DataAlreadyExistException {

		CurrencySettingsServiceInput currenySettingsServiceInput = currenySettingsMapper
				.mapApiRequestToServiceInput(currenySettingsApiRequest);
		CurrencySettingsServiceOutput currenySettingsServiceOutput = currenySettingsService
				.createCurrencySettings(currenySettingsServiceInput);

		CurrencySettingsApiResponse currenySettingsApiResponse = currenySettingsMapper
				.mapServiceOutputToApiResponse(currenySettingsServiceOutput);

		return new Response<>(currenySettingsApiResponse);
	}

	@PutMapping(path = "")
	public Response<CurrencySettingsApiResponse> updateCurrencySettings(
			@RequestBody @Validated CurrencySettingsApiRequest currenySettingsApiRequest)
			throws DataNotFoundException, DataAlreadyExistException {
		CurrencySettingsApiResponse currenySettingsApiResponse = new CurrencySettingsApiResponse();
		CurrencySettingsServiceInput currenySettingsServiceInput = currenySettingsMapper
				.mapApiRequestToServiceInput(currenySettingsApiRequest);

		List<CurrencySettingsServiceOutput> list = currenySettingsService.listAll();
		if (list.size() > 0) {
			CurrencySettingsServiceOutput currenySettingsServiceOutput = currenySettingsService
					.updateCurrencySettings(list.get(0).getId(), currenySettingsServiceInput);

			currenySettingsApiResponse = currenySettingsMapper
					.mapServiceOutputToApiResponse(currenySettingsServiceOutput);
		}

		return new Response<>(currenySettingsApiResponse);
	}

	@DeleteMapping(path = "/{id}")
	public Response<CurrencySettingsApiResponse> deleteCurrencySettings(@PathVariable("id") Integer id)
			throws DataNotFoundException, DataAlreadyExistException {

		CurrencySettingsServiceOutput currenySettingsServiceOutput = currenySettingsService.deleteCurrencySettings(id);

		CurrencySettingsApiResponse currenySettingsApiResponse = currenySettingsMapper
				.mapServiceOutputToApiResponse(currenySettingsServiceOutput);

		return new Response<>(currenySettingsApiResponse);
	}

}