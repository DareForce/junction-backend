package com.dareforce.junctionbackend.common.error;

import com.dareforce.junctionbackend.common.ErrorCode;
import com.dareforce.junctionbackend.common.ErrorResponse;
import com.dareforce.junctionbackend.common.error.exception.AlreadyExistsException;
import com.dareforce.junctionbackend.common.error.exception.InvalidRequestException;
import com.dareforce.junctionbackend.common.error.exception.NotFoundException;
import com.dareforce.junctionbackend.common.error.exception.S3Exception;
import com.dareforce.junctionbackend.common.error.exception.ThumbnailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
  // 500 : Internal Server Error
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleServerException(Exception e) {
    return handleException(e, ErrorCode.INTERNAL_SERVER_ERROR);
  }
  // 405 : Method Not Allowed
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(
      HttpRequestMethodNotSupportedException e) {
    return handleException(e, ErrorCode.METHOD_NOT_ALLOWED);
  }

  // 400 : MethodArgumentNotValidException
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException e) {
    return handleException(e, ErrorCode.INVALID_TYPE_VALUE);
  }

  // 400 : MethodArgumentType
  @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(
      MethodArgumentTypeMismatchException e) {
    return handleException(e, ErrorCode.INVALID_TYPE_VALUE);
  }

  // 400 : Bad Request, ModelAttribute
  @ExceptionHandler(BindException.class)
  public ResponseEntity<ErrorResponse> handleBindException(BindException e) {
    return handleException(e, ErrorCode.INVALID_INPUT_VALUE);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(
      HttpMessageNotReadableException e) {
    return handleException(e, ErrorCode.INVALID_INPUT_VALUE);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
    return handleException(e, ErrorCode.INVALID_INPUT_VALUE);
  }

  @ExceptionHandler(NoHandlerFoundException.class)
  public ResponseEntity<ErrorResponse> handleNoHandlerFoundException(NoHandlerFoundException e) {
    return handleException(e, ErrorCode.NOT_FOUND);
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
    return handleException(e, ErrorCode.MISSING_REQUEST_PARAMETER);
  }

  @ExceptionHandler(MissingServletRequestPartException.class)
  public ResponseEntity<ErrorResponse> handleMissingServletRequestPartException(MissingServletRequestPartException e) {
    return handleException(e, ErrorCode.MISSING_REQUEST_PARAMETER);
  }

  @ExceptionHandler(MaxUploadSizeExceededException.class)
  public ResponseEntity<ErrorResponse> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
    return handleException(e, ErrorCode.MAX_UPLOAD_SIZE_EXCEEDED);
  }

  /**
   * 400: Custom Index
   */
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e) {
    return handleException(e, e.getErrorCode());
  }

  @ExceptionHandler(AlreadyExistsException.class)
  public ResponseEntity<ErrorResponse> handleFriendExistsException(AlreadyExistsException e) {
    return handleException(e, e.getErrorCode());
  }

  @ExceptionHandler(InvalidRequestException.class)
  public ResponseEntity<ErrorResponse> handleInvalidRequestException(InvalidRequestException e) {
    return handleException(e, e.getErrorCode());
  }

  @ExceptionHandler(S3Exception.class)
  public ResponseEntity<ErrorResponse> handleInvalidRequestException(S3Exception e) {
    return handleException(e, e.getErrorCode());
  }

  @ExceptionHandler(ThumbnailException.class)
  public ResponseEntity<ErrorResponse> handleInvalidRequestException(ThumbnailException e) {
    return handleException(e, e.getErrorCode());
  }

  private ResponseEntity<ErrorResponse> handleException(Exception e, ErrorCode errorCode) {
    log.warn(e.getMessage(), e);
    ErrorResponse errorResponse = ErrorResponse.of(errorCode);
    return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
  }
}
