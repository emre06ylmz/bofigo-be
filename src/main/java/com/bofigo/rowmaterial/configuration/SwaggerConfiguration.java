package com.bofigo.rowmaterial.configuration;

import java.util.Collections;

import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(metaData()).select()
				.apis(RequestHandlerSelectors.basePackage("com.bofigo.rowmaterial")).paths(PathSelectors.any()).build();
	}

	public ApiInfo metaData() {
		return new ApiInfo("bofigo raw material track system", "description", "1.0.0", "Terms of serviceUrl",
				new Contact("bofigo", "bofigo url", "bofigo email"), "licence", "licenceUrl", Collections.emptyList());

	}

}
