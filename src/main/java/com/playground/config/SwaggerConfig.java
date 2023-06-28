package com.playground.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
  @Bean
  public GroupedOpenApi publicApi() {
    return GroupedOpenApi.builder().group("playground").pathsToMatch("/playground/**").build();
  }

  @Bean
  public OpenAPI openAPI() {

    Info info = new Info().version("v1.0.0").title("Playground API").description("playground 프로젝트 API 명세서");

    // SecurityScheme 명
    String jwtSchemeName = "jwtAuth";

    // API 요청헤더에 인증정보 포함
    SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);

    // SecuritySchemes 등록
    Components components = new Components().addSecuritySchemes(jwtSchemeName, new SecurityScheme().name(jwtSchemeName).type(SecurityScheme.Type.HTTP) // HTTP 방식
        .scheme("bearer").bearerFormat("JWT")); // 토큰 형식을 지정하는 임의의 문자(Optional)

    return new OpenAPI().info(info).addSecurityItem(securityRequirement).components(components);
  }

  // 전역 설정 필요 시 사용
  @Bean
  public OperationCustomizer operationCustomizer() {
    return (Operation operation, HandlerMethod handlerMethod) -> {
      Parameter param = new Parameter().in(ParameterIn.HEADER.toString()) // 전역 헤더 설정
          .schema(new StringSchema()._default("1234567").name("AppID")) // default 값 설정
          .name("AppID").description("TEST AppID").required(true);

      operation.addParametersItem(param);

      return operation;
    };
  }
}
