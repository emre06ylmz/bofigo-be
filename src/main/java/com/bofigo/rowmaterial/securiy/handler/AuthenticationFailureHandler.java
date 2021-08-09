package com.bofigo.rowmaterial.securiy.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.bofigo.rowmaterial.api.response.Response;
import com.bofigo.rowmaterial.api.response.ResponseStatus;

@Component
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(new ObjectMapper().writeValueAsString(getFailureResponse(exception.getMessage())));
	}

	private Response<String> getFailureResponse(String message) {
		Response<String> response = new Response<>();
		response.setStatus(ResponseStatus.FAILURE);
		response.setMessage(message);
		return response;
	}

}
