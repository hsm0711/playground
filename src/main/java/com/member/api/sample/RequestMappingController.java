package com.member.api.sample;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import com.member.api.sample.model.RequestMappingResponse;
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
  @ApiOperation(value = "requestMapping 정보 조회",
      notes = "RequestMappingHandlerMapping를 활용해서 request의 url, http method, class명, method, parameter, return type등 정보를 조회")
  @GetMapping
  public ResponseEntity<BaseResponse<Map<String, RequestMappingResponse>>> getRequestMappings() {
    ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
    Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
    Map<String, RequestMappingResponse> resultMap = new HashMap<>();

    for (Map.Entry<RequestMappingInfo, HandlerMethod> enty : map.entrySet()) {
      RequestMappingInfo key = enty.getKey();
      HandlerMethod handlerMethod = enty.getValue();
      Method method = handlerMethod.getMethod();

      RequestMappingResponse requestMappingInfoResponse = new RequestMappingResponse();

      PatternsRequestCondition patternsRequestCondition = key.getPatternsCondition();

      if (patternsRequestCondition != null) {
        requestMappingInfoResponse.setPaths(patternsRequestCondition.getPatterns());
      }

      RequestMethodsRequestCondition requestMethodsRequestCondition = key.getMethodsCondition();

      requestMappingInfoResponse.setHttpMethods(requestMethodsRequestCondition.getMethods());
      requestMappingInfoResponse.setClassName(handlerMethod.getBeanType().getName());
      requestMappingInfoResponse.setClassNameSimple(handlerMethod.getBeanType().getSimpleName());
      requestMappingInfoResponse.setMethodName(method.getName());

      // 파라메터 정보 - start
      Class<?>[] parameterTypes = method.getParameterTypes();
      String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);

      if (parameterNames != null && parameterNames.length > 0) {
        List<String> params = new ArrayList<>();
        List<String> paramsSimple = new ArrayList<>();

        for (int i = 0; i < parameterNames.length; i++) {
          params.add(parameterTypes[i].getName() + " " + parameterNames[i]);
          paramsSimple.add(parameterTypes[i].getSimpleName() + " " + parameterNames[i]);
        }

        requestMappingInfoResponse.setParams(params);
        requestMappingInfoResponse.setParamsSimple(paramsSimple);
      }
      // 파라메터 정보 - end


      /*
       * // 메소드 return 정보 : ResponseEntity가 generic 정보 필요 없을 때 사용
       * requestMappingInfoResponse.setReturnType(method.getReturnType().getName());
       * requestMappingInfoResponse.setReturnType(method.getReturnType().getSimpleName());
       */

      // 메소드 return 정보 (generic 정보까지 조회) - start
      Type returnType = method.getGenericReturnType();

      if (returnType instanceof ParameterizedType) {
        ParameterizedType parameterizedType = (ParameterizedType) returnType;

        requestMappingInfoResponse.setReturnType(getName(parameterizedType, false));
        requestMappingInfoResponse.setReturnTypeSimple(getName(parameterizedType, true));
      } else {
        requestMappingInfoResponse.setReturnType(returnType.getTypeName());
      }
      // 메소드 return 정보 (generic 정보까지 조회) - end

      // @ApiOperation 정보로 메소드 상세 내역 조회 - start
      ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);

      if (apiOperation != null) {
        requestMappingInfoResponse.setMethodDescription(apiOperation.value());

        if (StringUtils.isNotBlank(apiOperation.notes())) {
          requestMappingInfoResponse.setMethodDescriptionDetail(apiOperation.notes());
        }
      }
      // @ApiOperation 정보로 메소드 상세 내역 조회 - end

      // request url이 여러개인 경우 분리를 하기 위함
      for (String path : requestMappingInfoResponse.getPaths()) {
        resultMap.put(path, requestMappingInfoResponse);
      }
    }

    return ResponseEntity.ok(new BaseResponse<>(new TreeMap<>(resultMap)));
  }

  private String getName(Type type, boolean isSimple) {
    if (type instanceof ParameterizedType) {
      ParameterizedType parameterizedType = (ParameterizedType) type;
      Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
      Type rawType = parameterizedType.getRawType();
      StringBuilder stringBuilder = new StringBuilder();

      stringBuilder.append(getName(rawType, isSimple));
      stringBuilder.append("<");

      for (int i = 0; i < actualTypeArguments.length; i++) {
        if (i > 0) {
          stringBuilder.append(", ");
        }

        if (isSimple) {
          stringBuilder.append(getName(actualTypeArguments[i], isSimple));
        } else {
          stringBuilder.append(actualTypeArguments[i].getTypeName());
        }
      }

      stringBuilder.append(">");

      return stringBuilder.toString();
    } else if (type instanceof TypeVariable) {
      TypeVariable<?> typeVariable = (TypeVariable<?>) type;

      return typeVariable.getName();
    } else if (type instanceof Class) {
      Class<?> clzz = (Class<?>) type;

      return isSimple ? clzz.getSimpleName() : clzz.getName();
    } else {
      return type.getTypeName();
    }
  }

  @GetMapping({"/test1", "/test2", "/test3"})
  public void test() {}
}
