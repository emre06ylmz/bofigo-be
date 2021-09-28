package com.bofigo.rowmaterial.securiy.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import com.bofigo.rowmaterial.api.response.Response;
import com.bofigo.rowmaterial.api.response.ResponseStatus;
import com.bofigo.rowmaterial.securiy.authentication.JwtAuthenticationTokenFilter;
import com.bofigo.rowmaterial.securiy.util.JwtUtil;

@Component
public class AuthenticationLogoutSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler implements LogoutSuccessHandler{

	@Autowired
	private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		Cookie cookie = WebUtils.getCookie(httpServletRequest, JwtUtil.JWT_TOKEN);
		String jwtToken = cookie.getValue();
		jwtAuthenticationTokenFilter.getBlackList().add(jwtToken);
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(new ObjectMapper().writeValueAsString(getLogoutResponse()));
	}

	private Response<String> getLogoutResponse() {
		Response<String> response = new Response<>();
		response.setStatus(ResponseStatus.SUCESS);
		response.setMessage("başaılı logout");
		return response;
	}

	
	
}
