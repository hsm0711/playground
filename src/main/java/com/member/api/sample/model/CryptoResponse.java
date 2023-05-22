package com.member.api.sample.model;

import com.member.model.BaseResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "CryptoResponse", description= "암복호화 응답 데이터")
@Builder
@AllArgsConstructor
@Getter
@Setter
public class CryptoResponse extends BaseResponse {

	@Schema(description = "암복호화 요청 문자열", defaultValue = "", example = "qwer1234/5uKD75cYuiNPZReQp36J..")
	private String inputStr;

	@Schema(description = "암복호화 결과 문자열", defaultValue = "", example = "hong12/5uKD75cYuiNPZReQp36J..")
	private String resultStr;
}
