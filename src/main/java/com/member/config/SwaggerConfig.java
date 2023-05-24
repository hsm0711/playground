package com.member.config;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.common.net.HttpHeaders;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.OAS_30).useDefaultResponseMessages(true) // Swagger 에서 제공해주는 기본 응답 코드
        .apiInfo(apiInfo()) // Swagger UI 로 노출할 정보
        .securityContexts(List.of(securityContext())) // SecurityContext 설정
        .securitySchemes(List.of(apiKey())) // ApiKey 설정
        .select().apis(RequestHandlerSelectors.basePackage("com.member")) // api 스펙이 작성되어 있는 패키지
        .paths(PathSelectors.any()) // apis 에 위치하는 API 중 특정 path 를 선택
        .build();

  }

  public ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("회원프로젝트").description("회원프로젝트 Swagger").version("0.1").build();
  }

  // JWT SecurityContext 구성
  private SecurityContext securityContext() {
    return SecurityContext.builder().securityReferences(defaultAuth()).build();
  }

  private List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];

    authorizationScopes[0] = authorizationScope;

    return List.of(new SecurityReference(HttpHeaders.AUTHORIZATION, authorizationScopes));
  }

  // ApiKey 정의
  private ApiKey apiKey() {
    return new ApiKey(HttpHeaders.AUTHORIZATION, HttpHeaders.AUTHORIZATION, "header");
  }

}
