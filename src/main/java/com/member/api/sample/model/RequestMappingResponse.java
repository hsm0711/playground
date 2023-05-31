package com.member.api.sample.model;

import java.util.List;
import java.util.Set;
import org.springframework.web.bind.annotation.RequestMethod;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.member.model.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(name = "RequestMappingInfoResponse", description = "RequestMapping 정보 응답 데이터")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestMappingResponse extends BaseDto {

  @Schema(description = "request url과 동일한 메소드에 mapping된 url 목록", example = "[\"/member/public/sample/request-mapping\"]")
  private Set<String> paths;

  @Schema(description = "http method 목록", example = "[\"GET\", \"POST\"]")
  private Set<RequestMethod> httpMethods;

  @Schema(description = "Class명 상세", example = "com.member.api.sample.RequestMappingController")
  private String className;

  @Schema(description = "Class명 단순", example = "RequestMappingController")
  private String classNameSimple;

  @Schema(description = "Method명", example = "getRequestMappings")
  private String methodName;

  @Schema(description = "Method 설명", example = "requestMapping 정보 조회")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String methodDescription;

  @Schema(description = "Method 설명 상세",
      example = "RequestMappingHandlerMapping를 활용해서 request의 url, http method, class명, method, parameter, return type등 정보를 조회")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String methodDescriptionDetail;

  @Schema(description = "parameter 상세", example = "[\"java.lang.String id\", \"java.lang.String pwd\"]")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private List<String> params;

  @Schema(description = "parameter 단순", example = "[\"String id\", \"String pwd\"]")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private List<String> paramsSimple;

  @Schema(description = "return 상세",
      example = "org.springframework.http.ResponseEntity<com.member.model.BaseResponse<java.util.Map<java.lang.String, com.member.api.sample.model.RequestMappingInfoResponse>>>")
  private String returnType;

  @Schema(description = "return 단순", example = "ResponseEntity<BaseResponse<Map<String, RequestMappingInfoResponse>>>")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String returnTypeSimple;
}
