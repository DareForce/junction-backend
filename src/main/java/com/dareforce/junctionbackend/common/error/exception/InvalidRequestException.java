package com.dareforce.junctionbackend.common.error.exception;


import com.dareforce.junctionbackend.common.ErrorCode;
import lombok.Getter;

@Getter
public class InvalidRequestException extends RuntimeException{

  private final ErrorCode errorCode;

  public InvalidRequestException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }
}
