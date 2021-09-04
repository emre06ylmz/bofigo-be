package com.bofigo.rowmaterial.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.bofigo.rowmaterial.BofigoBeApplication;

@Component
public class CookieFilter implements Filter {

	private static Logger logger = LoggerFactory.getLogger(LogRequestFilter.class);

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

		httpResponse.addHeader("Access-Control-Request-Method", "GET, POST, DELETE, PUT");
		httpResponse.addHeader("Access-Control-Request-Origin", BofigoBeApplication.FE_DOMAIN);
		httpResponse.addHeader("Access-Control-Allow-Credentials", "true");

		filterChain.doFilter(servletRequest, servletResponse);

	}

}