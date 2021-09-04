package com.bofigo.rowmaterial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class BofigoBeApplication {

	public static String FE_DOMAIN = "https://bofigo-fe.herokuapp.com/";
	
	public static void main(String[] args) {
		SpringApplication.run(BofigoBeApplication.class, args);
	}


}
