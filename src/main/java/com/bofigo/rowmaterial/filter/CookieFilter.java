package com.bofigo.rowmaterial.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class CookieFilter extends OncePerRequestFilter {

	@Override
	public void destroy() {

	}

	private String getAllowedDomainsRegex() {
		return "individual / customized Regex";
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String origin = "http://localhost:3000";

		//response.addHeader("Access-Control-Allow-Origin", origin);
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Headers",
				"content-type, x-gwt-module-base, x-gwt-permutation, clientid, longpush");

		filterChain.doFilter(request, response);

	}
}