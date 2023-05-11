package com.member.model;

import com.member.utils.MessageUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse extends BaseDto {
	private String result;
	private String reason;

	public BaseResponse() {
		this.result = MessageUtils.SUCCESS;
		this.reason = "";
	}

	public BaseResponse(String result) {
		this.reason = MessageUtils.FAIL;
		this.result = result;
	}

}