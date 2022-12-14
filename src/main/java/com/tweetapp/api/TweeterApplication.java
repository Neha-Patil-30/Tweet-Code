package com.tweetapp.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableMongoAuditing
@Configuration
@EnableSwagger2
public class TweeterApplication {
	public static void main(String[] args) {
		SpringApplication.run(TweeterApplication.class, args);
	}

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.tweetapp.api.controller")).build().apiInfo(apiInfo());

	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Tweet Application Backend").version("1.0.0").description("Build By : Neha")
				.build();
	}
}
