package com.bofigo.rowmaterial.securiy.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.bofigo.rowmaterial.api.response.Response;
import com.bofigo.rowmaterial.api.response.ResponseStatus;

@Component
public class UnAuthorizedEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.setContentType("applications/json");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(new ObjectMapper().writeValueAsString(getFailureResponse()));
	}

	private Response<String> getFailureResponse() {
		Response<String> response = new Response<>();
		response.setStatus(ResponseStatus.FAILURE);
		response.setMessage("login olduktan sonra tekrar deneyiniz");
		return response;
	}

}
