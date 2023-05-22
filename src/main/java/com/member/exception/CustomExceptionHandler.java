package com.member.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.member.model.BaseResponse;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(CustomException.class)
	protected ResponseEntity<BaseResponse<Void>> customException(CustomException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponse<>(e.getMessage()));
	}
}
