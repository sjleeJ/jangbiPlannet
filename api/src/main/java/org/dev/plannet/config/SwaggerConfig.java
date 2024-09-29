package org.dev.plannet.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

	private static final String SECURITY_SCHEME_NAME = "authorization";

	// 특정 API 그룹 설정
	@Bean
	public GroupedOpenApi userApi() {
		return GroupedOpenApi.builder()
			.group("user")
			.pathsToMatch("/api/**")
			.build();
	}

	@Bean
	public GroupedOpenApi memberApi() {
		return GroupedOpenApi.builder()
			.group("member")
			.pathsToMatch("/member-api/**")
			.build();
	}

	@Bean
	public GroupedOpenApi adminApi() {
		return GroupedOpenApi.builder()
			.group("admin")
			.pathsToMatch("/api-admin/**")
			.build();
	}

	// Swagger UI를 위한 기본 OpenAPI 설정
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
			.components(new Components()
				.addSecuritySchemes(SECURITY_SCHEME_NAME,
					new SecurityScheme()
						.name(SECURITY_SCHEME_NAME)
						.type(SecurityScheme.Type.HTTP)
						.scheme("bearer")
						.bearerFormat("JWT")
				))
			.addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
			.info(new Info()
				.title("My Application API")
				.description("My Application API Documentation")
				.version("1.0.0"));
	}
}
