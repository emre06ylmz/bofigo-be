package com.bofigo.rowmaterial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableAutoConfiguration
public class BofigoBeApplication {

	public static String FE_ORIGIN = "http://213.238.181.234:80";
	public static String FE_DOMAIN = "213.238.181.234";
	public static int TIMEOUT = 60 * 60 * 2;
	public static boolean IS_SECURE = true;

	public static void main(String[] args) {
		SpringApplication.run(BofigoBeApplication.class, args);

	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins(FE_ORIGIN);
			}
		};
	}

}
