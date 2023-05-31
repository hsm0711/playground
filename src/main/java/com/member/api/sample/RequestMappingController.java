package com.member.api.sample;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.HeadersRequestCondition;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import com.member.model.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "RequestMapping 샘플 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/member/public/sample/request-mapping")
public class RequestMappingController {
  private final RequestMappingHandlerMapping requestMappingHandlerMapping;

  /**
   * requestMapping 정보 조회
   */
  @ApiOperation(value = "requestMapping 정보 조회", notes = "requestMapping 정보 조회")
  @GetMapping
  public ResponseEntity<BaseResponse<List<Map<String, Object>>>> getRequestMappings() {
    ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
    List<Map<String, Object>> resultList = new ArrayList<>();
    Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();

    for (Map.Entry<RequestMappingInfo, HandlerMethod> enty : map.entrySet()) {
      RequestMappingInfo key = enty.getKey();
      HandlerMethod handlerMethod = enty.getValue();
      Method method = handlerMethod.getMethod();

      Map<String, Object> resultMap = new HashMap<>();

      if (key.getName() != null) {
        resultMap.put("name", key.getName());
      }

      PatternsRequestCondition patternsRequestCondition = key.getPatternsCondition();

      if (patternsRequestCondition != null) {
        resultMap.put("path", patternsRequestCondition.getPatterns());
      }

      RequestMethodsRequestCondition requestMethodsRequestCondition = key.getMethodsCondition();

      resultMap.put("http-methods", requestMethodsRequestCondition.getMethods());

      HeadersRequestCondition headersRequestCondition = key.getHeadersCondition();

      if (!headersRequestCondition.isEmpty()) {
        resultMap.put("headers", key.getHeadersCondition());
      }

      resultMap.put("class", handlerMethod.getBeanType().getName());
      resultMap.put("method", method.getName());

      Class<?>[] parameterTypes = method.getParameterTypes();
      String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);

      if (parameterNames != null && parameterNames.length > 0) {
        String[] params = new String[parameterNames.length];

        for (int i = 0; i < parameterNames.length; i++) {
          String parameter = parameterNames[i];
          String parameterType = parameterTypes[i].getName();

          params[i] = parameter + " " + parameterType;
        }

        resultMap.put("params", params);
      }

      Type returnType = method.getGenericReturnType();

      // resultMap.put("return", method.getReturnType()); // ResponseEntity가 아닌 단순 형태 일때

      if (returnType instanceof ParameterizedType) {
        ParameterizedType parameterizedType = (ParameterizedType) returnType;
        Type[] typeArguments = parameterizedType.getActualTypeArguments();

        StringBuilder genericTypeBuilder = new StringBuilder(parameterizedType.getRawType().getTypeName()).append("<");

        for (int i = 0; i < typeArguments.length; i++) {
          genericTypeBuilder.append(typeArguments[i].getTypeName());

          if (i < typeArguments.length - 1) {
            genericTypeBuilder.append(", ");
          }
        }

        genericTypeBuilder.append(">");

        resultMap.put("return", genericTypeBuilder.toString());
      } else {
        resultMap.put("return", returnType.getTypeName());
      }

      resultList.add(resultMap);
    }

    return ResponseEntity.ok(new BaseResponse<>(resultList));
  }
}
