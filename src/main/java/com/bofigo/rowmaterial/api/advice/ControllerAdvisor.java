package com.bofigo.rowmaterial.api.advice;

import java.util.Date;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bofigo.rowmaterial.api.response.Response;
import com.bofigo.rowmaterial.api.response.ResponseStatus;
import com.bofigo.rowmaterial.exception.OperationNotValidException;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

	@ExceptionHandler(OperationNotValidException.class)
	public Response<String> handleCityNotFoundException(OperationNotValidException ex, WebRequest request) {

		Response<String> response = new Response<String>("İşlem gerçekleştirilemedi");
		response.setStatus(ResponseStatus.FAILURE);
		response.setMessage("İşlem gerçekleştirilemedi");
		response.setTimestamp(new Date());

		return response;
	}

}