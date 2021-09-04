package com.bofigo.rowmaterial.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.cors.CorsConfiguration;

public class RegexCorsConfiguration extends CorsConfiguration {
	private static Logger logger = LoggerFactory.getLogger(RegexCorsConfiguration.class);

	public String checkOrigin(String requestOrigin) {
		logger.info("checkOrigin: " + requestOrigin);
		return requestOrigin;
	}

}
