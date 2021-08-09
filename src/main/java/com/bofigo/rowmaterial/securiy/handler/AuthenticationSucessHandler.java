package com.bofigo.rowmaterial.securiy.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.bofigo.rowmaterial.securiy.model.JWTAuthenticationToken;
import com.bofigo.rowmaterial.securiy.util.JwtUtil;

@Component
public class AuthenticationSucessHandler extends SimpleUrlAuthenticationSuccessHandler {

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
		Cookie cookie = new Cookie(jwtUtil.JWT_TOKEN, jwtAuthenticationToken.getToken());
		cookie.setPath("/");
		response.addCookie(cookie);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(new ObjectMapper().writeValueAsString(jwtAuthenticationToken.getPrincipal()));
	}

}
