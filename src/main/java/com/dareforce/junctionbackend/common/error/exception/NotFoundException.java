package com.dareforce.junctionbackend.common.error.exception;

import com.dareforce.junctionbackend.common.ErrorCode;
import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

  private final ErrorCode errorCode;

  public NotFoundException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }
}