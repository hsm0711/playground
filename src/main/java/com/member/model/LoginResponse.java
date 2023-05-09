package com.member.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "LoginResponse", description= "로그인 응답 데이터")
@Data
public class LoginResponse extends BaseResponse {

	@Schema(description = "토큰", defaultValue = "", example = "JWT 토큰")
	private String token; //JWT 토큰
}
