package com.hackaton.search.configuration;

import com.google.common.collect.ImmutableList;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Value("${swaggerBaseUrl}")
	private String baseUrl;

	@Bean
	public Docket productApi() {

		// responses
		final ResponseMessage unauthorizedResponse = new ResponseMessageBuilder().code(HttpStatus.UNAUTHORIZED.value())
				.message("Unauthorized access").build();

		final ResponseMessage forbiddenResponse = new ResponseMessageBuilder().code(HttpStatus.FORBIDDEN.value()).message("Access denied")
				.build();

		return new Docket(DocumentationType.SWAGGER_2).apiInfo(metaData()).useDefaultResponseMessages(false).host(baseUrl).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(Api.class)).build()
				.globalResponseMessage(RequestMethod.POST, ImmutableList.of(unauthorizedResponse, forbiddenResponse));
	}

	private ApiInfo metaData() {

		final String projectVersion = "1.0.0.0";
		return new ApiInfoBuilder().title("Search Engine")
				.description("\"Spring Boot REST API for the Fatest Search Engine\"").version(projectVersion)
				.license("Hackathon © 2019").build();
	}

	@Bean
	public UiConfiguration uiConfig() {

		return UiConfigurationBuilder.builder().displayRequestDuration(true).validatorUrl(Strings.EMPTY).build();
	}
}
