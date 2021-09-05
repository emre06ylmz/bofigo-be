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

	public static String FE_ORIGIN = "https://bofigo-fe.herokuapp.com:443";
	public static String FE_DOMAIN = "bofigo-be.herokuapp.com";

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
