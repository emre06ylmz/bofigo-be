package com.bofigo.rowmaterial.securiy.handler;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.bofigo.rowmaterial.BofigoBeApplication;
import com.bofigo.rowmaterial.securiy.model.JWTAuthenticationToken;
import com.bofigo.rowmaterial.securiy.util.JwtUtil;

@Component
public class AuthenticationSucessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private static Logger logger = LoggerFactory.getLogger(AuthenticationSucessHandler.class);

	private JwtUtil jwtUtil;

	public AuthenticationSucessHandler(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		if (!(authentication instanceof JWTAuthenticationToken)) {
			return;
		}
		JWTAuthenticationToken jwtAuthenticationToken = (JWTAuthenticationToken) authentication;
		Cookie cookie = new Cookie(JwtUtil.JWT_TOKEN, jwtAuthenticationToken.getToken());
		cookie.setPath("/");
		cookie.setHttpOnly(true);
		response.addCookie(cookie);
		cookie.setDomain(BofigoBeApplication.FE_DOMAIN);
		response.setCharacterEncoding("UTF-8");
		logger.info("cookie is setted as: " + cookie.getValue());

		response.setHeader("SameSite", "None");
		
		//response.addHeader("Access-Control-Request-Method", "GET, POST, DELETE, PUT");
		//response.addHeader("Access-Control-Request-Origin", BofigoBeApplication.FE_ORIGIN);
		//response.addHeader("Access-Control-Allow-Credentials", "true");
		
		response.getWriter().print(new ObjectMapper().writeValueAsString(jwtAuthenticationToken.getPrincipal()));
	}

}
