package com.playground.exception;

public class CustomException extends RuntimeException {

  private static final long serialVersionUID = -968869927329692091L;

  public CustomException(String msg) {
    super(msg);
  }
}
