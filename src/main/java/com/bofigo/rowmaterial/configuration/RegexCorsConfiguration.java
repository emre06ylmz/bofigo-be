package com.bofigo.rowmaterial.configuration;

import org.springframework.web.cors.CorsConfiguration;

public class RegexCorsConfiguration extends CorsConfiguration {

	public String checkOrigin(String requestOrigin) {
		return requestOrigin;
	}

}
