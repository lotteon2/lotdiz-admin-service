package com.lotdiz.adminservice.exceptionhandler;

import com.lotdiz.adminservice.utils.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class AdminRestControllerAdvice {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> invalidRequestHandler(MethodArgumentNotValidException e) {
    log.error("{}", e.getMessage());
    int statusCode = HttpStatus.BAD_REQUEST.value();
    ErrorResponse errorResponse =
        ErrorResponse.builder()
            .code(String.valueOf(statusCode))
            .message(
                e.getBindingResult().getFieldError() == null
                    ? e.getMessage()
                    : e.getBindingResult().getFieldError().getDefaultMessage())
            .detail(e.getCause().getMessage())
            .build();
    return ResponseEntity.status(statusCode).body(errorResponse);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> exception(Exception e) {
    log.error("{}", e.getMessage());
    int statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
    ErrorResponse errorResponse =
        ErrorResponse.builder().code(String.valueOf(statusCode)).message(e.getMessage()).build();
    return ResponseEntity.status(statusCode).body(errorResponse);
  }
}
