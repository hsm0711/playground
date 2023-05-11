package com.member.api.member.model;

import com.member.model.BaseResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(name = "LoginResponse", description= "로그인 응답 데이터")
public class LoginResponse extends BaseResponse {

	@Schema(description = "토큰", defaultValue = "", example = "JWT 토큰")
	private String token; //JWT 토큰

	@Builder
	public LoginResponse(String token) {
		super();
		this.token = token;
	}
}
