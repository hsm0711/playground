package com.member.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.member.utils.MessageUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse<T> extends BaseDto {
	private String result;

	@JsonInclude(Include.NON_NULL)
	private String errorMessage;

	private T data;

	public BaseResponse() {
		this.result = MessageUtils.SUCCESS;
	}

	public BaseResponse(T data) {
		this.result = MessageUtils.SUCCESS;
		this.data = data;
	}

	public BaseResponse(String errorMessage) {
		this.result = MessageUtils.FAIL;
		this.errorMessage = errorMessage;
	}
}