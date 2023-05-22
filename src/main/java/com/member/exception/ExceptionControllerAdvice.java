package com.member.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.member.model.BaseResponse;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(UnsupportedJwtException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public BaseResponse<Void> unsupportedJwtException(Exception e) {
		return new BaseResponse<>("UnsupportedJwtException");
	}

	@ExceptionHandler(MalformedJwtException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public BaseResponse<Void> malformedJwtException(Exception e) {
		return new BaseResponse<>("MalformedJwtException");
	}

	@ExceptionHandler(ExpiredJwtException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public BaseResponse<Void> expiredJwtException(Exception e) {
		return new BaseResponse<>("ExpiredJwtException");
	}

	@ExceptionHandler(SignatureException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public BaseResponse<Void> signatureException(Exception e) {
		return new BaseResponse<>("SignatureException");
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	public BaseResponse<Void> httpRequestMethodNotSupportedException(Exception e) {
		return new BaseResponse<>("HttpRequestMethodNotSupportedException");
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public BaseResponse<Void> noHandlerFoundException(Exception e) {
		return new BaseResponse<>("NoHandlerFoundException");
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<Void> methodArgumentValidException(MethodArgumentNotValidException e) {
	    ObjectError objectError = e.getBindingResult().getAllErrors().stream().findFirst().orElse(new ObjectError("", "파라메터가 올바르지 않습니다."));

		return new BaseResponse<>(objectError.getDefaultMessage());
    }
}
