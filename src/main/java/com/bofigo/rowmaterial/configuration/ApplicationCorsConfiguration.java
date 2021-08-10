package com.bofigo.rowmaterial.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class ApplicationCorsConfiguration {

	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		RegexCorsConfiguration regexCorsConfiguration = new RegexCorsConfiguration();
		regexCorsConfiguration.setAllowedHeaders(java.util.Collections.singletonList("*"));
		regexCorsConfiguration.setAllowCredentials(true);
		regexCorsConfiguration.setAllowedMethods(java.util.Collections.singletonList("*"));
		source.registerCorsConfiguration("/**", regexCorsConfiguration);
		return new CorsFilter(source);
	}

}
