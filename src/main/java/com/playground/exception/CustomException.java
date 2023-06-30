package com.playground.exception;

import java.io.Serial;

public class CustomException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = -968869927329692091L;

  public CustomException(String msg) {
    super(msg);
  }
}
