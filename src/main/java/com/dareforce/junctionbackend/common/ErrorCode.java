package com.dareforce.junctionbackend.common;

import lombok.Getter;

@Getter
public enum ErrorCode {
  // Server Error
  INTERNAL_SERVER_ERROR(500, "S000", "서버에 문제가 생겼습니다."),

  // Client Error
  METHOD_NOT_ALLOWED(405, "C000", "적절하지 않은 HTTP 메소드입니다."),
  INVALID_TYPE_VALUE(400, "C001", "요청 값의 타입이 잘못되었습니다."),
  INVALID_INPUT_VALUE(400, "C002", "적절하지 않은 값입니다."),
  NOT_FOUND(404, "C003", "해당 리소스를 찾을 수 없습니다."),
  BAD_REQUEST(400, "C004", "잘못된 요청입니다."),
  MISSING_REQUEST_PARAMETER(400, "C005", "필수 파라미터가 누락되었습니다."),
  INVALID_LENGTH(400, "C006", "올바르지 않은 길이입니다."),
  INVALID_FILE_EXTENSION(400, "C007", "올바르지 않은 파일 확장자입니다. (png, jpg, jpeg 가능)"),
  MAX_UPLOAD_SIZE_EXCEEDED(400, "C008", "최대 파일 크기(5MB)보다 큰 파일입니다."),
  RESOURCE_PERMISSION_DENIED(400, "C009", "해당 리소스에 대한 작업 권한이 없습니다."),
  ACCESS_DENIED(403, "C010", "요청 권한이 없습니다."),
  UNAUTHENTICATED_USER(401, "C011", "인증되지 않은 사용자입니다."),

  /**
   * User Domain
   */
  USER_NOT_FOUND(400, "U001", "유저가 존재하지 않습니다."),


  /*
  S3
   */
  S3_UPLOAD_FAILED(400, "S001", "S3 업로드 실패"),
  INVALID_URL(400, "S002", "올바르지 않은 url이 반환되었습니다."),
  INPUTSTREAM_CLOSE_FAILED(400, "S003", "업로드 후 인풋스트림 해제 실패");

  private final int status;
  private final String code;
  private final String message;

  ErrorCode(int status, String code, String message) {
    this.status = status;
    this.code = code;
    this.message = message;
  }

}
