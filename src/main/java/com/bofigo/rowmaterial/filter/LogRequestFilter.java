package com.bofigo.rowmaterial.filter;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Arrays;

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

@Component
public class LogRequestFilter implements Filter {

	private static Logger logger = LoggerFactory.getLogger(LogRequestFilter.class);

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
		
		if (Arrays.asList("POST", "PUT").contains(httpRequest.getMethod())) {
			CustomHttpRequestWrapper requestWrapper = new CustomHttpRequestWrapper(httpRequest);
			logPostOrPutRequestBody((HttpServletRequest) servletRequest);
			filterChain.doFilter(requestWrapper, servletResponse);
			return;
		}
		logGetOrDeleteRequest((HttpServletRequest) servletRequest);
		filterChain.doFilter(servletRequest, servletResponse);
	}

	private void logPostOrPutRequestBody(HttpServletRequest httpRequest) throws IOException {
		if (Arrays.asList("POST", "PUT").contains(httpRequest.getMethod())) {
			String characterEncoding = httpRequest.getCharacterEncoding();
			Charset charset = Charset.forName(characterEncoding);
			String bodyInStringFormat = readInputStreamInStringFormat(httpRequest.getInputStream(), charset);
			String requestUri = httpRequest.getRequestURI();
			logger.info("({}) {}, body: {}", httpRequest.getMethod(), requestUri, bodyInStringFormat);
		}
	}

	private void logGetOrDeleteRequest(HttpServletRequest httpRequest) throws IOException {
		if (Arrays.asList("GET", "DELETE").contains(httpRequest.getMethod())) {
			String requestUri = httpRequest.getRequestURI();
			logger.info("({}) {}", httpRequest.getMethod(), requestUri);
		}
	}

	private String readInputStreamInStringFormat(InputStream stream, Charset charset) throws IOException {
		final int MAX_BODY_SIZE = 1024;
		final StringBuilder bodyStringBuilder = new StringBuilder();
		if (!stream.markSupported()) {
			stream = new BufferedInputStream(stream);
		}

		stream.mark(MAX_BODY_SIZE + 1);
		final byte[] entity = new byte[MAX_BODY_SIZE + 1];
		final int bytesRead = stream.read(entity);

		if (bytesRead != -1) {
			bodyStringBuilder.append(new String(entity, 0, Math.min(bytesRead, MAX_BODY_SIZE), charset));
			if (bytesRead > MAX_BODY_SIZE) {
				bodyStringBuilder.append("...");
			}
		}
		stream.reset();

		return bodyStringBuilder.toString();
	}

}